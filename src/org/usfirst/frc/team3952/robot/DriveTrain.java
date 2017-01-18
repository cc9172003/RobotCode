package org.usfirst.frc.team3952.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class DriveTrain
{
	
	Joystick j;
	Joystick j2;
	edu.wpi.first.wpilibj.Talon leftFrontDrive;
	edu.wpi.first.wpilibj.Talon rightFrontDrive;
	edu.wpi.first.wpilibj.Talon leftRearDrive;
	edu.wpi.first.wpilibj.Talon rightRearDrive;
	private RobotDrive rd;
	private RobotDrive rd2;
	private double power;
	private double turnRate;
	private boolean flag;
	private RobotDriver objRobotDriver;
	private boolean tflag;
	private long time;
	
	private int minus;
	private int switchx;
//	private boolean willStop;
	
	public DriveTrain(Joystick joyStick)//), Joystick joystick2)
	{
		tflag=false;
		j = joyStick;
//		j2 = joystick2;
		leftFrontDrive = new Talon(0);
		rightFrontDrive = new Talon(1);
		leftRearDrive = new Talon(2);
		rightRearDrive = new Talon(3);
		objRobotDriver = new RobotDriver(leftFrontDrive, rightFrontDrive, leftRearDrive, rightRearDrive);
		
		rd = new RobotDrive(leftFrontDrive, rightFrontDrive);
		rd2 = new RobotDrive(leftRearDrive, rightRearDrive);
		power  = 1.0;//0.7
		turnRate = 1.1;//0.6
		switchx=1;
		//0.5 is regular
	}
	
	//below
	public void autonD(long start)
{
		while(System.currentTimeMillis()-start<1500)
		{
			objRobotDriver.SetFromController(.2,.4, 0.0, 0.0);
		}
	}
	
	public void drive(){
		
		if(j.getRawButton(2))
		{
			switchx*=-1;
		}
		
		if(switchx==1)
		{
			if(j.getY()>0.4 && j.getX() >0.3)
			{
				objRobotDriver.SetFromController(j.getX(), j.getY(), 0.0, 0.0);
			}
			else
			{	
				objRobotDriver.SetFromController(j.getX(), j.getY(), 0.0, 0.0);
			}
		}
		else	
		{
			if(j.getY()>0.4 && j.getX() >0.3)
			{
				objRobotDriver.SetFromController(j.getX(), -j.getY(), 0.0, 0.0);
			}
			else
			{
				objRobotDriver.SetFromController(j.getX(), -j.getY(), 0.0, 0.0);
			}
		}
		
	}
	public boolean small(double x)
	{
		return Math.abs(x)<0.05;
	}
	public double getPower(){
		return power;
	}
	public double getTurnRate(){
		return turnRate;
	}
}
