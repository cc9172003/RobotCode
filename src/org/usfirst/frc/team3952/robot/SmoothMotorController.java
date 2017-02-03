/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Talon;
//import java.lang.*;
//import edu.wpi.first.wpilibj;

/**
 *
 * @author MJP
 */
// Dummy object... replace with real motor controller object.
//class Motor extends edu.wpi.first.wpilibj.Talon
//{
//	public Motor(int nMotorIndex)
//	{
//		super(nMotorIndex);
//	}
//	public void SetSpeed(double d)
//	{
//		System.out.println("Motor speed " + d);
//	}
//};

public class SmoothMotorController extends Talon implements Runnable
{
	double	m_dDesiredPosition,
			m_dCurrentPosition,
			m_dSpringAcceleration,
			m_dDampingAcceleration,
			m_dVelocity,
			m_dSpringCoefficient,
			m_dDampingCoefficient,
			m_dPastValue;
	boolean	m_bKeepRunning;
	Thread	m_objThread;
	
	SmoothMotorController(int nMotorIndex)
	{
		super(nMotorIndex);
		m_dDesiredPosition =
		m_dCurrentPosition =
		m_dSpringAcceleration =
		m_dDampingAcceleration =
		m_dVelocity = 0.0;
		m_dSpringCoefficient = 4.0 / 15.0;
		m_dDampingCoefficient = 4.0 / 15.0;
		m_bKeepRunning = true;
		m_dPastValue = m_dCurrentPosition;
		m_objThread = new Thread(this);
		m_objThread.start();
	}
	@Override
	public void finalize()
	{
		// The IDE recommended this finalize then complained when I added it.
		// So I am leaving it in commented out to document that.  The code
		// has been verified to work without it.
		//super.finalize();
		m_bKeepRunning = false;
	}
	@Override
	public synchronized void set(double dSpeed)
	{
		//one way of testing is super.set so it goes through talon. also comment other super set.
		// TODO:  Verify that this is getting called by RobotDrive object.
		m_dDesiredPosition = dSpeed;
		super.set(m_dCurrentPosition);
	}
	
	public void run()
	{
		while (m_bKeepRunning)
		{
			synchronized(this)
			{
				try
				{
					Thread.sleep(50);
				}
				catch (InterruptedException e)
				{
				}
				
				m_dSpringAcceleration = (m_dDesiredPosition - m_dCurrentPosition) * m_dSpringCoefficient;
				m_dDampingAcceleration = -m_dVelocity * m_dDampingCoefficient;
				m_dVelocity += m_dSpringAcceleration + m_dDampingAcceleration;
				m_dCurrentPosition += m_dVelocity;
				if (m_dCurrentPosition > 1.0)
				{
					m_dCurrentPosition = 1.0;
				}
				else if (m_dCurrentPosition < -1.0)
				{
					m_dCurrentPosition = -1.0;
				}
//				double newValue = m_dCurrentPosition / 3.0;
//				if (m_dPastValue != newValue)
//				{
//					super.set(newValue);
//				}
//				m_dPastValue = newValue;
			}
		}
	}
};
