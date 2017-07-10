package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class MechanumWheels {
	
	private Talon m_pobjLeftFrontWheel, m_pobjRightFrontWheel, m_pobjLeftRearWheel, m_pobjRightRearWheel;
	
	public static final double MAX_SPEED = 0.5, MAX_TURN = 0.3;
	
	
	public MechanumWheels(Talon pobjLeftFrontWheel, Talon pobjRightFrontWheel, Talon pobjLeftRearWheel, Talon pobjRightRearWheel)
	{
		m_pobjLeftFrontWheel = pobjLeftFrontWheel;
		m_pobjRightFrontWheel = pobjRightFrontWheel;
		m_pobjLeftRearWheel = pobjLeftRearWheel;
		m_pobjRightRearWheel = pobjRightRearWheel;
	}

	
	public void setFromController(double horizontal, double lateral, double turnValue)
	{
		//clean input
		horizontal = -MAX_SPEED * clean(horizontal);
		lateral = -MAX_SPEED * clean(lateral);
		turnValue = MAX_TURN * clean(turnValue);
		
		double dLeftFront = (lateral - horizontal),
				dRightFront = (-lateral - horizontal),
				dLeftRear = (lateral + horizontal) ,
				dRightRear = (-lateral + horizontal);
		
		dLeftFront -= turnValue;
		dLeftRear -= turnValue;
		dRightFront -= turnValue;
		dRightRear -= turnValue;
		

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
