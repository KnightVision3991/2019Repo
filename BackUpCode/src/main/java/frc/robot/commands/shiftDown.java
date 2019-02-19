/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class shiftDown extends Command {
  public shiftDown() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.driveTrain.leftShift.get() == Value.kOff || Robot.driveTrain.leftShift.get() == Value.kForward) {
      Robot.driveTrain.leftShift.set(Value.kReverse);
    }

    if(Robot.driveTrain.rightShift.get() == Value.kOff || Robot.driveTrain.rightShift.get() == Value.kForward) {
      Robot.driveTrain.rightShift.set(Value.kReverse);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.driveTrain.leftShift.get() == Value.kReverse && Robot.driveTrain.rightShift.get() == Value.kReverse) {
      return true;
    }
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
