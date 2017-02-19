package org.usfirst.frc.team3952.robot;

import java.io.IOException;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
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
 * 
 * Task:
 * 	TODAY: ball launcher PLS NEIL SERIOUSLY MAKER IT WORK>:>>>>>>
 *  LONG TERM:
 *  	CAMERA CODE TESTING> 
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
	
	private AnalogInput ultraRight;
	private AnalogUltrasonic ultraLeft;
	
	private Ultrasonic dUltraRight, dUltraLeft;
	
	private Encoder frontRightEncoder;
	private Encoder frontLeftEncoder;
	private Encoder rearRightEncoder;
	private Encoder rearLeftEncoder;
	
	private Task currentTask;
	private Task shooterTask; //exception to task workflow.
	private Task climbTask;
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
    	climbTask = new ClimbRopeTask(new Talon(7), controller);
    	
//    	dUltraLeft = new Ultrasonic(1,2);
//    	
//    	currentTask = new DriveTask(controller);
    	shooterTask = new BallLaunchTask(new Talon(9), new Talon(8), controller);
//		leftFrontDrive = new SmoothMotorController(0);
//		rightFrontDrive = new SmoothMotorController(1);
//		leftRearDrive = new SmoothMotorController(2);
//		rightRearDrive = new SmoothMotorController(3);
//		objRobotDriver = new RobotDriver(leftFrontDrive, rightFrontDrive, leftRearDrive, rightRearDrive);
//
//		ultraRight = new AnalogInput(2);
//		//ultraLeft = new AnalogUltrasonic(3, 100);
//		frontRightEncoder = new Encoder(0,1); //front right
//		frontLeftEncoder = new Encoder(2, 3);
//		rearRightEncoder = new Encoder(4, 5);
//		rearLeftEncoder = new Encoder(6, 7);
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
    	
    	shooterTask.performTask(null); //SHOULD NOT MOVE THE ROBOT. THROWS NULL POINTER IF IT TRIES SO yay. 
    	climbTask.performTask(null); //ALSO SHOULD NOT MOVE THE ROBOT
    	
    	System.out.println(controller.getPOV());
    	
//    	dUltraLeft.ping();
//    	System.out.println(dUltraLeft.getRangeInches());
//    	
//    	boolean done = currentTask.performTask(objRobotDriver);
//		if(controller.getBButton()){
//			if(!(currentTask instanceof DriveTask)){
//				currentTask.cancel();
//				objRobotDriver.setMotorsDirectly(0, 0, 0, 0);
//				currentTask = new DriveTask(controller);
//			}
//		}else if(controller.getAButton()){
//		//	if(done) currentTask = new MoveToWallTask(ultraRight, ultraLeft, 32);
//		}
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
