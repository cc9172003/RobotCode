package org.usfirst.frc.team3952.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class DriveTrain {
	
	Joystick j;
	Talon leftDrive;
	Talon rightDrive;
	private RobotDrive rd;
	private double power;
	private double turnRate;
	private boolean flag;
	
	private boolean tflag;
	private long time;
	private int minus;
	
//	private boolean willStop;
	
	public DriveTrain(Joystick joyStick){
		tflag=false;
		j = joyStick;
		leftDrive = new Talon(0);
		rightDrive = new Talon(1);
		rd = new RobotDrive(leftDrive, rightDrive);
		power  = 0.6;//0.7
		turnRate = 0.6;//0.6
		//0.5 is regular
	}
	
	//below
	public void autonD()
	{
		if(!tflag)
		{	
			time=System.currentTimeMillis(); tflag=true; minus=1;
		}
		if(System.currentTimeMillis()-time>3000.0)
		{
			minus*=-1; 
			time=System.currentTimeMillis();
		}
		rd.arcadeDrive(0.0, 0.6*minus, true);
	}
	
	public void drive(){
//		if(j.getX()==0.0&&j.getY()==0.0)
//			willStop=true;
		if(j.getRawButton(2)){
    		power = 0.6;
    		turnRate = 0.6;
    		flag = true;
    	}
    	else if(j.getRawButton(3)){
    		if(power>=0.2&&turnRate>=0.2)
    		{
    			power-=0.1;
    			turnRate-=0.1;
    		}
    	}
    	else if(j.getRawButton(4)){
    		if(power<=0.9&&turnRate<=0.9)
    		{
    			power+=0.1;
    			turnRate+=0.1;

    		}
    	}
		if(j.getY()>0.2)
		{
			rd.arcadeDrive(-j.getY()*power,j.getX()*turnRate,flag);
		}
		else
		{
			rd.arcadeDrive(-j.getY()*power, -j.getX()*turnRate, flag);
		}
//		if(willStop)
//			power/=2.0;turnRate/=2.0;
		
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
