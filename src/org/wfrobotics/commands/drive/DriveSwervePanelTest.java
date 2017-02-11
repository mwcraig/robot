
package org.wfrobotics.commands.drive;

import org.wfrobotics.Utilities;
import org.wfrobotics.controller.Panel.COLOR;
import org.wfrobotics.robot.OI;
import org.wfrobotics.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSwervePanelTest extends Command 
{
    public DriveSwervePanelTest()
    {
        requires(Robot.driveSubsystem);
    }

    protected void initialize()
    {
        
    }

    protected void execute() 
    {
        Utilities.PrintCommand("Drive", this);

        double values[] = OI.DriveSwerveOI.getPanelKnobs();
        boolean save = OI.DriveSwerveOI.getPanelSave();
        
        COLOR leds[];
        
        if(save)
        {
            leds = new COLOR[] { COLOR.RED, COLOR.RED, COLOR.RED, COLOR.RED};
        }
        else
        {
            leds = new COLOR[] { COLOR.GREEN, COLOR.GREEN, COLOR.GREEN, COLOR.GREEN};
        }
        
        OI.setPanelLEDs(leds, leds);
        
        Robot.driveSubsystem.fullWheelCalibration(.5, values, save);
        
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() 
    {
    }

    protected void interrupted() 
    {
    }
}
