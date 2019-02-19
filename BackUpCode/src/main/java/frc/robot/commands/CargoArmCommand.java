/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CargoArm;
import frc.robot.Constants;
import frc.robot.Gains;

public class CargoArmCommand extends Command {
  public CargoArmCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.CargoArm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.CargoArm.init(Constants.armGains);
    Robot.CargoArm.useConfigPID();
    //Robot.CargoArm.arm1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Robot.CargoArm.setManualPower(0);
    Robot.CargoArm.setPosition(Robot.CargoArm.position.getDouble(0));
    Robot.CargoArm.updatePos();
    //Robot.CargoArm.PIDArm(Robot.CargoArm.getArmPos());
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
