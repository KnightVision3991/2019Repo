/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3991.TheActualRealOof.commands;

import org.usfirst.frc3991.TheActualRealOof.Robot;
import org.usfirst.frc3991.TheActualRealOof.subsystems.driveTrain;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class shift extends InstantCommand {
  /**
   * Add your docs here.
   */
  public shift() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    driveTrain.shift = driveTrain.shift*-1;
  }

}
