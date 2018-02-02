package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class MechanumWheels {
	
	private Talon m_pobjLeftFrontWheel, m_pobjRightFrontWheel, m_pobjLeftRearWheel, m_pobjRightRearWheel;
	
	public static final double MAX_SPEED = 1;
	
	
	public MechanumWheels(Talon pobjLeftFrontWheel, Talon pobjRightFrontWheel, Talon pobjLeftRearWheel, Talon pobjRightRearWheel)
	{
		m_pobjLeftFrontWheel = pobjLeftFrontWheel;
		m_pobjRightFrontWheel = pobjRightFrontWheel;
		m_pobjLeftRearWheel = pobjLeftRearWheel;
		m_pobjRightRearWheel = pobjRightRearWheel;
	}

	
	public void setFromController(double horizontal, double lateral, double rot)
	{
		//clean input
		horizontal = MAX_SPEED * clean(horizontal);
		lateral = MAX_SPEED * cleanLateral(lateral);
		
		double dLeftFront = (lateral + horizontal),
				dRightFront = (lateral - horizontal),
				dLeftRear = (lateral - horizontal) ,
				dRightRear = (lateral + horizontal);
		
		//is it -= or +=?
		dLeftFront += rot;
		dLeftRear += rot;
		dRightFront -= rot;
		dRightRear -= rot;
		

		m_pobjLeftFrontWheel.set(clean2(dLeftFront));
		m_pobjRightFrontWheel.set(clean2(dRightFront));
		m_pobjRightRearWheel.set(clean2(dRightRear));
		m_pobjLeftRearWheel.set(clean2(dLeftRear));
		
	}
	
	
	//UTIL
	public static double clean(double x){
		if (Math.abs(x) < 0.2) return 0;
		if (x > 1) return 1;
		if (x < -1) return -1;
		return x;
	}

	public static double clean2(double x){
		if (Math.abs(x) < 0.02) return 0;
		if (x > 1) return 1;
		if (x < -1) return -1;
		return x;
	}

	public static double cleanLateral(double x){
		if (Math.abs(x) < 0.2) return 0;
		if (x > 1) return 1;
		if (x < -1) return -1;
		return x;
	}
}
