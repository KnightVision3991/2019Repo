/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.RobotMap;
import frc.robot.commands.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static WPI_TalonSRX test1 = new WPI_TalonSRX(6);
  public static WPI_TalonSRX testSlave = new WPI_TalonSRX(7);
  public static ShuffleboardTab tab = Shuffleboard.getTab("SmartDashboard");
  public static NetworkTableEntry kP =
    tab.add("P", 0)
      .getEntry();
  public static NetworkTableEntry kI =
    tab.add("I", 0)
      .getEntry();
  public static NetworkTableEntry kD =
    tab.add("D", 0)
      .getEntry();
  public static NetworkTableEntry kF =
    tab.add("F", 0)
      .getEntry();
  public static NetworkTableEntry setPoint =
    tab.add("Set Point", 0)
      .getEntry();
  public static NetworkTableEntry position =
    tab.add("position", 0)
      .getEntry();
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new moveArm());

  }
  public static void configPID(double kp, double ki, double kd, double kf){
    test1.config_kP(0, kp);
    test1.config_kI(0, ki);
    test1.config_kD(0, kd);
    test1.config_kF(0, kf);
  }
  public static void zeroPos(){
    test1.setSelectedSensorPosition(0);
  }
  public static void useConfigPID(){
    configPID(kP.getDouble(0), kI.getDouble(0), kD.getDouble(0), kF.getDouble(0));
  }
  public static void updatePos(){
    int pos = test1.getSelectedSensorPosition();
    position.setDouble(pos);
  }


  
}
