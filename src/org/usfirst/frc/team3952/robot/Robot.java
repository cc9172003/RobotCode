package org.usfirst.frc.team3952.robot;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.*;
import edu.wpi.cscore.UsbCamera;
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
	
	
	private XboxController controller; 
	
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
	
	public static final double MAX_SPEED = 0.5;
	private boolean pastInvertButton = false;
	
	private AnalogUltrasonic ultraRight;
	private AnalogUltrasonic ultraLeft;
	
	private Encoder frontRightEncoder;
	private Encoder frontLeftEncoder;
	private Encoder rearRightEncoder;
	private Encoder rearLeftEncoder;
	
	private Task currentTask;
	private Queue<Task> anonymousTaskQueue = new LinkedList<Task>();
	
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	private final Object imgLock = new Object();

	
	
    /**
     * This function is run when the robot is first started up and should be;ioj;oi
     * used for any initialization code.
     */
    public void robotInit() {
    	controller = new XboxController(0);
    	
    	currentTask = new DriveTask(controller);
		leftFrontDrive = new SmoothMotorController(0);
		rightFrontDrive = new SmoothMotorController(1);
		leftRearDrive = new SmoothMotorController(2);
		rightRearDrive = new SmoothMotorController(3);
		objRobotDriver = new RobotDriver(leftFrontDrive, rightFrontDrive, leftRearDrive, rightRearDrive);

		ultraRight = new AnalogUltrasonic(1);
		ultraLeft = new AnalogUltrasonic(2);
		frontRightEncoder = new Encoder(0,1); //front right
		frontLeftEncoder = new Encoder(2, 3);
		rearRightEncoder = new Encoder(4, 5);
		rearLeftEncoder = new Encoder(6, 7);
  
    	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

		CvSink cvSink = CameraServer.getInstance().getVideo();
		CvSource output = CameraServer.getInstance().putVideo("Blur", 640, 480);
		Mat src = new Mat();
		Mat dst = new Mat();
		
		//ideal x = 280 pixels when 680 horizontal pixel resolution
        
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
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	Task frontOfQueue = anonymousTaskQueue.peek();
		if(frontOfQueue != null)
		{
			boolean done = frontOfQueue.performTask(objRobotDriver);
			if(done) {
				anonymousTaskQueue.poll();
			}
		}
    	
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
    	boolean done = currentTask.performTask(objRobotDriver);
		if(controller.getBButton()){
			if(!(currentTask instanceof DriveTask)){
				currentTask.cancel();
				currentTask = new DriveTask(controller);
			}
		}else if(controller.getAButton()){
			if(done) currentTask = new MoveToWallTask(ultraRight, ultraLeft, 32);
		}
	}
	
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
    
    //==============================HELPER METHODS+==================================//
    public boolean small(double x)
	{
		return Math.abs(x)<0.05;
	}
}
