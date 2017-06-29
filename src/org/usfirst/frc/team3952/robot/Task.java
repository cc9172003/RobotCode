package org.usfirst.frc.team3952.robot;

public interface Task {
	
	/**
	 * 
	 * @return true if done, false if not done
	 */
	public boolean performTask();
	
	/**
	 * forces task to stop
	 */
	public void cancel();
	
}
