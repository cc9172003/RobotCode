package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;

public class do7c extends Command{

	Talon winch;
	
	//boolean val;
	public do7c(Talon winchx)
	{
		winch=winchx;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		//val=false;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
//		if(winch.get()!=0.0)
//			winch.set(0.0);
//		else
//			winch.set(-0.5);
//		val=true;
		winch.set(-0.5);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//return val;
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
