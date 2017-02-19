package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;

public class ClimbRopeTask implements Task{

	private Talon motor;
	private XboxController controller;
	
	public ClimbRopeTask(Talon motor, XboxController controller) {
		this.motor = motor;
		this.controller = controller;
	}
	
	@Override
	public boolean performTask(RobotDriver driver) {

		double upValue = controller.getTriggerAxis(Hand.kRight);
		double downValue = controller.getTriggerAxis(Hand.kLeft);
		if(upValue > 0.05 && downValue > 0.05){
			motor.set(0);
		} else if(upValue > 0.05){
			motor.set(0.7*upValue);
		} else{
			motor.set(-0.7*downValue);
		}
		
		return false;
	}

	@Override
	public void cancel() {
		; //nothing happens yay.
	}
	
}
