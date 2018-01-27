package org.usfirst.frc.team3952.robot;
import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogUltrasonic {
	private AnalogInput sensor;
	private CircularQueue cq;
	//units cm/what ever sensor gives
	private static final double EMF_TO_CM = 107.14; //Determined Experimentally CHANGE IF HAS ISSUES
	
	
	public AnalogUltrasonic(int pin){
		sensor = new AnalogInput(pin);
		new UpdateSensorDataThread().start();	
	}
	
	
	public double getDistance(){
		return cq.average();
	}
	
	private class UpdateSensorDataThread extends Thread{
		
		@Override
		public void run(){
			try{
				cq.add(sensor.getVoltage()* EMF_TO_CM);
				Thread.sleep(100); //update every .1 seconds
			} catch(InterruptedException e){
				System.out.println("Update Ultrasonic Sensor Thread died");
			}
			
		}
	
	}
	
	private class CircularQueue{
		private double[] values;
		private int index = 0;
		private boolean filled = false;
		
		public CircularQueue(int size){
			values = new double[size];
		}
		
		public synchronized void add(double value){
			
			values[index] = value;
			
			index = (index + 1);
			if(index > values.length){
				filled = true;
				index %= values.length;
			}
		}
		
		public synchronized double average(){
			double av = 0.0;
			int max = (filled? values.length:index+1);
			
			for(int i = 0; i < max; i++){
				av += values[i];
			}
			return av/max;
		}
	}	
}