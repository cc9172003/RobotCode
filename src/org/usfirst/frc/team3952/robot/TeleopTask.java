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
		double hor = controller.getHorizontalMovement(), lat = controller.getLateralMovement();
		drive.driveCartesian(hor, 
							 lat, 
							 controller.getRotation()
							 );
		
		SmartDashboard.putString("Horizontal Movement", "" + hor);
		SmartDashboard.putString("Lateral Movement", "" + lat);
		//mecanumwheels.setFromController(controller.getLateralMovement(), etc) if ^ fails
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