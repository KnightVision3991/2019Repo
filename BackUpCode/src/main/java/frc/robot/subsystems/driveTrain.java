/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.driveTrainCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;
import frc.robot.Gains;
/**
 * Add your docs here.
 */
public class driveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  WPI_TalonSRX right1;
  WPI_TalonSRX right2;
  WPI_TalonSRX right3;

  WPI_TalonSRX left1;
  WPI_TalonSRX left2;
  WPI_TalonSRX left3;

  public DoubleSolenoid shift;

  int driveOof;

  public driveTrain() {
    left1 = new WPI_TalonSRX(0);
    left2 = new WPI_TalonSRX(1);
    left3 = new WPI_TalonSRX(2);

    right1 = new WPI_TalonSRX(3);
    right2 = new WPI_TalonSRX(4);
    right3 = new WPI_TalonSRX(5);

    shift = new DoubleSolenoid(0, 1); //Due to change

    shift.set(Value.kOff);


    left2.set(ControlMode.Follower, 0);
    left3.set(ControlMode.Follower, 0);


    right2.set(ControlMode.Follower, 3);
    right3.set(ControlMode.Follower, 3);

    setMotorGains(left1, Constants.leftDriveGains, false);
    setMotorGains(right1, Constants.rightDriveGains, false);

    driveOof = 1;

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // set
    setDefaultCommand(new driveTrainCommand());
  }

  public void arcadeDrive(double throttle, double rot) {
      double leftPow = throttle + rot;
      double rightPow = throttle - rot; 

      left1.set(ControlMode.PercentOutput, -leftPow);
      right1.set(ControlMode.PercentOutput, rightPow);

      left2.set(ControlMode.Follower, 0);
      left3.set(ControlMode.Follower, 0);


      right2.set(ControlMode.Follower, 3);
      right3.set(ControlMode.Follower, 3);


      SmartDashboard.putNumber("Left Encoder Value", left1.getSelectedSensorVelocity());
      SmartDashboard.putNumber("Right Encoder Value", right1.getSelectedSensorVelocity());


  }

  public void arcadeDrivePID(double throttle, double rot) {
    double leftPow = throttle + rot;
    double rightPow = throttle - rot; 
    //leftPow *= -1;

    //leftPow *= 500 * 4096 / 600;
    //rightPow *= 500 * 4096 / 600;

    left1.set(ControlMode.Velocity, 22000 * -leftPow);
    right1.set(ControlMode.Velocity, 22000 * rightPow);

    left2.set(ControlMode.Follower, 0);
    left3.set(ControlMode.Follower, 0);


    right2.set(ControlMode.Follower, 3);
    right3.set(ControlMode.Follower, 3);

  }


  public void setMotorGains(WPI_TalonSRX motor, Gains gains, boolean sensorPhase) {
    motor.configFactoryDefault();
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
    

    motor.setSensorPhase(sensorPhase);

    motor.configNominalOutputForward(0);
    motor.configNominalOutputReverse(0);

    motor.configPeakOutputForward(1);
    motor.configPeakOutputReverse(-1);

    motor.config_kF(0, gains.kF);
    motor.config_kP(0, gains.kP);
    motor.config_kI(0, gains.kI);
    motor.config_kD(0, gains.kD);


  }

  public int getFlip(){
    return driveOof;
  }
  
  public void flip(){
    driveOof = driveOof*-1;
  }

}
