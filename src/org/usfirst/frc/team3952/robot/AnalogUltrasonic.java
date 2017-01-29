package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogUltrasonic {
	private AnalogInput sensor;
	//units cm/what ever sensor gives
	private static final double EMF_TO_CM = 107.14; //Determined Experimentally CHANGE IF HAS ISSUES
	
	
	public AnalogUltrasonic(int pin){
		sensor = new AnalogInput(pin);
	}
	
	
	public double getDistance(){
		return sensor.getVoltage() * EMF_TO_CM;
		
	}
}
