package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class TurnTask extends Task {
	
	private ADXRS450_Gyro gyro;
	private MecanumDrive drive;
	private double startingAngle;
	private double degrees;
	
	/**
	 * Constructs a Turn Task task to turn degrees degrees
	 * @param robot the robot
	 * @param degrees negative = left, + = right
	 */
	public TurnTask(Robot robot, double degrees){
		drive = robot.getDrive();
		gyro = robot.getGyro();
		startingAngle = gyro.getAngle() % 360;
		this.degrees = degrees;
	}
	
	public boolean run(){
		
		if(differenceAngle(gyro.getAngle(), startingAngle+degrees) < 0.5){
			drive.driveCartesian(0, 0, 0);
			return true; //should die after this
			
		}
		
		if(degrees < 0){
			drive.driveCartesian(0, 0, -0.5);
		} else if (degrees > 0){
			drive.driveCartesian(0,0, 0.5);
		} 
		return false;
	}
	
	public void cancel(){
		drive.driveCartesian(0, 0, 0);
	}
	
	public String toString(){
		return "TurnTask";
	}
	
	
	private double differenceAngle(double a1, double a2){
		return Math.abs(a1 % 360 - a2 % 360);
	}
}
