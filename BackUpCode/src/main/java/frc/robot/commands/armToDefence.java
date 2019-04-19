package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class armToDefence extends InstantCommand {

  public armToDefence() {
    super();
    requires(Robot.CargoArm);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.CargoArm.setPos(250);
    //Robot.CargoArm.armBrake.set(false);
    Robot.CargoArm.clearPID();
  }

}
