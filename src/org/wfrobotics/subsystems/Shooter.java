package org.wfrobotics.subsystems;

import org.wfrobotics.commands.Rev;
import org.wfrobotics.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem 
{
    private CANTalon m_motor;
    private double m_speedDesired;

    public Shooter()
    {
        m_motor = new CANTalon(RobotMap.SHOOTER_MOTOR_SRX);
        m_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        m_motor.changeControlMode(TalonControlMode.Speed);
        m_motor.setPID(.115,0.0001,0.015);
        m_motor.setCloseLoopRampRate(.01);
        
        //is this needed?
        m_motor.setInverted(true);
    }
    
    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new Rev(Rev.MODE.OFF));
    }

    /**
     * Control speed of the shooting wheel(s)
     * @param rpm (usually between 3500 and 4000)
     * @return current speed the shooter wheel is running at
     */
    public double setSpeed(double rpm)
    {
        m_speedDesired = rpm;
        m_motor.set(m_speedDesired);
        printDash();
        
        return m_motor.getSpeed();
    }
    
    /**
     * Tells if the current speed is at the previously set speed within this tolerance
     * @param tolerance percent above or below that counts as being at that speed (ex: .1 = +/-10%) 
     * @return if the shooting wheel(s) is at that speed
     */
    public boolean speedReached(double tolerance)
    {
        return Math.abs(m_speedDesired - m_motor.getSpeed()) <= tolerance;
    }

    public void printDash()
    {
        SmartDashboard.putNumber("ShooterSpeedDesired", m_speedDesired);
        SmartDashboard.putNumber("ShooterSpeedActual", m_motor.getSpeed());
    }
}
