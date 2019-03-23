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
    double r = Robot.m_oi.joystick1.getRawAxis(3);
    double l = Robot.m_oi.joystick1.getRawAxis(2);
    double ls = Robot.m_oi.joystick1.getRawAxis(0);


    double throttle = smoothedJoystick(r)-smoothedJoystick(l);//r*r*r - l*l*l;
    double rot = ls*ls*ls;
    int flip = Robot.driveTrain.getFlip();
    Robot.driveTrain.arcadeDrive(flip*throttle, flip*rot);
    if(flip == 1){
      Robot.m_oi.table.getEntry("stream").setNumber(1);
    }
    if(flip == -1){
      Robot.m_oi.table.getEntry("stream").setNumber(2);
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


  public double smoothedJoystick(double x) {
    //return (6 * Math.pow(x, 5)) - (15 * Math.pow(x, 4) + (10 * Math.pow(x, 3)));
    return (.5 * Math.pow(x, 3)) + (.5 * x);
  }
}
