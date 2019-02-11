// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3991.TheActualRealOof.commands;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc3991.TheActualRealOof.Constants;
import org.usfirst.frc3991.TheActualRealOof.Robot;

/**
 *
 */
public class moveArm extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public WPI_TalonSRX oof;
    public int armPos;
    public DoubleSolenoid armBrake;
    public moveArm() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.arm);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        oof = Robot.arm.armMain;
        armBrake = Robot.arm.armBrake;
        armPos = Robot.arm.position;
        
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        oof.config_kD(Constants.kSlotIdx, Constants.armGains.kD);
        oof.config_kI(Constants.kSlotIdx, Constants.armGains.kI);
        oof.config_kP(Constants.kSlotIdx, Constants.armGains.kP);
        oof.config_kF(Constants.kSlotIdx, Constants.armGains.kF);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        switch(armPos){
            case 0 :
                //set wanted position
                oof.set(ControlMode.Position, Constants.armPos0);
                //apply brake when within tolerance of position
                while(Math.abs(Constants.armPos0 - oof.getSelectedSensorPosition()) < Constants.armTolerance){
                    armBrake.set(DoubleSolenoid.Value.kForward);
                }
                //release brake when not within tolerance of postition
                while(Math.abs(Constants.armPos0 - oof.getSelectedSensorPosition()) > Constants.armTolerance){
                    armBrake.set(DoubleSolenoid.Value.kReverse);
                }
            case 1 :
                oof.set(ControlMode.Position, Constants.armPos1);
                while(Math.abs(Constants.armPos1 - oof.getSelectedSensorPosition()) < Constants.armTolerance){
                    armBrake.set(DoubleSolenoid.Value.kForward);
                }
                while(Math.abs(Constants.armPos1 - oof.getSelectedSensorPosition()) > Constants.armTolerance){
                    armBrake.set(DoubleSolenoid.Value.kReverse);
                }
            case 2 :
                oof.set(ControlMode.Position, Constants.armPos2);
                while(Math.abs(Constants.armPos2 - oof.getSelectedSensorPosition()) < Constants.armTolerance){
                    armBrake.set(DoubleSolenoid.Value.kForward);
                }
                while(Math.abs(Constants.armPos2 - oof.getSelectedSensorPosition()) > Constants.armTolerance){
                    armBrake.set(DoubleSolenoid.Value.kReverse);
                }
            case 3 :
                oof.set(ControlMode.Position, Constants.armPos3);
                while(Math.abs(Constants.armPos3 - oof.getSelectedSensorPosition()) < Constants.armTolerance){
                    armBrake.set(DoubleSolenoid.Value.kForward);
                }
                while(Math.abs(Constants.armPos3 - oof.getSelectedSensorPosition()) > Constants.armTolerance){
                    armBrake.set(DoubleSolenoid.Value.kReverse);
                }

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
