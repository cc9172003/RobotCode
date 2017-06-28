package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class serves as a layer between the actual controllers and the reading of
 * controller input. This is in place because it makes it easier to switch controllers 
 * later on.
 * It also makes it easier to add complex conditions.
 *
 */
public class Controller {
	private Joystick j;
	
	public Controller(){
		this.j= new Joystick(0);
	}
	
	public double horizontalMovement(){
		return j.getX();
	}
	
	public double lateralMovement(){
		return j.getY();
	}
	
	public boolean shouldTurnRight(){
		return j.getRawButton(5);
	}
	
	public boolean shouldTurnLeft(){
		return j.getRawButton(4);
	}
	
	public boolean shouldShoot(){
		return j.getTrigger();
	}
	
	public boolean shouldAgitateRandom(){
		return j.getRawButton(3) && j.getRawButton(2);
	}
	
	public boolean shouldAgitateBackwards(){
		return j.getRawButton(3) && !j.getRawButton(2);
	}
	
	public boolean shouldAgitateForwards(){
		return j.getRawButton(2) && !j.getRawButton(3);
	}
}
