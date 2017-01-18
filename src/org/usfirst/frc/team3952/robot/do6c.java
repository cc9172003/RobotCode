package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;

public class do6c extends Command
{
	Talon linAct;
	//AnalogInput pot;
	boolean val;
	public do6c(Talon linActx)
	{
		linAct=linActx;
		//pot=potx;
	}
	

	public void moveLinAct(double dir){

	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		//val=false;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		linAct.set(0.5);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}