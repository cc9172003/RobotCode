package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;


public class ServoC {
	Servo s;
	Joystick j;
	boolean canUse;
	public ServoC(Joystick jx)
	{
		j=jx;
		canUse=true;
	}
	public void pCheck()
	{
		if(j.getRawButton(2)&&canUse)
		{
			s.set(0.5);
			canUse=false;
		}
	}
}
