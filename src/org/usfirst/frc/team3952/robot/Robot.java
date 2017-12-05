package org.usfirst.frc.team3952.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 0 = Back right
   2 = nothing
	3 = climber
		//4 = front right
		//5 = nothing
		//6 = nothing
		//7 = agitator
		//8 = launcher motor
		//9 = left front
	//0 bottom right* 
* Rules which will keep this code easy to read
* 
* 0. Put decision logic (when should we shoot, when should we agitate) in Controller, put motor and 
* camera actions in Systems (MechanumWheels class, Shooter class) and keep the Robot class 
* as slim as possible. The less code there is in Robot, the easier it is to read.
* 1. Try to not pass controller in as an argument in method calls. That way,
* all the controller decision making is inside Robot. Itll make it easier to
* find out why certain things do what they do. If you pass in controller, you will have to
* look into that method, find out what it does and repeat for all other method calls 
* which accept controller as an argument. Note, this is just a part of Rule 0.
* 2. Keep Joystick, XboxController and others inside Controller class. This makes it easy to switch
* the actual controller without having to dig through a lot of code. If you want to switch controllers
* all you gotta do is change Controller class.
* 3. Prefix Controller boolean methods with should. For example
* 	public boolean shouldShoot(){
* 		//figures out whether it should shoot based on joy stick or xbox or something.
*   }
* 
* 
 */
public class Robot extends IterativeRobot {
	
	private Talon rightBack, rightFront, leftBack, leftFront;
	private Joystick joystick;	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		rightBack = new Talon(0);
		rightFront = new Talon(4);
		leftFront = new Talon(9);
		leftBack = new Talon(1);
		joystick = new Joystick(0);
	}
	

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	
	void setRF(double s) {
		rightFront.set(s);
	}
	
	void setRB(double s) {
		rightBack.set(s);
	}
	
	void setLF(double s) {
		leftFront.set(-s);
	}
	
	void setLB(double s) {
		leftBack.set(-s);
	}
	
	@Override
	public void teleopPeriodic() {
		double yValue = (double)9 / 20 * (joystick.getZ() + (double) 11 / 9) * joystick.getY();
		setRF(yValue);
		setRB(yValue);
		setLF(yValue);
		setLB(yValue);
		if(joystick.getRawButton(5)) {
			//left
			if(Math.abs(yValue) < 0.05) {
				setRF(0.2);
				setRB(0.2);
				setLF(-0.2);
				setLB(-0.2);
			} else {
				setRF(yValue + 0.2);
				setRB(yValue + 0.2);
				setLF(yValue);
				setLB(yValue);
			}
		} else if(joystick.getRawButton(4)) {
			//right
			if(Math.abs(yValue) < 0.05) {
				setRF(-0.2);
				setRB(-0.2);
				setLF(0.2);
				setLB(0.2);
			} else {
				setRF(yValue);
				setRB(yValue);
				setLF(yValue + 0.2);
				setLB(yValue + 0.2);
			}
		}
		
		/*if(joystick.getRawButton(5)) {	//shifting
			rightBack.set(yValue);
			rightFront.set(-yValue);
			leftBack.set(-yValue);
			leftFront.set(yValue);
		}*/
	}
	

	
	@Override
	public void testPeriodic() {
		
	}

	
	
	
	
}

