/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 * Add your docs here.
 */

public class driveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX left;
  WPI_TalonSRX right;
  Waypoint[] waypoints;
  Trajectory.Config config;
  Trajectory trajectory;
  double samples;
  double dt;
  double max_velocity;
  double max_acceleration;
  double max_jerk;
  double wheelbase_width;
  TankModifier tankModifier;
  Trajectory leftTrajectory;
  Trajectory rightTrajectory;
  EncoderFollower leftFollower;
  EncoderFollower rightFollower;

  public driveTrain(){
    left = new WPI_TalonSRX(0);
    right = new WPI_TalonSRX(1);
    waypoints = new Waypoint[]{
      new Waypoint(0, 0, 0),
      new Waypoint(1, 1, 0),
      new Waypoint(0, 2, 0),
    };
    dt = 0.05;
    max_velocity = 5;
    max_acceleration = 5;
    max_jerk = 50;
    config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, dt, max_velocity, max_acceleration, max_jerk);
    trajectory = Pathfinder.generate(waypoints, config);
    tankModifier = new TankModifier(trajectory);
    tankModifier.modify(wheelbase_width);
    leftTrajectory = tankModifier.getLeftTrajectory();
    rightTrajectory = tankModifier.getRightTrajectory();
    leftFollower = new EncoderFollower(leftTrajectory);
    rightFollower = new EncoderFollower(rightTrajectory);
    

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
