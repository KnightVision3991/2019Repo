/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import frc.robot.Gains;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.commands.CargoArmCommand;

/**
 * Add your docs here.
 */
public class CargoArm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX arm1;
  WPI_TalonSRX arm2;
  public static ShuffleboardTab tab = Shuffleboard.getTab("SmartDashboard");
  public NetworkTableEntry kP =
    tab.add("P", 0)
      .getEntry();
  public NetworkTableEntry kI =
    tab.add("I", 0)
      .getEntry();
  public NetworkTableEntry kD =
    tab.add("D", 0)
      .getEntry();
  public NetworkTableEntry kF =
    tab.add("F", 0)
      .getEntry();
  public NetworkTableEntry setPoint =
    tab.add("Set Point", 0)
      .getEntry();
  public NetworkTableEntry position =
    tab.add("position", 0)
      .getEntry();



  public CargoArm() {
    arm1 = new WPI_TalonSRX(6);
    arm2 = new WPI_TalonSRX(7);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CargoArmCommand());
  }

  public void setManualPower(double percent) {
    arm1.set(percent);
    arm2.set(-percent);

  }
  public void init(Gains oof){
    arm1.config_kP(0, oof.kP);
    arm1.config_kI(0, oof.kI);
    arm1.config_kD(0, oof.kD);
    arm1.config_kF(0, oof.kF);
    arm2.setInverted(true);
    arm1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }
  public void setPosition(double oof){
    arm1.set(ControlMode.Position, oof);
    arm2.follow(arm1);
  }
  public void configPID(double kp, double ki, double kd, double kf){
    arm1.config_kP(0, kp);
    arm1.config_kI(0, ki);
    arm1.config_kD(0, kd);
    arm1.config_kF(0, kf);
    arm1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }
  public void zeroPos(){
    arm1.setSelectedSensorPosition(0);
  }
  public void useConfigPID(){
    configPID(kP.getDouble(0), kI.getDouble(0), kD.getDouble(0), kF.getDouble(0));
  }
  public void updatePos(){
    int pos = arm1.getSelectedSensorPosition();
    position.setDouble(pos);
  }
}
