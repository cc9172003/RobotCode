///*
//* To change this template, choose Tools | Templates
//* and open the template in the editor.
//*/
//package org.usfirst.frc.team3952.robot;
//import java.lang.*;
//
//import edu.wpi.first.wpilibj.Talon;
//
///**
//*
//* @author Administrator
//*/
//// Dummy object... replace with real motor controller object.
//
//
//public class SmoothMotorController implements Runnable
//{
//   double    m_dDesiredPosition,
//           m_dCurrentPosition,
//           m_dSpringAcceleration,
//           m_dDampingAcceleration,
//           m_dVelocity;
//   boolean    m_bKeepRunning;
//   Talon    t1;
//   Talon 	t2;
//   Thread    m_objThread;
//   // ---
//   //SmoothMotorController(Motor objMotor)
//   SmoothMotorController()
//   {
//       m_dDesiredPosition =
//       m_dCurrentPosition =
//       m_dSpringAcceleration =
//       m_dDampingAcceleration =
//       m_dVelocity = 0.0;
//       m_bKeepRunning = true;
//       //m_objMotor = objMotor;
//       t1 = new Talon(0);    // Replace this with line preceeding 
//       t2=new Talon(1);
////it and uncomment constructor line that takes motor (actual motor) object.
//       m_objThread = new Thread(this);
//       m_objThread.start();
//   }
//   public void finalize()
//   {
//       m_bKeepRunning = false;
//   }
//   public synchronized void SetSpeed(double dSpeed)
//   {
//       m_dDesiredPosition = dSpeed;
//   }
//   public void run()
//   {
//       while (m_bKeepRunning)
//       {
//           synchronized(this)
//           {
//               try
//               {
//                   Thread.sleep(50);
//               }
//               catch (InterruptedException e)
//               {
//               }
//               m_dSpringAcceleration = (m_dDesiredPosition - 
//m_dCurrentPosition) / 15.0;
//               m_dDampingAcceleration = -m_dVelocity / 15.0;
//               m_dVelocity += m_dSpringAcceleration + 
//m_dDampingAcceleration;
//               m_dCurrentPosition += m_dVelocity;
//               if (m_dCurrentPosition > 1.0)
//               {
//                   m_dCurrentPosition = 1.0;
//               }
//               else if (m_dCurrentPosition < -1.0)
//               {
//                   m_dCurrentPosition = -1.0;
//               }
//               t1.set(m_dCurrentPosition);
//               t2.set(
//           }
//       }
//   }
//};