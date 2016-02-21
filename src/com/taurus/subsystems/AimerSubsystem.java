package com.taurus.subsystems;

import com.taurus.PIDController;
import com.taurus.commands.AimerStop;
import com.taurus.hardware.MagnetoPot;
import com.taurus.hardware.MagnetoPotSRX;
import com.taurus.robot.RobotMap;
import com.taurus.vision.Vision;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AimerSubsystem extends Subsystem
{
    private Vision vision;
    private MagnetoPotSRX aimAngle;
    private CANTalon aimer;
    private PIDController aimerPID;

    public AimerSubsystem()
    {
        aimer = new CANTalon(RobotMap.PIN_SHOOTER_TALON_AIMER);
        aimerPID = new PIDController(1, 0, 0, 1);//TODO update these values 
        aimAngle = new MagnetoPotSRX(aimer,360);
        vision = Vision.getInstance();
    }

    protected void initDefaultCommand()
    {
        // TODO Auto-generated method stub 

        setDefaultCommand(new AimerStop());
    }
    
    /**
     * aims the shooter
     * @param changeInAngle 0 to 360
     * @return true if angle reached, false if not
     */
    public boolean aim(double changeInAngle)
    {
        double motorOutput = aimerPID.update(changeInAngle);//TODO add limits for angle

        if(Math.abs(changeInAngle) <= 5){
            aimer.set(0);
            return true;
        } else {
            //aimer.set(motorOutput);
            return false;
        }
    }
    /***
     * basic speed function 
     * @param speed
     */
    public void setSpeed(double speed){
        aimAngle.setOffset(Preferences.getInstance().getDouble("AimerOffset", 0));
        SmartDashboard.putNumber("Aimer Angle", aimAngle.get());
        aimer.set(speed);
    }
    
    
    
    /**
     * aims the shooter at the detected target 
     * @return true when aimer is at desired angle
     */
    public boolean aim()
    {
        return aim(vision.getTarget().Pitch());
    }
    
    public double getCurrentAngle()
    {
        return aimAngle.get();
    }
}
