package org.usfirst.frc.team3952.robot;

public class TelopTask implements Task{

	private MechanumWheels mechWheels;
	private Controller controller;
	private Shooter shooter;
	
	public TelopTask(Robot r){
		mechWheels = r.getMechWheels();
		controller = r.getController();
		shooter = r.getShooter();
	}

	public boolean performTask(){
		shooter.reset(); //sets them all to zero so they don't keep spinning.
		
		//driving
		mechWheels.setFromController(
				controller.horizontalMovement(), 
				controller.lateralMovement(), 
				controller.shouldTurnRight(),
				controller.shouldTurnLeft()
		);
		
		//shooting
		if(controller.shouldShoot())
    		shooter.shoot();
    	
		if(controller.shouldAgitateRandom())
			shooter.agitateRandom();
		else if(controller.shouldAgitateBackwards())
    		shooter.agitateBackwards();
		else if(controller.shouldAgitateForwards())
    		shooter.agitateForwards();
    	return false; //its never done
	}
	public void cancel(){
		//do nothing.
	}
	
	public String toString(){
		return "Telop";
	}
}