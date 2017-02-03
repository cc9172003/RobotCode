/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author MJP т.и.к. Яков Борисович Зел'дович
 */
// This is now to be regarded as a reference of how we thought we would do it at first with a two-motor
// approach.  We are actually extending to a four-motor approach with lateral translation capability and
// servo-to-gyro-reference rotation capability.
public class RobotDriver
{
	private RobotDrive	rd,
						rd2;
	private Talon		m_pobjLeftFrontWheel,
						m_pobjRightFrontWheel,
						m_pobjLeftRearWheel,
						m_pobjRightRearWheel;
//	private double					m_dLinearSpeed,		// In robot units... -5.0 to 5.0 I believe.
//									m_dRotationalSpeed;	// Rotational speed... also in robot units.  Negative is anti-clockwise, positive is clockwise.
	// ---
	RobotDriver(Talon pobjLeftFrontWheel, Talon pobjRightFrontWheel, Talon pobjLeftRearWheel, Talon pobjRightRearWheel)
	{
		m_pobjLeftFrontWheel = pobjLeftFrontWheel;
		m_pobjRightFrontWheel = pobjRightFrontWheel;
		rd = new RobotDrive(m_pobjLeftFrontWheel,m_pobjRightFrontWheel);
		m_pobjLeftRearWheel = pobjLeftRearWheel;
		m_pobjRightRearWheel = pobjRightRearWheel;
		rd2 = new RobotDrive(m_pobjLeftRearWheel,m_pobjRightRearWheel);
	}
	// In 4 motor system where left controller x, y is to indicate fore/aft(y), lateral(x) and right controller indicates vector to servo to gyro angle.
	// Left front = (y - x) + c			Right front = (y - x) - c
	// Left rear  = (y + x) + c			Right rear  = (y + x) - c
	// ---
	// This function returns c for the above equations.  x and y are the right controller x, y, dGyroAngle is obtained from gyro and is in radians.
	private double ServoToGyroOffset(double x, double y, double dGyroAngle)
	{
		double	gx,
				gy,
				dDotProduct;

		// MJP:  Note the hysteresis... gives a 75% dead-zone so direction changes are guaranteed deliberate.
		// This assumes 1.0 is max value we are expecting from joystick.  If that turns out to be wrong,
		// scale the 0.75 accordingly... i.e. 0.75 * actual-extremity.
		if (x * x + y * y >= 0.75 * 0.75)
		{
			// Calculate normal to gyro angle.
			dGyroAngle += java.lang.Math.PI / 2.0;
			// Calculate x and y pointing to normal.
			gx = java.lang.Math.sin(dGyroAngle);
			gy = java.lang.Math.cos(dGyroAngle);
			dDotProduct = x * gx + y * gy;
			// Hysteresis:  sin(1 degree) sort of == 0.01.  If we are within 1 degree then do not servo... inhibits jitter.
			if (java.lang.Math.abs(dDotProduct) < 0.01)
			{
				return 0.0;
			}
			// If dot product with normal to gyro angle is negative then anticlockwise else clockwise.
			if (dDotProduct < 0.0)
			{
				return -0.2;
			}
			else
			{
				return 0.2;
			}
		}
		return 0.0;
	}
	void SetFromController(double leftx, double lefty, double rightx, double righty)//, double dGyroAngle)
	{
		double	dClockwise = 0.0, //dClockwise = ServoToGyroOffset(rightx, righty, dGyroAngle),
				dLeftFront = (lefty - leftx) + dClockwise,
				dRightFront = (-lefty - leftx) - dClockwise,
				dLeftRear = (lefty + leftx) + dClockwise,
				dRightRear = (-lefty + leftx) - dClockwise;
		
		m_pobjLeftFrontWheel.set(dLeftFront);
		m_pobjRightFrontWheel.set(dRightFront);
		m_pobjRightRearWheel.set(dRightRear);
		m_pobjLeftRearWheel.set(dLeftRear);
	}
//	void SetLinearSpeed(double dSpeed)
//	{
//		m_dLinearSpeed = dSpeed;
//		SetMotors();
//	}
//	void SetRotationalSpeed(double dSpeed)
//	{
//		m_dRotationalSpeed = dSpeed;
//		SetMotors();
//	}
//	private void SetMotors()
//	{
//		m_pobjLeftWheel.SetSpeed(m_dLinearSpeed - m_dRotationalSpeed);
//		m_pobjRightWheel.SetSpeed(m_dLinearSpeed + m_dRotationalSpeed);
//	}
}
