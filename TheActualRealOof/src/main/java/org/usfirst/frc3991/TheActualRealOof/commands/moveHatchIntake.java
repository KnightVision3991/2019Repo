/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3991.TheActualRealOof.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc3991.TheActualRealOof.Robot;

import edu.wpi.first.wpilibj.command.Command;


import org.usfirst.frc3991.TheActualRealOof.Constants;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;


public class moveHatchIntake extends Command {
  public moveHatchIntake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hatchIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    Robot.hatchIntake.hatchRotate.config_kD(Constants.kSlotIdx, Constants.armGains.kD);
    Robot.hatchIntake.hatchRotate.config_kI(Constants.kSlotIdx, Constants.armGains.kI);
    Robot.hatchIntake.hatchRotate.config_kP(Constants.kSlotIdx, Constants.armGains.kP);
    Robot.hatchIntake.hatchRotate.config_kF(Constants.kSlotIdx, Constants.armGains.kF);
    Robot.hatchIntake.hatchRotate.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.hatchIntake.hatchRotate.set(ControlMode.Position, Robot.hatchIntake.position);


  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
