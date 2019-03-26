package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class zeroPos extends InstantCommand {

  public zeroPos() {
    super();
    requires(Robot.CargoArm);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.CargoArm.zeroPos();
  }

}
