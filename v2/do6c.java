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
	
//	public boolean atMax()
//	{
//		return pot.getValue()>=4.5;
//	}
//
//	public boolean atMin()
//	{
//		return (pot.getValue()<=0.5);
//		
//	}
//	public void setRate(double inputRate){
//		armRate = inputRate;
//	}

	public void moveLinAct(double dir){
//		linAct.set(dir);
		if(dir==0.0)
			linAct.set(0.0);
		else if(dir>0.0)
		{
//			if(atMax())
//				linAct.set(0.0);
//			else
				linAct.set(dir);
		}
		else{
//			if(atMin())
//				linAct.set(0.0);
//			else
				linAct.set(dir);
		}
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		val=false;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(linAct.get()!=0.0)
			moveLinAct(0.0);
		else
			moveLinAct(0.5);
		val=true;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return val;
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