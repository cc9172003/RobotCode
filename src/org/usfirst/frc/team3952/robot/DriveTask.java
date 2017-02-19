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
	
	
	
	@Override
	public boolean performTask(RobotDriver driver) {
		double y = controller.getY(Hand.kLeft);
		double x = controller.getX(Hand.kLeft); //this used to be the Y of the right hand but i think that was wrong.

		
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
