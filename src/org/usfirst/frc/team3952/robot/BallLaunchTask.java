package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;

public class BallLaunchTask implements Task{
	
	private Talon gateMotor;
	private Talon launcher;
	
	private XboxController controller;
	
	private static final double LAUNCHER_SPEED = 0.5, GATE_MOTOR = 0.08;
	private static final long WARM_UP_TIME = 500; //0.5second
	private static final long COOL_DOWN_TIME = 3000; // 3 second

	private boolean coolingDown;
	private long timeCoolDownBegan;
	private long timeWarmUpBegan;
	
	public BallLaunchTask(Talon gateMotor, Talon launcher, XboxController controller){
		this.gateMotor = gateMotor;
		this.launcher = launcher;
		coolingDown = true;
		timeCoolDownBegan = 0; 
		timeWarmUpBegan = 0; 
		this.controller = controller;
	}
	
	
	/**
	 * 
	 * @return true if done, false if not done
	 */
	public boolean performTask(RobotDriver driver){
		
		//setting variables based on controller input.
		//System.out.println(System.currentTimeMillis() + ": "+ timeWarmUpBegan);
		if(!controller.getXButton()){
			coolingDown = true;
			if(timeCoolDownBegan == 0)
				timeCoolDownBegan = System.currentTimeMillis();
			timeWarmUpBegan = 0;
		} else{
			coolingDown = false; //means we are now warming up
			timeCoolDownBegan = 0;
			if(timeWarmUpBegan == 0){
				timeWarmUpBegan = System.currentTimeMillis();
			}
		}
		
		
		
		if(coolingDown){
			gateMotor.set(0);
			if(System.currentTimeMillis() > COOL_DOWN_TIME + timeCoolDownBegan){
				launcher.set(0);
			}
			
		} else if(System.currentTimeMillis() < WARM_UP_TIME + timeWarmUpBegan){
			//warm up launch motor
			launcher.set(LAUNCHER_SPEED); //adjust this constant for
			gateMotor.set(0);
		} else {
			gateMotor.set(GATE_MOTOR);
			launcher.set(LAUNCHER_SPEED);
		}
		
		return false; //never done :) but doesn't matter since its special.
	}
	
	/**
	 * forces task to stop
	 */
	public void cancel(){
		coolingDown = true;
		timeCoolDownBegan = System.currentTimeMillis();
	}
}
