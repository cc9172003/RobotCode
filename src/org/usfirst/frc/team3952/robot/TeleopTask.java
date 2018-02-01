package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;


public class TeleopTask extends Task {
	private Controller controller;
	private MecanumDrive drive;
	private ADXRS450_Gyro gyro;
	
	public TeleopTask(Robot robot) {
		controller = robot.getController();
		drive = robot.getDrive();
		gyro = robot.getGyro();
	}
	
	@Override
	public boolean run() {
		drive.driveCartesian(controller.getHorizontalMovement(), 
							 controller.getLateralMovement(), 
							 controller.getRotation(),
							 gyro.getAngle()
							 );
		//mecanumwheels.setFromController(controller.getLateralMovement(), etc) if ^ fails
		return false;
	}
	
	@Override
	public void cancel() {
		drive.driveCartesian(0, 0, 0);		// needed?
	}
	
	@Override
	public String toString() {
		return "Teleop";
	}
}