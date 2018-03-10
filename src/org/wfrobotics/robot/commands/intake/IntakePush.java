package org.wfrobotics.robot.commands.intake;

import org.wfrobotics.robot.Robot;
import org.wfrobotics.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class IntakePush extends Command
{
    protected final IntakeSubsystem intake = Robot.intakeSubsystem;

    public IntakePush(int timeout)
    {
        requires(intake);
        setTimeout(timeout);
    }

    protected void execute()
    {
        intake.setIntake(1); // TODO Pass a boolean, pick either in or out speed off that, make instantcommand, rename to IntakeSet?
    }

    protected boolean isFinished()
    {
        return isTimedOut();
    }
}
