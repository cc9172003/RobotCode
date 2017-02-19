package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogUltrasonic {
	private AnalogInput sensor;
	//units cm/what ever sensor gives
	private double emf_to_cm = 107.14; //Determined Experimentally CHANGE IF HAS ISSUES
	
	
	public AnalogUltrasonic(int pin, double emf_to_cm){
		this.emf_to_cm = emf_to_cm;
		sensor = new AnalogInput(pin);
	}
	
	
	public double getDistance(){
		return sensor.getVoltage() * emf_to_cm;
		
	}
}
