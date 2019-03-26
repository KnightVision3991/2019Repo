package frc.robot.commands;

import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class armToIntake extends InstantCommand {

  public armToIntake() {
    super();
    requires(Robot.CargoArm);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.CargoArm.setPos(5);
    Robot.CargoArm.armBrake.set(false);
    Robot.CargoArm.clearPID();
  }

}
