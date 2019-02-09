/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.DriveTrainCommand;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 * Add your docs here.
 */
public class DriveTrain extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  TalonSRX[] driveTrainMotors;

  DifferentialDrive dd;
  SpeedControllerGroup l;
  SpeedControllerGroup r;


  public void Init() {
    driveTrainMotors = new TalonSRX[RobotMap.driveTrainMotorAmount];


    if(RobotMap.driveTrainMotorAmount > RobotMap.driveTrainMotors.length) {
      DriverStation.reportError("YOU ROBOTMAP HAS DONE OOFED, THE AMOUNT OF MOTORS THAT YOU HAVE SET DO NOT ALL HAVE A CORRESPONDING CAN ID!", false);
    }

    for(int i = 0; i < RobotMap.driveTrainMotorAmount; i++) {
      driveTrainMotors[i] = new TalonSRX(RobotMap.driveTrainMotors[i]);

      driveTrainMotors[i].configNominalOutputForward(0);
      driveTrainMotors[i].configNominalOutputReverse(0);
      driveTrainMotors[i].configPeakOutputForward(1);
      driveTrainMotors[i].configPeakOutputForward(-1);

      driveTrainMotors[i].config_kP(0, RobotMap.driveTrainMotorPIDConfigs[i].P);
      driveTrainMotors[i].config_kI(0, RobotMap.driveTrainMotorPIDConfigs[i].I);
      driveTrainMotors[i].config_kD(0, RobotMap.driveTrainMotorPIDConfigs[i].D);
      driveTrainMotors[i].config_kF(0, 0);

    }

    

    

//frc grip
  }

  public DriveTrain() {
    // Intert a subsystem name and PID values here
    //-------Name-------P--I--D NOT USED SINCE WE ARE USING CLOSED LOOPED TALONS
    super("DriveTrain", 1, 2, 3);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new DriveTrainCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;

    
    return 0.0;
  }

  @Override
  public void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);

    //OI.leftJoyStick.getRawAxis(2);
    //LEFT SIDE


    double rot = OI.leftJoyStick.getRawAxis(0);

    double throttle = OI.leftJoyStick.getRawAxis(3) - OI.leftJoyStick.getRawAxis(2);

    double leftPow = throttle - rot;
    double rightPow = throttle + rot;

    leftPow *= 4000;
    rightPow *= 4000;



    for(int i = 0; i < RobotMap.driveTrainMotorAmount/2; i++) {
      driveTrainMotors[i].set(ControlMode.Velocity, leftPow); //USE THIS TO SET DESIRED TARGET FOR PID TO REACH TOO
    }


    //RIGHT SIDE 
    for(int i = RobotMap.driveTrainMotorAmount/2; i < RobotMap.driveTrainMotorAmount; i++) {
      driveTrainMotors[i].set(ControlMode.Velocity, rightPow); //USE THIS TO SET DESIRED TARGET FOR PID TO REACH TOO

    }

  }
}
