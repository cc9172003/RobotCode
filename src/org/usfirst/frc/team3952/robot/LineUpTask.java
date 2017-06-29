package org.usfirst.frc.team3952.robot;

public class LineUpTask implements Task {

	private MechanumWheels mechWheels;
	private AnalogUltrasonic analogUltrasonic;
	
	public LineUpTask(Robot r){
		mechWheels = r.getMechWheels();
		analogUltrasonic = r.getAnalogUltrasonic();
	}
	
	
	@Override
	public boolean performTask() {
		
		if(analogUltrasonic.getDistance() > 30){
			mechWheels.setFromController(0, 0.3, false, false);
			return false;
		} else{
			mechWheels.setFromController(0, 0, false, false);
			return true;
		}
		
	}

	@Override
	public void cancel() {
		mechWheels.setFromController(0, 0, false, false);
	}

}
