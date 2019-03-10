/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import frc.robot.Gains;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
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
  DoubleSolenoid armBrake;
  int armPos;
  double armPosition;
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
    tab.add("position", 0).withWidget(BuiltInWidgets.kGraph)
      .getEntry();
  public NetworkTableEntry error =
    tab.add("error", 0)
      .getEntry();



  public CargoArm() {
    arm1 = new WPI_TalonSRX(6);
    arm2 = new WPI_TalonSRX(7);
    armBrake = new DoubleSolenoid(6, 2);
    //arm1.setInverted(true);
    //arm2.setInverted(true);
    arm1.setNeutralMode(NeutralMode.Brake);
    arm2.setNeutralMode(NeutralMode.Brake);

    configPID(1, .1, 0, 0);


  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CargoArmCommand());
  }

  public void setManualPower(double percent) {
    arm1.set(percent);
    arm2.set(percent);

  }
  public void init(Gains oof){
    arm1.config_kP(0, oof.kP);
    arm1.config_kI(0, oof.kI);
    arm1.config_kD(0, oof.kD);
    arm1.config_kF(0, oof.kF);
    arm2.setInverted(InvertType.OpposeMaster);
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
    arm2.setInverted(true);
    arm1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }
  public void zeroPos(){
    arm1.setSelectedSensorPosition(0);
  }
  public void useConfigPID(){
    //configPID(kP.getDouble(0), kI.getDouble(0), kD.getDouble(0), 0);
    configPID(1.7, .001, 750, 0);
  }
  public void updatePos(){
    int pos = arm1.getSelectedSensorPosition();
    position.setDouble(pos);
    //error.setDouble(arm1.getIntegralAccumulator());
  }
  public int getArmPos(){
    return armPos;
  }
  public void setArmPos(int bigoof){
    armPos = bigoof;
  }
  public void PIDArm(int oof){
    arm2.follow(arm1);
    switch(oof){
      case 0 :
          //set wanted position
          arm1.set(ControlMode.Position, Constants.armPos0);
          //apply brake when within tolerance of position
          if(Math.abs(Constants.armPos0 - arm1.getSelectedSensorPosition()) < Constants.armTolerance){
              armBrake.set(DoubleSolenoid.Value.kForward);
          }
          //release brake when not within tolerance of postition
          if(Math.abs(Constants.armPos0 - arm1.getSelectedSensorPosition()) > Constants.armTolerance){
              armBrake.set(DoubleSolenoid.Value.kReverse);
          }
      case 1 :
          arm1.set(ControlMode.Position, Constants.armPos1);
          if(Math.abs(Constants.armPos1 - arm1.getSelectedSensorPosition()) < Constants.armTolerance){
              armBrake.set(DoubleSolenoid.Value.kForward);
          }
          if(Math.abs(Constants.armPos1 - arm1.getSelectedSensorPosition()) > Constants.armTolerance){
              armBrake.set(DoubleSolenoid.Value.kReverse);
          }
      case 2 :
          arm1.set(ControlMode.Position, Constants.armPos2);
          if(Math.abs(Constants.armPos2 - arm1.getSelectedSensorPosition()) < Constants.armTolerance){
              armBrake.set(DoubleSolenoid.Value.kForward);
          }
          if(Math.abs(Constants.armPos2 - arm1.getSelectedSensorPosition()) > Constants.armTolerance){
              armBrake.set(DoubleSolenoid.Value.kReverse);
          }
      case 3 :
          arm1.set(ControlMode.Position, Constants.armPos3);
          if(Math.abs(Constants.armPos3 - arm1.getSelectedSensorPosition()) < Constants.armTolerance){
              armBrake.set(DoubleSolenoid.Value.kForward);
          }
          if(Math.abs(Constants.armPos3 - arm1.getSelectedSensorPosition()) > Constants.armTolerance){
              armBrake.set(DoubleSolenoid.Value.kReverse);
          }

  }
  }


  public void MotionMagicArm(double value) {
    //map joystick value to 0,1 rather than -1, 1
    /*double mappedValue = (value/2) + 0.5;
    arm1.set(ControlMode.Position, -200 * 1);
    arm2.follow(arm1);*/


    if(Math.abs(-value - arm1.getSelectedSensorPosition()) < Constants.armTolerance){
      armBrake.set(DoubleSolenoid.Value.kReverse);
      arm1.set(0);
    }
  //release brake when not within tolerance of postition
    if(Math.abs(-value - arm1.getSelectedSensorPosition()) > Constants.armTolerance){
      armBrake.set(DoubleSolenoid.Value.kForward);
      double mappedValue = Math.abs(value);
      arm1.set(ControlMode.Position, -value);
      arm2.follow(arm1);
    }


  }
  public void fArmControl(double oof){
    double ff = -.131*Math.cos((arm1.getSelectedSensorPosition()*Math.PI*2/1000) - .12);
    if(Math.abs(-oof - arm1.getSelectedSensorPosition()) > Constants.armTolerance){
      arm1.set(ControlMode.Position, -oof, DemandType.ArbitraryFeedForward, ff);
      arm2.follow(arm1);
      //armBrake.set(DoubleSolenoid.Value.kForward);
    }
    
    if(Math.abs(-oof - arm1.getSelectedSensorPosition()) < Constants.armTolerance){
      //armBrake.set(DoubleSolenoid.Value.kReverse);
      arm1.set(ControlMode.Position, -oof, DemandType.ArbitraryFeedForward, ff);
      arm2.follow(arm1);
    }

  }
  public void brakeArm(){
    armBrake.set(DoubleSolenoid.Value.kReverse);
  }
  public void setPos(double oof){
    armPosition = oof;
  }
  public double armPosition(){
    return armPosition;
  }
  public void clearPID(){
    arm1.setIntegralAccumulator(0);
  }

}
