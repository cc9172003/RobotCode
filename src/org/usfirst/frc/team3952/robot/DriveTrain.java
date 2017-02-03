package org.usfirst.frc.team3952.robot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Encoder;

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
	
	private AnalogUltrasonic ultraRight;
	private AnalogUltrasonic ultraLeft;
	
	private Encoder frontRightEncoder;
	private Encoder frontLeftEncoder;
	private Encoder rearRightEncoder;
	private Encoder rearLeftEncoder;

	
	public DriveTrain(Joystick joyStick)//), Joystick joystick2)
	{
		tflag=false;
		j = joyStick;
//		j2 = joystick2;
		leftFrontDrive = new SmoothMotorController(0);
		rightFrontDrive = new SmoothMotorController(1);
		leftRearDrive = new SmoothMotorController(2);
		rightRearDrive = new SmoothMotorController(3);
		objRobotDriver = new RobotDriver(leftFrontDrive, rightFrontDrive, leftRearDrive, rightRearDrive);

		
		rd = new RobotDrive(leftFrontDrive, rightFrontDrive);
		rd2 = new RobotDrive(leftRearDrive, rightRearDrive);
		power  = 1.0;//0.7
		turnRate = 1.1;//0.6
		//0.5 is regular
		
		ultraRight = new AnalogUltrasonic(0);
		//ultraLeft = new AnalogUltrasonic(1);
		frontRightEncoder = new Encoder(0,1); //front right
		frontLeftEncoder = new Encoder(2, 3);
		rearRightEncoder = new Encoder(4, 5);
		rearLeftEncoder = new Encoder(6, 7);
	}
	
	//below
	public void autonD(long start)
{
		while(System.currentTimeMillis()-start<1500)
		{
			objRobotDriver.SetFromController(0.2, 0.4, 0.0, 0.0);
		}
	}
	
	public void drive()
	{
		System.out.println(rearLeftEncoder.get());
		if(small(j.getX()) && small(j.getY())) return;//so it don't figit
		objRobotDriver.SetFromController(-MAX_SPEED*j.getX(), -MAX_SPEED*j.getY(), 0.0, 0.0);
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
