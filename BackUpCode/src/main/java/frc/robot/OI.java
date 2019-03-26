package frc.robot;


import frc.robot.commands.*;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.*;

public class OI {

  public Joystick joystick1 = new Joystick(0);
  public Joystick joystick2 = new Joystick(1);

  public JoystickButton A = new JoystickButton(joystick2, 1);
  public JoystickButton B = new JoystickButton(joystick2, 2);
  public JoystickButton X = new JoystickButton(joystick2, 3);
  public JoystickButton Y = new JoystickButton(joystick2, 4);
  public JoystickButton Y2 = new JoystickButton(joystick1, 4);
  public JoystickButton LB = new JoystickButton(joystick1, 5);
  public JoystickButton RB = new JoystickButton(joystick1, 6);
  public JoystickButton STRT = new JoystickButton(joystick1, 7);
  public JoystickButton BACK = new JoystickButton(joystick1, 8);
  public POVButton up = new POVButton(joystick2, 0);
  public POVButton right = new POVButton(joystick2, 90);
  public POVButton down = new POVButton(joystick2, 180);
  public POVButton left = new POVButton(joystick2, 270);
  public NetworkTable table;


  public Button limitSwitch = new Button(){
  
    @Override
    public boolean get() {
      if(!Robot.cargoIntake.limitSwitch.get()) {
        return true;
      } else {
        return false;

      }
    }
  };

  public OI(){

    X.whenPressed(new CargoIntakeOut());
    A.whenPressed(new CargoIntakeIn());
    B.whenPressed(new CargoIntakeOff());
    Y.whileHeld(new brakeArm());
    up.whenPressed(new armToDefence());
    down.whenPressed(new armToIntake());
    right.whenPressed(new armToOuttakeFront());
    left.whenPressed(new armToRocket());
    RB.whenPressed(new shiftUp());
    LB.whenPressed(new shiftDown());
    STRT.whenPressed(new moveHatchIntakeLever());
    BACK.whenPressed(new moveHatchIntakePiston());
    limitSwitch.whenPressed(new CargoIntakeOff());
    Y2.whenPressed(new flipDrive());
    table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);


    ShuffleboardTab tab = Shuffleboard.getTab("SmartDashboard");
      tab.add("Config PID", new PIDConfig());
      tab.add("Zero Position", new zeroPos());
      tab.add("Arm Intake", new armToIntake());
      tab.add("Arm Outtake Front", new armToOuttakeFront());
      tab.add("Arm Defence", new armToDefence());
      tab.add("Arm Outtake Back", new armToOuttakeBack());
      tab.add("Intake In", new CargoIntakeIn());
      tab.add("Intake Out", new CargoIntakeOut());
      tab.add("Intake Off", new CargoIntakeOff());
      tab.add("Shift Up", new shiftUp());
      tab.add("Shift Down", new shiftDown());
      tab.add("Hatch Up", new moveHatchIntakeLever());
      tab.add("Hatch Down", new moveHatchIntakePiston());
      tab.add("rocket", new armToRocket());


  }
}
