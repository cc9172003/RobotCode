package org.usfirst.frc.team3952.robot;

public class NullTask extends Task {

	@Override
	public boolean run() {
		return false;
	}

	@Override
	public void cancel() {

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Null Task";
	}

}
