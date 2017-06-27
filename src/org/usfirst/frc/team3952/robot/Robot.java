package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 0 = back right
   2 = nothing
	3 = climber
		//4 = front right
		//5 = nothing
		//6 = nothing
		//7 = agitator
		//8 = launcher motor
		//9 = left front
 */
public class Robot extends IterativeRobot {
	private Shooter shooter;
	private MechanumWheels mechWheels;
	
	private Joystick joy;
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		mechWheels = new MechanumWheels(
				new Talon(9),
				new Talon(4),
				new Talon(1),
				new Talon(0)
		);
		shooter = new Shooter(
				new Talon(8),
				new Talon(7)
		);
		joy = new Joystick(0);
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		//driving
		mechWheels.setFromController(
				-MechanumWheels.MAX_SPEED * MechanumWheels.clean(joy.getX()), 
				-MechanumWheels.MAX_SPEED * MechanumWheels.clean(joy.getY()), 
				joy.getRawButton(5),
				joy.getRawButton(4)
		);
		
		//shooting
		shooter.reset(); //sets them all to zero so they don't keep spinning.
		
		if(joy.getTrigger())
    		shooter.shoot();
    	
		if(joy.getRawButton(3) && joy.getRawButton(2))
			shooter.agitateRandom();
		else if(joy.getRawButton(3)) 
    		shooter.agitateBackwards();
		else if(joy.getRawButton(2)) 
    		shooter.agitateForwards();
    	
		
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}

