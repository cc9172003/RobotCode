package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Talon;

public class Shooter {
	public static final double AGITATE_SPEED = 0.3;
	private Talon shooter, agitator;
	
	public Shooter(Talon shooter, Talon agitator){
		this.shooter = shooter;
		this.agitator = agitator;
	}
	
	public void shoot(){
		shooter.set(1);
	}
	
	public void agitateForwards(){
		agitator.set(AGITATE_SPEED);
	}
	
	public void agitateBackwards(){
		agitator.set(-AGITATE_SPEED);
	}
	
	public void agitateRandom(){
		agitator.set( 0.8 * AGITATE_SPEED * Math.sin(10*System.currentTimeMillis()/1000.0));
	}
	
	
	
	public void reset(){
		shooter.set(0);
		agitator.set(0);
	}
}
