package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class WindshieldMotor {

	Talon t;
	Talon t2;
	int channel;
	int channel2;
	Joystick j;
	JoystickButton b3,b4;
	public WindshieldMotor(int channelx, int channel2x,Joystick jx)
	{
		channel=channelx;
		channel2=channel2x;
		t=new Talon(channel);
		t2=new Talon(channel2);
		j=jx;
		b3=new JoystickButton(j,3);
		b4=new JoystickButton(j,4);
		
		b3.whileHeld(new do3c(t,t2));
		b4.whileHeld(new do4c(t,t2));
	}
	
}





//package org.usfirst.frc.team3952.robot;
//
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Talon;
//
//public class WindshieldMotor {
//
//	Talon t;
//	Talon t2;
//	int channel;
//	int channel2;
//	Joystick j;
//	public WindshieldMotor(int channelx, int channel2x,Joystick jx)
//	{
//		channel=channelx;
//		channel2=channel2x;
//		t=new Talon(channel);
//		t2=new Talon(channel2);
//		j=jx;
//	}
//	public void pressCheck()
//	{
//		if(j.getRawButton(3))//forward
//		{
//			moveMotor(0.5,0.5);
//		}
//		else if(j.getRawButton(4))//back
//		{
//			moveMotor(-0.5,-0.5);
//		}
//	}
//	public void moveMotor(double dir,double dir2){//positive dir extends
//		if(t.get()!=0.0)
//			t.set(0.0);
//		else
//			t.set(dir);
//		
//		if(t2.get()!=0.0)
//			t2.set(0.0);
//		else
//			t2.set(dir2);
//	}
//}
//
