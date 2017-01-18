package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class LinearWinch{
	
	boolean isFront;
	boolean isBack;
	
	Joystick jStick;
	
	Talon armController;
	
	Encoder encoder;
	DigitalInput back;
	DigitalInput front;
	DashBoard board;

	
	public LinearWinch(DashBoard inBoard){
		board = inBoard;
		front = new DigitalInput(3);
		back = new DigitalInput(2);
		isFront = false;
		isBack = false;
		jStick = new Joystick(2);
		armController = new Talon(2);
		encoder = new Encoder(1,0,true, EncodingType.k2X);	
		encoder.setDistancePerPulse(2);
	}
	
	public boolean isFront(){
		return front.get();
	}
	
	public boolean isBack(){
		return !back.get();
	}
	
	public void moveArm(double dir){ //dir > 0 forward
									//dir < 0 back
//		if(isBottom() && dir > 0){
//			armController1.set(0);
//			armController2.set(0);
//		}
//		else if(isTop() && dir < 0){
//			armController1.set(0);
//			armController2.set(0);
//		}
//		else{
//			armController1.set(dir);
//			armController2.set(dir);
//		}
		if(isBack()&&dir<0)
		{
			armController.set(0);
		}
		else if(isFront()&&dir>0)
		{
			armController.set(0);
		}
		else
		{
			armController.set(dir);
		}
	}
	
	public void stop(){
//		armController1.set(0);
//		armController2.set(0);
		armController.set(0);
	}
	
} 