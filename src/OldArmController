package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class ArmController{
	double chainPitch;
	int maxDistance;
	double heightOfTrashbin;
	double heightOfTote = 12.1; // inches
	boolean isTop;
	boolean isBottom;
	double sprocketDia;
	double armRate;
	Joystick jStick;
	int gearNum;
	Talon armController1;
	Talon armController2;
	Encoder encoder;
	DigitalInput bottom;
	DigitalInput top;
	DashBoard board;

	
	public ArmController(DashBoard inBoard){
		armRate = 1.0;
		board = inBoard;
		bottom = new DigitalInput(3);
		top = new DigitalInput(2);
		chainPitch = .375;
		maxDistance = 2222;
		heightOfTrashbin = 29;
		heightOfTote = 2222/3.25;
		isTop = false;
		isBottom = false;
		sprocketDia = 2.1;
		jStick = new Joystick(2);
		gearNum = 15;		
		armController1 = new Talon(2);
		armController2 = new Talon(3);
		encoder = new Encoder(1,0,true, EncodingType.k2X);	
		encoder.setDistancePerPulse(2);
	}
	
	public boolean isTop(){
		return top.get();
	}
	
	public boolean isBottom(){
		return !bottom.get();
	}
	
	public void setRate(double inputRate){
		armRate = inputRate;
	}
	
	public void moveArm(double dir){
		if(isBottom() && dir > 0){
			armController1.set(0);
			armController2.set(0);
		}
		else if(isTop() && dir < 0){
			armController1.set(0);
			armController2.set(0);
		}
		else{
			armController1.set(dir);
			armController2.set(dir);
		}
	}
	
	public void stop(){
		armController1.set(0);
		armController2.set(0);
	}
	
	public double measureTheHeight(){
		while(!isTop()){
			moveArm(-armRate);			
			}		
		encoder.reset();
		while(!isBottom()){
			moveArm(armRate);
		}		
		moveArm(0);
		return -encoder.getDistance();
	}	
	
	public void moveUpOneTote(){
		encoder.reset();
		while(encoder.getDistance() < heightOfTote){
			moveArm(-armRate);
		}		
	}
	
	public void moveToBottom(){
		while(!isBottom()){
			moveArm(armRate);
		}
	}
	
	public void moveToTop(){
		while(!isTop()){
			moveArm(-armRate);
		}
	}
	
	public void startArm(){
		moveArm(jStick.getY()*armRate);		
	}
	
} 