package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogUltrasonic {
	private AnalogInput sensor;
	private static final double VOLTAGE_TO_METERS = 1;
	
	
	public AnalogUltrasonic(int pin){
		sensor = new AnalogInput(pin);
	}
	
	
	public double getDistance(){
		return sensor.getVoltage() * VOLTAGE_TO_METERS;
		
	}
}
