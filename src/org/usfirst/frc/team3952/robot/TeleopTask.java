package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TeleopTask extends Task {
	Robot robot;				// delete this
	private Controller controller;
	private MechanumWheels drive;
	private ADXRS450_Gyro gyro;
	
	public TeleopTask(Robot robot) {
		controller = robot.getController();
		drive = robot.getDrive();
		gyro = robot.getGyro();
		
		this.robot  = robot;
	}
	
	@Override
	public boolean run() {
		
		drive.setFromController(controller.getHorizontalMovement(), controller.getLateralMovement(), controller.getRotation());
//		//mecanumwheels.setFromController(controller.getLateralMovement(), etc) if ^ fails

//		robot.frontLeft.set(-0.2);
//		robot.rearLeft.set(-0.2);
//		robot.frontRight.set(-0.2);
//		robot.rearRight.set(-0.2);
//		
		//drive.driveCartesian(0,  0.2,  0);
		
		SmartDashboard.putString("Front Left Talon", "" + robot.frontLeft.get());
		SmartDashboard.putString("Rear Left Talon", "" + robot.rearLeft.get());
		SmartDashboard.putString("Front Right Talon", "" + robot.frontRight.get());
		SmartDashboard.putString("Rear Right Talon", "" + robot.rearRight.get());
		SmartDashboard.putString("getHorizontalMov", "" + controller.getHorizontalMovement());
		SmartDashboard.putString("Laterla", "" + controller.getLateralMovement());
		
		return false;
	}
	
	@Override
	public void cancel() {
		drive.setFromController(0, 0, 0);		// needed?
	}
	
	@Override
	public String toString() {
		return "Teleop";
	}
}