package org.usfirst.frc.team3952.robot;
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
	private boolean pastInvertButton = false;

	Ultrasonic ultra = new Ultrasonic(9,1);
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

    	ultra.setAutomaticMode(true);
		rd = new RobotDrive(leftFrontDrive, rightFrontDrive);
		rd2 = new RobotDrive(leftRearDrive, rightRearDrive);
		power  = 1.0;//0.7
		turnRate = 1.1;//0.6
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
		System.out.println(ultra.getRangeInches());
		if(small(j.getX()) && small(j.getY())) return;//so it don't figit
		objRobotDriver.SetFromController(MAX_SPEED*j.getX(), MAX_SPEED*j.getY(), 0.0, 0.0);
			
		
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
