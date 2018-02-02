package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;


/**
 * Status: Untested
 * 
 *
 */
public class MoveForwardTask extends Task {
	private MecanumDrive drive;
	private Encoder leftEncoder, rightEncoder;
	private double initialDistance;
	private double distance;
	
	private boolean started = false;
	
	public MoveForwardTask(Robot robot, double distance) {
		drive = robot.getDrive();
		leftEncoder = robot.getLeftEncoder();
		rightEncoder = robot.getRightEncoder();
		this.distance = distance;
	}
	
	@Override
	public boolean run() {
		if(!started){
			initialDistance = (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
			started = true;
		}
		
		double currentDistance = (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
		if(currentDistance >= initialDistance + distance - 0.1) {
			drive.driveCartesian(0, 0, 0);
			return true;
		} 
	
		drive.driveCartesian(0, 0.3, 0);		// set to a reasonable value
		return false;
	
	}
	
	@Override
	public void cancel() {
		drive.driveCartesian(0, 0, 0);		// needed? needed
	}
	
	@Override
	public String toString() {
		double currentDistance = (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
		return "Move Forward: " + (int)distance + " feet(" + (int) (currentDistance - initialDistance) + " feet left)";
	}
}