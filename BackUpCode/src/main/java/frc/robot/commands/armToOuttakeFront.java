package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class armToOuttakeFront extends InstantCommand {
  /**
   * Add your docs here.
   */
  public armToOuttakeFront() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.CargoArm);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.CargoArm.setPos(155);
    //Robot.CargoArm.armBrake.set(false);

    Robot.CargoArm.clearPID();
  }

}
