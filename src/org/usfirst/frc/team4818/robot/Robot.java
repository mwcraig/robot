
package org.usfirst.frc.team4818.robot;

import com.taurus.SwerveController;
import com.taurus.SwerveChassis;
import com.taurus.SwerveConstants;
import com.taurus.SwerveVector;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
    // Motor Objects
    private SwerveChassis drive;
    
    // Joysticks
    private SwerveController controller;

    
    private boolean TEST = true;
    private final int TEST_MODE_NORMAL = 0;
    private final int TEST_MODE_WHEEL = 1;
    private SendableChooser testChooser = new SendableChooser();;
    private SendableChooser testWheelChooser = new SendableChooser();;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        
        drive = new SwerveChassis();
        controller = new SwerveController();
 
        // set up the choosers for running tests while in teleop mode
        testChooser = new SendableChooser();
        testChooser.addDefault("Normal",    Integer.valueOf(TEST_MODE_NORMAL));
        testChooser.addObject("Wheel Test", Integer.valueOf(TEST_MODE_WHEEL));
        SmartDashboard.putData("Test", testChooser);
        
        testWheelChooser = new SendableChooser();
        testWheelChooser.addDefault("Front Left", Integer.valueOf(0));
        testWheelChooser.addObject("Front Right", Integer.valueOf(1));
        testWheelChooser.addObject("Back Right",  Integer.valueOf(2));
        testWheelChooser.addObject("Back Left",   Integer.valueOf(3));
        SmartDashboard.putData("Test Wheel", testWheelChooser);
        
        SmartDashboard.putBoolean("TEST MODE", TEST);
        
    }

    /**
     * runs when robot is disabled
     */
    public void disabledPeriodic()
    {
        UpdateDashboard();
    }
    
    /**
     * runs when robot is disabled
     */
    public void disabledInit()
    {
        UpdateDashboard();
    }
    
    
    /**
     * runs when operator mode is enabled.
     */
    public void teleopInit()
    {
    }

    /**
     * runs during operator control
     */
    public void teleopPeriodic() 
    {
        UpdateDashboard();
        
        if(!TEST)
        {
            DriveNormal();
        }
        else
        {
            TestRun();
        }
    }
    
    /**
     * Update the dashboard with the common entries
     */
    private void UpdateDashboard()
    {
        // display the joysticks on smart dashboard
        SmartDashboard.putNumber("Left Mag",    controller.getMagnitude(Hand.kLeft));
        SmartDashboard.putNumber("Left Angle",  controller.getDirectionDegrees(Hand.kLeft));
        SmartDashboard.putNumber("Right Mag",   controller.getMagnitude(Hand.kRight));
        SmartDashboard.putNumber("Right Angle", controller.getDirectionDegrees(Hand.kRight));
        
        // display each wheel's mag and angle in SmartDashboard
        for(int i = 0; i < SwerveConstants.WheelCount; i++)
        {
            SmartDashboard.putNumber("Wheel " + Integer.toString(i) + " Mag", drive.getWheelActual(i).getMag());
            SmartDashboard.putNumber("Wheel " + Integer.toString(i) + " Angle", drive.getWheelActual(i).getAngle());
        }
        
        drive.MaxAvailableVelocity = SmartDashboard.getNumber("Max Velocity", drive.MaxAvailableVelocity);
        
        //SmartDashboard.putNumber("Gyro Angle", drive.getGyro().getAngle());
                
        // update the test mode
        // disable for competitions?
        TEST = SmartDashboard.getBoolean("TEST MODE", TEST);
    }
    
    /**
     * Run the test functions
     */
    private void TestRun()
    {
        switch(((Integer)testChooser.getSelected()).intValue())
        {
            case TEST_MODE_WHEEL:
                TestWheel(((Integer)testWheelChooser.getSelected()).intValue());
                break;
                
            case TEST_MODE_NORMAL:
            default:
                DriveNormal();
                break;
        }
    }
    
    /**
     * Test an individual wheel module
     */
    private void TestWheel(int index)
    {
        // use the left joystick to control the wheel module
        SwerveVector WheelActual = drive.getWheel(index).setDesired(controller.getHaloDrive_Velocity(), controller.getHighGearEnable());

        // display in SmartDashboard
        SmartDashboard.putNumber("Test Wheel Mag Actual", WheelActual.getMag());
        SmartDashboard.putNumber("Test Wheel Angle Actual", WheelActual.getAngle());
        
        // if the button is not held down, we're in high gear
        drive.setGearHigh(controller.getHighGearEnable());
        drive.UpdateShifter();
    }
    
    /**
     * Run the normal operating Drive system
     */
    private void DriveNormal()
    {
        // Use the Joystick inputs to update the drive system
        switch(controller.getDriveScheme())
        {
            case SwerveController.ANGLE_DRIVE:
                drive.UpdateAngleDrive(controller.getAngleDrive_Velocity(), controller.getAngleDrive_Heading());
                break;
                
            default:
            case SwerveController.HALO_DRIVE:
                drive.UpdateHaloDrive(controller.getHaloDrive_Velocity(), controller.getHaloDrive_Rotation());
                break;
        }

        // if the button is not held down, we're in high gear
        drive.setGearHigh(controller.getHighGearEnable());
        drive.setBrake(controller.getBrake());
    }

    /**
     * called at init of autonomous mode
     */
    public void autonomousInit()
    {
    }

    /**
     * This function is called periodically during autonomous mode.
     */
    public void autonomousPeriodic()
    {
    }

    /**
     * called at init of test mode
     */
    public void testInit()
    {
    }
    
    /**
     * This function is called periodically during test mode.
     */
    public void testPeriodic()
    {
    }
}