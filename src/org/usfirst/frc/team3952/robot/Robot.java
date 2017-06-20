package org.usfirst.frc.team3952.robot;

import java.io.IOException; 

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot; 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Ultrasonic;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	DriveTrain dt;
	DashBoard board;
	Joystick stick;
	Joystick stick2;
	//LinearActuatorWinch lin;	
	//ImageProcess i;
	int autoLoopCounter;
	
	ServoC sc;
	WindshieldMotor m1;
	long start;
	
	Talon agitator;
	Talon launcher;

	
	
	//double speed;
	//we are using talon motors so need to make new DriveTrain class
	
    /**
     * This function is run when the robot is first started up and should be;ioj;oi
     * used for any initialization code.
     */
    public void robotInit() {
    	board = new DashBoard();
    	stick = new Joystick(0);
    //	stick2 = new Joystick(1);
    	dt=new DriveTrain(stick);//,stick2);
    //	lin =new LinearActuatorWinch(stick);
    	//sc=new ServoC(stick, stick2,new Servo(6));
    	//m1=new WindshieldMotor(4,5,stick,stick2);
    	//speed=-0.1;
    	agitator = new Talon(7);
    	launcher = new Talon(8);
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	start=System.currentTimeMillis();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	dt.autonD(start);
    	
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	dt.drive();
    	if(stick.getTrigger()){
    		launcher.set(1);
    		agitator.set(0.2);
    	} else{
    		launcher.set(0);
    		agitator.set(0);
    	}
    	board.updateDashboard();
    	//lin.goLAW();
  
    	//m1.pressCheck();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
    
}
