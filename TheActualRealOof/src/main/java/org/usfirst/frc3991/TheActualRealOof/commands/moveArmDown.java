/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3991.TheActualRealOof.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc3991.TheActualRealOof.Robot;

/**
 * Add your docs here.
 */
public class moveArmDown extends InstantCommand {
  /**
   * Add your docs here.
   */
  public moveArmDown() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(Robot.arm.position > 0){
      Robot.arm.position = Robot.arm.position++;
    }
  }

}
