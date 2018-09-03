package org.wfrobotics.robot.auto.modes;

import org.wfrobotics.reuse.commands.drive.DriveDistance;
import org.wfrobotics.reuse.commands.drive.TurnToHeading;
import org.wfrobotics.robot.auto.IntakeSet;
import org.wfrobotics.robot.auto.SwitchChoice;
import org.wfrobotics.robot.config.MatchState2018.Side;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ModeSwitchCenter extends CommandGroup
{
    double angleFirstTurn = 40.0;

    public ModeSwitchCenter()
    {
        // Score Cube
        addSequential(new DriveDistance(12.0));
        addSequential(new SwitchChoice(Side.Right, new TurnToHeading(angleFirstTurn), new TurnToHeading(-angleFirstTurn)));
        addSequential(new SwitchChoice(Side.Right, new TurnToHeading(angleFirstTurn), new TurnToHeading(-angleFirstTurn)));
        addSequential(new DriveDistance(100.0));
        addSequential(new TurnToHeading(0.0));
        addSequential(new TurnToHeading(0.0));
        //        addSequential(new DriveIntakeSensors(0.0, 1.0));
        addSequential(new DriveDistance(15.0));
        addSequential(new IntakeSet(0.4, 0.5));

        // Get Around Switch
        addSequential(new DriveDistance(-12.0));
        addSequential(new SwitchChoice(Side.Right, new TurnToHeading(90.0), new TurnToHeading(-90.0)));
        addSequential(new SwitchChoice(Side.Right, new TurnToHeading(90.0), new TurnToHeading(-90.0)));
        addSequential(new DriveDistance(12.0 * 4.0));
        addSequential(new TurnToHeading(0.0));
        addSequential(new TurnToHeading(0.0));
    }
}
