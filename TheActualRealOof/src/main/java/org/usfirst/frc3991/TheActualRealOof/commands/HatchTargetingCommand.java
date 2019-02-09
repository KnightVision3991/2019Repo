/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3991.TheActualRealOof.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc3991.TheActualRealOof.RobotMap;
import org.usfirst.frc3991.TheActualRealOof.subsystems.*;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.*;


public class HatchTargetingCommand extends Command {

  driveTrain dt;
  cargoIntake ci;


  public HatchTargetingCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(dt);
    requires(ci);
  }

  // Called just before this Command runs the first time




  NetworkTable table;
  NetworkTableEntry TX;
  NetworkTableEntry TY;


  @Override
  protected void initialize() {
    
    dt = new driveTrain();
    ci = new cargoIntake();

    table = NetworkTableInstance.getDefault().getTable("limelight");
    TX = table.getEntry("tx");
    TY = table.getEntry("ty");

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {


    double tx = TX.getDouble(0.0);
    double heading_error = -tx;

    double ty = TY.getDouble(0.0);
    double distance_error = -ty;


    double steering_adjust = 0.0;


    double kPaim = 1.0;
    double kIaim = 1.0;
    double kDaim = 1.0;

    double kPdistance = 1.0;
    double kIdistance = 1.0;
    double kDditsance = 1.0;

    if(tx > 1.0) {

      steering_adjust = kPaim * heading_error - 0.05;

    } else if(tx < 1.0) {
      steering_adjust = kPaim * heading_error + 0.05;
    }

    double distance_adjust = kPdistance * distance_error;

    double leftPower = (distance_adjust + steering_adjust);
    double rightPower = (distance_adjust - steering_adjust);

    leftPower *= RobotMap.driveTrainMaxRPM;
    rightPower *= RobotMap.driveTrainMaxRPM;


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
    dt.killMotors();
  }
}
