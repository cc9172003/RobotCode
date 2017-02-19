package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.XboxController;

public class DriveTask implements Task{

	private XboxController controller;
	
	public DriveTask(XboxController controller) {
		this.controller = controller;
	}
	
	private class HI implements PIDSource{

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	
	@Override
	public boolean performTask(RobotDriver driver) {
		double y = controller.getY(Hand.kLeft);
		double x = controller.getY(Hand.kRight);

		
		if(controller.getPOV() == 0){
			//PIDController pid = new 
		}
		
		if(small(x) && small(y)) {
			;
		}else{
			driver.SetFromController(-Robot.MAX_SPEED*x, -Robot.MAX_SPEED*y, 0.0, 0.0);
		}
		return true; //its always done.
	}
	

	public boolean small(double x)
	{
		return Math.abs(x)<0.05;
	}

	@Override
	public void cancel() {
		; //do nothing bc we are cool.
	}

}
