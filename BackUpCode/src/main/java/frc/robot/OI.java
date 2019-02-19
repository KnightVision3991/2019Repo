/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.commands.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public Joystick joystick1 = new Joystick(0);

  public JoystickButton A = new JoystickButton(joystick1, 1);
  public JoystickButton B = new JoystickButton(joystick1, 2);
  public JoystickButton X = new JoystickButton(joystick1, 3);
  public JoystickButton Y = new JoystickButton(joystick1, 4);
  public JoystickButton LB = new JoystickButton(joystick1, 5);
  public JoystickButton RB = new JoystickButton(joystick1, 6);
  public JoystickButton STRT = new JoystickButton(joystick1, 7);
  public JoystickButton BACK = new JoystickButton(joystick1, 8);
  public POVButton up = new POVButton(joystick1, 0);
  public POVButton right = new POVButton(joystick1, 90);
  public POVButton down = new POVButton(joystick1, 180);
  public POVButton left = new POVButton(joystick1, 270);


  
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  public OI(){

    X.whenPressed(new CargoIntakeOut());
    A.whenPressed(new CargoIntakeIn());
    B.whenPressed(new CargoIntakeOff());
    up.whenPressed(new armToDefence());
    down.whenPressed(new armToIntake());
    right.whenPressed(new armToOuttakeFront());
    left.whenPressed(new armToOuttakeBack());
    RB.whenPressed(new shiftUp());
    LB.whenPressed(new shiftDown());
    STRT.whenPressed(new hatchIntakeExtend());
    BACK.whenPressed(new HatchIntakeRetract());

    ShuffleboardTab tab = Shuffleboard.getTab("SmartDashboard");
      //tab.add("Config PID", new configPID());
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
      tab.add("Hatch Extend", new hatchIntakeExtend());
      tab.add("Hatch Retract", new HatchIntakeRetract());


  }
}
