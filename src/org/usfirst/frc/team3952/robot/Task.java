package org.usfirst.frc.team3952.robot;
import edu.wpi.first.wpilibj.XboxController;

public interface Task {
	
	/**
	 * 
	 * @return true if done, false if not done
	 */
	public boolean performTask(Robot robot);
	
	/**
	 * forces task to stop
	 */
	public void cancel();
	
}
