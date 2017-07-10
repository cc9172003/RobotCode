package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.XboxController;

/**
 * This class serves as a layer between the actual controllers and the reading of
 * controller input. This is in place because it makes it easier to switch controllers 
 * later on.
 * It also makes it easier to add complex conditions.
 * 
 * 
 * Xbox mapping
 * 	Left getX, getY = movement
 * 	right getX = turning
 *  right bumper, trigger = left, right agitate
 *  left trigger = shoot
 *  a,b,x,y = commands. B = CANCEL command
 *  
 *
 */
public class Controller {
	private static final boolean USE_XBOX = false;
	
	private Joystick j;
	private XboxController x;
	
	public Controller(){
		if(USE_XBOX){
			this.x = new XboxController(0);
		} else {
			this.j= new Joystick(0);
		}
	}
	
	public double horizontalMovement(){
		if(USE_XBOX) return x.getX(Hand.kLeft);
		return j.getX();
	}
	
	public double lateralMovement(){
		if(USE_XBOX) return x.getY(Hand.kLeft);
		return j.getY();
	}
	
	public boolean shouldShoot(){
		if(USE_XBOX) return x.getTrigger(Hand.kLeft);
		return j.getTrigger();
	}
	
	public boolean shouldAgitateRandom(){
		if(USE_XBOX) return false;
		return j.getRawButton(3) && j.getRawButton(2);
	}
	
	public boolean shouldAgitateBackwards(){
		if(USE_XBOX) return x.getTriggerAxis(Hand.kRight) > 0.1;
		return j.getRawButton(3) && !j.getRawButton(2);
	}
	
	public boolean shouldAgitateForwards(){
		if(USE_XBOX) return x.getBumper(Hand.kRight);
		return j.getRawButton(2) && !j.getRawButton(3);
	}

	public boolean shouldStartLineUpToWall() {
		if(USE_XBOX) return x.getAButton();
		return j.getRawButton(6);
	}
	

	public boolean cancelTask() {
		if(USE_XBOX) return x.getBButton();
		return j.getRawButton(7);
	}

	public double turnValue() {
		if(USE_XBOX){
			return x.getX(Hand.kRight);
		} else{
			if (j.getRawButton(4))
				return -1;
			else if (j.getRawButton(5))
				return 1;
			return 0;
		}
		
	}
	
}
