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
    //Robot.CargoArm.MotionMagicArm(Robot.m_oi.joystick1.getRawAxis(5));
    //Robot.CargoArm.Motion `agicArm(Robot.CargoArm.setPoint.getDouble(0));
    if(Robot.m_oi.Y.get()){
      Robot.CargoArm.setManualPower(Robot.m_oi.joystick2.getRawAxis(5)*Math.abs(Robot.m_oi.joystick2.getRawAxis(5)));
    }else{
      //Robot.CargoArm.fArmControl(Robot.CargoArm.armPosition());
      //Robot.CargoArm.setManualPower(Robot.m_oi.joystick1.getRawAxis(5));
      Robot.CargoArm.fMotionMagicControl(Robot.CargoArm.armPosition());
      Robot.CargoArm.updatePos();
      //Robot.CargoArm.PIDArm(Robot.CargoArm.getArmPos());
    }
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
