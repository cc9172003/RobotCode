package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class MechanumWheels {
	
	private Talon m_pobjLeftFrontWheel, m_pobjRightFrontWheel, m_pobjLeftRearWheel, m_pobjRightRearWheel;
	
	public static final double MAX_SPEED = 0.5;
	
	
	public MechanumWheels(Talon pobjLeftFrontWheel, Talon pobjRightFrontWheel, Talon pobjLeftRearWheel, Talon pobjRightRearWheel)
	{
		m_pobjLeftFrontWheel = pobjLeftFrontWheel;
		m_pobjRightFrontWheel = pobjRightFrontWheel;
		m_pobjLeftRearWheel = pobjLeftRearWheel;
		m_pobjRightRearWheel = pobjRightRearWheel;
	}
	// In 4 motor system where left controller x, y is to indicate fore/aft(y), lateral(x) and right controller indicates vector to servo to gyro angle.
	// Left front = (y - x) + c			Right front = (y - x) - c
	// Left rear  = (y + x) + c			Right rear  = (y + x) - c
	// ---
	// This function returns c for the above equations.  x and y are the right controller x, y, dGyroAngle is obtained from gyro and is in radians.
	public void setFromController(double leftx, double lefty, boolean left, boolean right)
	{
		double dLeftFront = (lefty - leftx),
				dRightFront = (-lefty - leftx),
				dLeftRear = (lefty + leftx) ,
				dRightRear = (-lefty + leftx);
		if(left){
			dLeftFront += 0.2;
			dLeftRear += 0.2;
			dRightFront += 0.2;
			dRightRear += 0.2;
		} else if(right){
			dLeftFront -=0.2;
			dLeftRear -=0.2;
			dRightFront -= 0.2;
			dRightRear -= 0.2;
		}

		m_pobjLeftFrontWheel.set(clean(dLeftFront));
		m_pobjRightFrontWheel.set(clean(dRightFront));
		m_pobjRightRearWheel.set(clean(dRightRear));
		m_pobjLeftRearWheel.set(clean(dLeftRear));
	}
	
	
	//UTIL
	public static double clean(double x){
		if (Math.abs(x) < 0.05) return 0;
		if (Math.abs(x) >= 1) return 1.0;
		return x;
	}

}
