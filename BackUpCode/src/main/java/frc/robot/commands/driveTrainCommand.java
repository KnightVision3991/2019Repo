/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
public class driveTrainCommand extends Command {
  public driveTrainCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double throttle = Math.abs(Robot.m_oi.joystick1.getRawAxis(3))*Robot.m_oi.joystick1.getRawAxis(3)*.75 - Math.abs(Robot.m_oi.joystick1.getRawAxis(2))*Robot.m_oi.joystick1.getRawAxis(2)*.75;
    double rot = Math.abs(Robot.m_oi.joystick1.getRawAxis(0))*Robot.m_oi.joystick1.getRawAxis(0)/2;
    int flip = Robot.driveTrain.getFlip();
    Robot.driveTrain.arcadeDrive(flip*throttle, flip*rot);
    if(flip == 1){
      Robot.m_oi.table.getEntry("stream").setDouble(2);
    }
    if(flip == -1){
      Robot.m_oi.table.getEntry("stream").setDouble(1);
    }
    //Robot.driveTrain.arcadeDrivePID(Robot.m_oi.joystick1.getRawAxis(3) - Robot.m_oi.joystick1.getRawAxis(2), Robot.m_oi.joystick1.getRawAxis(0));
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
