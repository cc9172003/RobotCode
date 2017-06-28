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

	
	public void setFromController(double horizontal, double lateral, boolean left, boolean right)
	{
		//clean input
		horizontal = -MAX_SPEED * clean(horizontal);
		lateral = -MAX_SPEED * clean(lateral);
		
		double dLeftFront = (lateral - horizontal),
				dRightFront = (-lateral - horizontal),
				dLeftRear = (lateral + horizontal) ,
				dRightRear = (-lateral + horizontal);
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
