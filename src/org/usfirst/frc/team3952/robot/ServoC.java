package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;


public class ServoC {
	Servo s;
	Joystick j;
	Joystick j2;
	boolean canUse;
	public ServoC(Joystick jx,Joystick jx2,Servo sx)
	{
		s=sx;
		j=jx;
		j2 =jx;
		canUse=true;
	}
	public void pCheck()
	{
		if(j.getTrigger()&&j2.getTrigger()&&canUse)
		{
			s.set(0.1);
			canUse=false;
			
		}
	}
}
