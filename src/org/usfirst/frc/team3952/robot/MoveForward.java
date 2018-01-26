package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

public class MoveForward extends Task {
	MecanumDrive drive;
	Encoder leftEncoder, rightEncoder;
	double totalDistance, remainingDistance;
	
	public MoveForward(Robot robot, double distance) {
		drive = robot.getDrive();
		leftEncoder = robot.getEncoder(Robot.LEFT);
		rightEncoder = robot.getEncoder(Robot.RIGHT);
		totalDistance = distance;
		remainingDistance = distance;
	}
	
	@Override
	public boolean run() {
		if(remainingDistance < 0) {
			return true;
		} else {
			drive.driveCartesian(1, 0, 0);		// set to a reasonable value
			remainingDistance -= (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;		// get average of two encoders for accuracy
			return false;
		}
	}
	
	@Override
	public void cancel() {
		drive.driveCartesian(0, 0, 0);		// needed?
	}
	
	@Override
	public String toString() {
		return "Move Forward " + (int)totalDistance + " feet(" + (int)remainingDistance + " feet left)";
	}
}