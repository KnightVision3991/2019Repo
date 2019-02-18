/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.driveTrainCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 * Add your docs here.
 */
public class driveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  WPI_TalonSRX right1;
  WPI_TalonSRX right2;
  WPI_TalonSRX right3;

  WPI_TalonSRX left1;
  WPI_TalonSRX left2;
  WPI_TalonSRX left3;



  public driveTrain() {
    left1 = new WPI_TalonSRX(0);
    left2 = new WPI_TalonSRX(1);
    left3 = new WPI_TalonSRX(2);

    right1 = new WPI_TalonSRX(3);
    right2 = new WPI_TalonSRX(4);
    right3 = new WPI_TalonSRX(5);

    left2.set(ControlMode.Follower, 0);
    left3.set(ControlMode.Follower, 0);


    right2.set(ControlMode.Follower, 3);
    right3.set(ControlMode.Follower, 3);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // set
    setDefaultCommand(new driveTrainCommand());
  }

  public void arcadeDrive(double throttle, double rot) {
      double leftPow = throttle + rot;
      double rightPow = throttle - rot; 

      left1.set(ControlMode.PercentOutput, leftPow);
      right1.set(ControlMode.PercentOutput, rightPow);

  }


}
