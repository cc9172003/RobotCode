package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class WindshieldMotor {

	Talon t;
	int channel;
	Joystick j;
	public WindshieldMotor(int channelx, Joystick jx)
	{
		channel=channelx;
		t=new Talon(channel);
		j=jx;
	}
	public void pressCheck()
	{
		if(j.getRawButton(3))//forward
		{
			moveMotor(0.2);
		}
		else if(j.getRawButton(4))//back
		{
			moveMotor(-0.2);
		}
	}
	public void moveMotor(double dir){//positive dir extends
		if(t.get()!=0.0)
			t.set(0.0);
		else
			t.set(dir);
	}
}
