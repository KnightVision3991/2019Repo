/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3991.TheActualRealOof.commands;

import javax.swing.text.StyleContext.SmallAttributeSet;

import org.usfirst.frc3991.TheActualRealOof.Robot;
import org.usfirst.frc3991.TheActualRealOof.subsystems.driveTrain;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainCommand extends Command {




  public DriveTrainCommand() {
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

    //Robot.driveTrain.usePIDOutput(0);
    double oof = Robot.oi.getThrottle();
    Robot.driveTrain.manualDrive(oof);
    /*NetworkTableEntry big_oof = 
            tab.add("oof", 0)
                .getEntry();
        big_oof.setDouble(oof);

    while(!isFinished()) {
      Robot.driveTrain.manualDrive(Robot.oi.joystick1.getRawAxis(3) - Robot.oi.joystick1.getRawAxis(2));
      oof = oof++;
      big_oof.setDouble(oof);

    }*/

  }
  public int oof;
  public ShuffleboardTab tab = Shuffleboard.getTab("SmartDashboard");


  

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

    Robot.driveTrain.killMotors();

  }
}
