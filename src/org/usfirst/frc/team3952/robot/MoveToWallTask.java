package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.XboxController;

public class MoveToWallTask implements Task {

	private AnalogUltrasonic right;
	private AnalogUltrasonic left;
	private double idealDist;
	private boolean finished = false;
	
	public MoveToWallTask(AnalogUltrasonic right, AnalogUltrasonic left, double idealDist) {
		this.right = right;
		this.left = left;
		this.idealDist = idealDist;
	}
	
	@Override
	public boolean performTask(RobotDriver driver) {
		
		if(finished) return true; //so we skip the calculations.
		
		double rightSpeed = Math.max(-Robot.MAX_SPEED * (right.getDistance()-idealDist), -Robot.MAX_SPEED);
		double leftSpeed = Math.max(-Robot.MAX_SPEED * (left.getDistance()-idealDist), -Robot.MAX_SPEED);
		
		if(rightSpeed <= 0.1 && leftSpeed <= 0.1){
			finished = true; return true;
		}
		
		driver.setMotorsDirectly(leftSpeed, rightSpeed, leftSpeed, rightSpeed);
		return false;
	}

	@Override
	public void cancel() {
		finished = true; //just in case they don't clean up. 
	}

}
