package org.usfirst.frc.team3952.robot;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * use camera displacement idea.
 * 
 *  dHeading = k * (idealx -actualx); where k is a constant
 *  
 *  Sqrt is employed since it prevents enormas dHeadings from occuring. Note, log might 
 *  be a good replacement if this dampening is not enough. Then we have:
 *  
 *  powerRight = constant - dHeading;
 *  powerLeft = constant + dHeading
 * 
 * 
 * Plan:
 * set up vision thread to update deltaTheta and mark it as updated
 * 	Make sure lock is used to prevent funny issues
 * if vision thread is called again before performTask(), it sees that deltaTheta has been updated and returns
 * When performTask() is called, it calculates power and marks deltaTheta as not updated.
 * When ultrasonics detect a wall less than 50 (?) cm away, cancel is called
 * 
 * cancel cleans up the thread so no more intensive vision calculations are done and makes it so performTask() returns false. 
 * 
 * @author DUMBOOCTOPUS
 *
 */
public class GearPlacingTask implements Task {

	private static final int IMG_WIDTH = 640;
	private static final int IMG_HEIGHT = 480;
	private static final int IDEAL_X = 280;
	private static final double K = (1/640.0)/8.0;
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	private final Object imgLock = new Object();
	
	private double dHeading = 0.1; //something thats not 0 so that 
	
	
	public GearPlacingTask(){
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

		CvSink cvSink = CameraServer.getInstance().getVideo();
		CvSource output = CameraServer.getInstance().putVideo("Blur", 640, 480);
		Mat src = new Mat();
		Mat dst = new Mat();
		
		//ideal x = 280 pixels when 680 horizontal pixel resolution
        
        visionThread = new VisionThread(camera, new GripPipeline(), new VisionRunner.Listener<GripPipeline>(){
        	public void copyPipelineOutputs(GripPipeline pipeline){

        		//calculates deltatheta
        		if (!pipeline.convexHullsOutput().isEmpty()) {

        			double leftMost = IMG_WIDTH + 100;
        			for(MatOfPoint mop: pipeline.convexHullsOutput()){
        				Rect r = Imgproc.boundingRect(mop);
        				if(r.x < leftMost) leftMost = r.x;
        			}
        			if(leftMost > IMG_WIDTH) return;
        			
        			synchronized(imgLock){
        				dHeading = -K * (IDEAL_X - leftMost);
        			}
        			
        			
	            }
        	}
        });
        
        
       visionThread.start();
	}
	
	@Override
	public boolean performTask(RobotDriver driver) {

		synchronized(imgLock){
			double pwrRight = Math.max(0.5*Robot.MAX_SPEED - dHeading, 0);
			double pwrLeft = Math.max(0.5*Robot.MAX_SPEED + dHeading, Robot.MAX_SPEED);
			driver.setMotorsDirectly(pwrLeft, pwrRight, pwrLeft, pwrRight);
		}
		
		return false;
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		visionThread.interrupt(); //kills the thread
		
	}
	
}
