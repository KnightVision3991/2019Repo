package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class shiftDown extends Command {
  public shiftDown() {;
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.driveTrain.shift.get() == Value.kOff || Robot.driveTrain.shift.get() == Value.kReverse) {
      Robot.driveTrain.shift.set(Value.kForward);
    }

    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.driveTrain.shift.get() == Value.kForward) {
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
