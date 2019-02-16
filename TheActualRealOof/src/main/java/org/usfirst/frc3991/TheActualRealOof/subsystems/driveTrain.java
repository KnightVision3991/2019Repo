// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3991.TheActualRealOof.subsystems;



import edu.wpi.first.wpilibj.command.PIDSubsystem;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;

import org.usfirst.frc3991.TheActualRealOof.RobotMap;
import org.usfirst.frc3991.TheActualRealOof.OI;

import com.ctre.phoenix.motorcontrol.*;

import org.usfirst.frc3991.TheActualRealOof.commands.*;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class driveTrain extends PIDSubsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private DoubleSolenoid leftShift;
    private DoubleSolenoid rightShift;
    public int shift;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    TalonSRX[] driveTrainMotors;


    public driveTrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        super("driveTrain", 1, 2, 3);

        driveTrainMotors = new TalonSRX[RobotMap.driveTrainMotors.length];


        

        for(int i = 0; i < RobotMap.driveTrainMotors.length; i++) {
            driveTrainMotors[i] = new TalonSRX(RobotMap.driveTrainMotors[i]);

            driveTrainMotors[i].configNominalOutputForward(0);
            driveTrainMotors[i].configNominalOutputReverse(0);
            driveTrainMotors[i].configPeakOutputForward(1);
            driveTrainMotors[i].configPeakOutputForward(-1);

            driveTrainMotors[i].config_kP(0, RobotMap.driveTrainMotorPIDConfigs[i].P);
            driveTrainMotors[i].config_kI(0, RobotMap.driveTrainMotorPIDConfigs[i].I);
            driveTrainMotors[i].config_kD(0, RobotMap.driveTrainMotorPIDConfigs[i].D);
            driveTrainMotors[i].config_kF(0, 0);

        }
        
        
        
        leftShift = new DoubleSolenoid(0, 0, 1);
        addChild("leftShift",leftShift);
        
        
        rightShift = new DoubleSolenoid(0, 2, 3);
        addChild("rightShift",rightShift);
        
        for(int i = 1; i < RobotMap.driveTrainMotors.length/2; i++) {
            driveTrainMotors[i].set(ControlMode.Follower, driveTrainMotors[0].getDeviceID());
        }
    
        for(int i = (RobotMap.driveTrainMotors.length/2) + 1; i < RobotMap.driveTrainMotors.length; i++) {
            driveTrainMotors[i].set(ControlMode.Follower, driveTrainMotors[(RobotMap.driveTrainMotors.length/2) + 1].getDeviceID());
        }

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }




    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());

        setDefaultCommand(new DriveTrainCommand());
    }
    public void shift(){
        if(shift == 1){
            leftShift.set(DoubleSolenoid.Value.kForward);
            rightShift.set(DoubleSolenoid.Value.kForward);
        }
        else{
            rightShift.set(DoubleSolenoid.Value.kReverse);
            leftShift.set(DoubleSolenoid.Value.kReverse);
        }
    }

    @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;

    
    return 0.0;
  }


  public double leftPow;
  public double rightPow;

  public void usePow() {

    //LEFT SIDE
    driveTrainMotors[0].set(ControlMode.Velocity, leftPow); //USE THIS TO SET DESIRED TARGET FOR PID TO REACH TOO
    


    //RIGHT SIDE 
    driveTrainMotors[(RobotMap.driveTrainMotors.length/2)].set(ControlMode.Velocity, rightPow); //USE THIS TO SET DESIRED TARGET FOR PID TO REACH TOO

  }

  public void killMotors() {
      //LEFT SIDE
    driveTrainMotors[0].set(ControlMode.Velocity, 0); //USE THIS TO SET DESIRED TARGET FOR PID TO REACH TOO
    


    //RIGHT SIDE 
    driveTrainMotors[(RobotMap.driveTrainMotors.length/2)].set(ControlMode.Velocity, 0); //USE THIS TO SET DESIRED TARGET FOR PID TO REACH TOO


    
  }

  @Override
  public void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);

    //OI.leftJoyStick.getRawAxis(2);
    //LEFT SIDE


    double rot = OI.joystick1.getRawAxis(0);

    double throttle = OI.joystick1.getRawAxis(3) - OI.joystick1.getRawAxis(2);

    leftPow = ((throttle + rot) - 0.5) * 2;
    rightPow = ((throttle - rot) - 0.5) * 2;

    leftPow *= RobotMap.driveTrainMaxRPM;
    rightPow *= RobotMap.driveTrainMaxRPM;


    //LEFT SIDE
    driveTrainMotors[0].set(ControlMode.Velocity, leftPow); //USE THIS TO SET DESIRED TARGET FOR PID TO REACH TOO
    


    //RIGHT SIDE 
    driveTrainMotors[(RobotMap.driveTrainMotors.length/2)].set(ControlMode.Velocity, rightPow); //USE THIS TO SET DESIRED TARGET FOR PID TO REACH TOO


    /*for(int i = 1; i < RobotMap.driveTrainMotors.length/2; i++) {
        driveTrainMotors[i].set(ControlMode.Follower, driveTrainMotors[0].getDeviceID());
    }

    for(int i = (RobotMap.driveTrainMotors.length/2) + 1; i < RobotMap.driveTrainMotors.length; i++) {
        driveTrainMotors[i].set(ControlMode.Follower, driveTrainMotors[(RobotMap.driveTrainMotors.length/2) + 1].getDeviceID());
    }*/
    

  }


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

