package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

public class Robot extends IterativeRobot {
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	private static final double ROTATION_SPEED = 0.2;
	private static final double LERP = 0.1;
	
	private Talon frontRight, frontLeft, rearRight, rearLeft;
	private Joystick joystick;
	private MecanumDrive drive;

	private double xVel;		//x velocity
	private double yVel;		//y velocity
	private double aVel;		//angular velocity
	
	@Override
	public void robotInit() {
		frontRight = new Talon(0);	//TODO: change parameter to correct pin
		frontLeft = new Talon(0);
		rearRight = new Talon(0);
		rearLeft = new Talon(0);
		joystick = new Joystick(0);
		drive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
		
		xVel = 0;
		yVel = 0;
		aVel = 0;
	}
	
	@Override
	public void teleopPeriodic() {
		xVel = lerpVel(xVel, joystick.getX());
		yVel = lerpVel(yVel, joystick.getY());
		aVel = lerpVel(aVel, getRotation());
		drive.driveCartesian(yVel, xVel, aVel);
	}
	
	private double getRotation() {
		double rotation = ROTATION_SPEED * ((joystick.getRawButton(RIGHT)) ? 1 : 0 - (joystick.getRawButton(LEFT) ? 1 : 0));
	}
	
	private double lerpVel(double x, double destination) {
		return constraint(x + (destination - x) * LERP, -1, 1);
	}
	
	private double constraint(double x, double min, double max) {
		return Math.min(Math.max(x, min), max);
	}
}
