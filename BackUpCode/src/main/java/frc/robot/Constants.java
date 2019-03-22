/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Constants {
    public static final int kSlotIdx = 0;
    public static final int armPos0 = 0;
    public static final int armPos1 = 200;
    public static final int armPos2 = 400;
    public static final int armPos3 = 600;
    public static final int armTolerance = 4;
    public static final Gains armGains = new Gains(1, 0, 0, 0, 0, 1);
    public static final Gains hatchGains = new Gains(1, 0, 0, 0, 0, 1);

    public static final Gains leftDriveGains = new Gains(0.25, 0.00, 0, 1023/7200, 300, 1);
    public static final Gains rightDriveGains = new Gains(0.25, 0.00, 0, 1023/7200, 300, 1);

}
