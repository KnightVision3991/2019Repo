/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3991.TheActualRealOof;

/**
 * Add your docs here.
 * 
 * 
 */
public class Constants {
    //set PID slot for all
    public static final int kSlotIdx = 0;
    //set PID loop for all
    public static final int kPIDLoopIdx = 0;
    //set timeout setting
    public static final int kTimeoutMs = 0;
    //set constansts for different outputs
    //Gains(kp, ki, kd, kf, izone, peak output);
    public static final Gains driveGains = new Gains(0.15, 0.0, 1.0, 0.0, 0, 1.0);
    public static final Gains armGains = new Gains(1, 0, 0, 0, 0, 0);
    public static final int armPos0 = 0;
    public static final int armPos1 = 200;
    public static final int armPos2 = 400;
    public static final int armPos3 = 800;
    public static final int armTolerance = 50;


    }
    
