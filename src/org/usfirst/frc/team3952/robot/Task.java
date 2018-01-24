package org.usfirst.frc.team3952.robot;

public abstract class Task {
	public abstract boolean run();
	
	// for safety measures(e.g. set talons to 0)
	public abstract void cancel();
	
	public abstract String toString();
}