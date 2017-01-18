package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;


public class ServoC {
	Servo s;
	Joystick j;
	boolean canUse;
	public ServoC(Joystick jx,Servo sx)
	{
		s=sx;
		j=jx;
		canUse=true;
	}
	public void pCheck()
	{
		if(j.getTrigger()&&canUse)
		{
			s.set(0.1);
			canUse=false;
			
		}
	}
}
