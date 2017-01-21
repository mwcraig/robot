package org.wfrobotics.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    /*
     * Digital: 0-9 are on-board, 10-25 are on the MXP
     * Analog: 0-3 are on-board, 4-7 are on the MXP 
     * PWM: 0-9 are on-board, 10-19 are on the MXP
     */
    public enum DRIVE_SYSTEMS
    {
        DRIVE_TANK,
        DRIVE_MECANUM,
        DRIVE_SWERVE
    }
    
    public static final DRIVE_SYSTEMS DriveSystem = DRIVE_SYSTEMS.DRIVE_SWERVE;
    
    public static final int ANG_SWERVE_ANGLE[] = { 0, 1, 2, 3 };
    public static final int CAN_SWERVE_DRIVE_TALONS[] = { 1, 4, 5, 8};
    public static final int CAN_SWERVE_ANGLE_TALONS[] = { 2, 3, 6, 7};
    public static final int PWM_SWERVE_SHIFT_SERVOS[] = { 0, 1, 2, 3 };

    public static final int SHOOTER_MOTOR_SRX = 21;

    public static final int INTAKE_MOTOR_SRX[] = {22, 23};
    public static final boolean INTAKE_MOTOR_INVERT[] = {false, true};

    public static final int CAN_TANK_TALONS_RIGHT[] = {12,13};
    public static final int CAN_TANK_TALONS_LEFT[] = {10,11};
    
    public static final int CAN_MECANUM_TALONS_RIGHT[] = {7,2};
    public static final int CAN_MECANUM_TALONS_LEFT[] = {8,1};
}
