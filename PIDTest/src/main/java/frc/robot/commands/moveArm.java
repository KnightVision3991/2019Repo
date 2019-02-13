/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.arm;
import frc.robot.*;

public class moveArm extends Command {
  public moveArm() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(new arm());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    arm.useConfigPID();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    arm.main.set(ControlMode.Position, arm.setPoint.getDouble(0));
    arm.slave.follow(arm.main);
    arm.updatePos();
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
