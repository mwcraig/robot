package org.wfrobotics.robot.commands.intake;

import org.wfrobotics.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/** Set the solenoids to the opposite state, repeated-buttonsmashing-safe */
public class WristToggle extends InstantCommand
{
    protected void initialize()
    {
        Robot.intakeSubsystem.setVert(!Robot.intakeSubsystem.getVertical());
    }
}