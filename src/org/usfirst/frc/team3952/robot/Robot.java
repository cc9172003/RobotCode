package org.usfirst.frc.team3952.robot;

import java.io.IOException;
import java.util.Arrays;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot; 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Ultrasonic;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;


import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private static final int IMG_WIDTH = 640;
	private static final int IMG_HEIGHT = 480;
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
	
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	private final Object imgLock = new Object();
	private NetworkTable table;
	
	
	public Robot(){
		//for some reason, the sample does this in the constructor. If it has issues, try changing to robotInit.
		table = NetworkTable.getTable("GRIP/myContourReport");
	}
	
	
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
    	sc=new ServoC(stick, stick2,new Servo(6));
    	m1=new WindshieldMotor(4,5,stick,stick2);
    	//speed=-0.1;
    
  
    	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

		CvSink cvSink = CameraServer.getInstance().getVideo();
		CvSource output = CameraServer.getInstance().putVideo("Blur", 640, 480);
		Mat src = new Mat();
		Mat dst = new Mat();
		
        
        visionThread = new VisionThread(camera, new GripPipeline(), new VisionRunner.Listener<GripPipeline>(){
        	public void copyPipelineOutputs(GripPipeline pipeline){
//	           
        		if(pipeline.hslThresholdOutput() != null){
            		
            		

        			//cvSink.grabFrame(src);
            		//Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2GRAY);
        			output.putFrame(pipeline.hslThresholdOutput());
            		
        		}
        		//if (!pipeline.convexHullsOutput().isEmpty()) {
//	                double[] convexHullsAreas = new double[pipeline.convexHullsOutput().size()];
//	                for(int i = 0; i < pipeline.convexHullsOutput().size(); i++){
//	                	convexHullsAreas[i] = Imgproc.contourArea(pipeline.convexHullsOutput().get(i));
//	                }
//	                
//	                for(double d: convexHullsAreas)
//	                	System.out.println(d);
//	               
//	                
//	                synchronized (imgLock) {
//	                	
//	                    System.out.println();
//	                }
	            //}
        	}
        });
        
        
       visionThread.start();
//        (new Thread(){
//        	private GripPipeline pipe = new GripPipeline();
//        	@Override
//        	public void run(){
//        		CvSink cvSink = CameraServer.getInstance().getVideo();
//        		CvSource output = CameraServer.getInstance().putVideo("Blur", 640, 480);
//        		
//        		Mat source = new Mat();
//        		
//        		while(!Thread.interrupted()){
//        			cvSink.grabFrame(source);
//        			source = pipe.processTMP(source);
//        			output.putFrame(source);
//        		}
//        	}
//        }).start();
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
    	board.updateDashboard();
    	//lin.goLAW();
    	
    	sc.pCheck();
    	//m1.pressCheck();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
    
}
