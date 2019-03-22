/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.Constants;

public class moveHatchIntake extends Command {
  public moveHatchIntake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hatchintake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.hatchintake.hatchRotate.config_kD(Constants.kSlotIdx, Constants.hatchGains.kD);
    Robot.hatchintake.hatchRotate.config_kI(Constants.kSlotIdx, Constants.hatchGains.kI);
    Robot.hatchintake.hatchRotate.config_kP(Constants.kSlotIdx, Constants.hatchGains.kP);
    Robot.hatchintake.hatchRotate.config_kF(Constants.kSlotIdx, Constants.hatchGains.kF);
    Robot.hatchintake.hatchRotate.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.hatchintake.hatchRotate.set(ControlMode.Position, Robot.hatchintake.position);
    Robot.hatchintake.updatePos();


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
