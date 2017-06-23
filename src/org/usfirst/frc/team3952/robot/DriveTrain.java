package org.usfirst.frc.team3952.robot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;

public class DriveTrain
{
	
	Joystick j;
	Joystick j2;
	Talon leftFrontDrive;
	Talon rightFrontDrive;
	Talon leftRearDrive;
	Talon rightRearDrive;
	private RobotDrive rd;
	private RobotDrive rd2;
	private double power;
	private double turnRate;
	private boolean flag;
	private RobotDriver objRobotDriver;
	private boolean tflag;
	private long time;
	
	private int minus;
	
	private static final double MAX_SPEED = 0.5;

//	private boolean willStop;
	
	public DriveTrain(Joystick joyStick)//), Joystick joystick2)
	{
		tflag=false;
		j = joyStick;
//		j2 = joystick2;
		leftFrontDrive = new Talon(9);
		rightFrontDrive = new Talon(4);
		leftRearDrive = new Talon(1);
		rightRearDrive = new Talon(0);
		//0 = back right
		//2 = nothing
		//3 = climber
		//4 = front right
		//5 = nothing
		//6 = nothing
		//7 = agitator
		//8 = launcher motor
		//9 = left front
		
		objRobotDriver = new RobotDriver(leftFrontDrive, rightFrontDrive, leftRearDrive, rightRearDrive);

		rd = new RobotDrive(leftFrontDrive, rightFrontDrive);
		rd2 = new RobotDrive(leftRearDrive, rightRearDrive);
		power  = 1.0;//0.7
		turnRate = 1.1;//0.6
		//0.5 is regular
		
	}
	
	//below
	public void autonD(long start)
	{
		if(System.currentTimeMillis()-start<1500)
		{
			objRobotDriver.SetFromController(.2,.2, true, false);
		}
	}
	
	public void drive(){
		//System.out.println(ultraRight.getDistance());
		//if(small(j.getX()) && small(j.getY())) return;//so it don't figit
		
		
		objRobotDriver.SetFromController(-MAX_SPEED*clean(j.getX()), -MAX_SPEED*clean(j.getY()), j.getRawButton(5), j.getRawButton(4));
	}
	public double clean(double x){
		if (Math.abs(x) < 0.05) return 0;
		return x;
	}
//	public boolean small(double x)
//	{
//		return Math.abs(x)<0.05;
//	}
	public double getPower(){
		return power;
	}
	public double getTurnRate(){
		return turnRate;
	}
}
