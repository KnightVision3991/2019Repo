package frc.robot.commands;

import edu.wpi.first.hal.sim.mockdata.RoboRioDataJNI;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class armToRocket extends InstantCommand {

  public armToRocket() {
    super();
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.CargoArm.setPos(110);
    //Robot.CargoArm.armBrake.set(false);

    Robot.CargoArm.clearPID();
  }

}
