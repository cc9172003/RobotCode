package org.usfirst.frc.team3952.robot;
//<<<<<<< HEAD
import java.io.DataInputStream;
//=======

//>>>>>>> c89d44f2691e55ca14bc0fa7d6bcec1bcb0615cb
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
//<<<<<<< HEAD
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;
//=======
//>>>>>>> c89d44f2691e55ca14bc0fa7d6bcec1bcb0615cb

import com.ni.vision.NIVision;
import com.ni.vision.VisionException;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Point;
import com.ni.vision.NIVision.RGBValue;
import com.ni.vision.NIVision.ROI;
import com.ni.vision.NIVision.RawData;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;

public class ImageProcess {
	Image frame;
	int session;
	ROI cam;
	CameraServer s;
//<<<<<<< HEAD
	int quality;
	List<Byte> m_imageData;
	boolean easyMode = false;
	
	public ImageProcess(boolean inputEasyMode){
		easyMode = inputEasyMode;
	}
//=======
	public ImageProcess(){
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		session = NIVision.IMAQdxOpenCamera("cam0",
	    NIVision.IMAQdxCameraControlMode.CameraControlModeController);
	    NIVision.IMAQdxConfigureGrab(session);
	    NIVision.imaqFlatten(frame,
	    NIVision.FlattenType.FLATTEN_IMAGE,
		NIVision.CompressionType.COMPRESSION_JPEG, 10 * 30);
	}
	public boolean detectTote(){
		// 1. Retrieve the image from the camera stream.
		// 2. Convert image to binary Format: only shows white and black pixels
		// 3. Calculate contour: use function that splits binary function into an array
		// 4. Calculate the center of each contour: uses moments of the contour fo find the centre
		// 5. Figure out which one is on the left and/or right: using the distance of the mass centre
		//	compare with midpoint
		// source: http://youtu.be/HYWgS2M8Zy4?t=2m6s
		//	   https://github.com/rr1706/vision2015/tree/master/src
		
		/**
		 * ALTERNATE
		 **/
		 
		 //NI myRIO
		 //Software dwnld: http://www.ni.com/product-documentation/14603/en/
		
//>>>>>>> c89d44f2691e55ca14bc0fa7d6bcec1bcb0615cb
		return Math.random()<0.5;
	}
	
	public void runCamera() throws IOException{
		if(easyMode){
			s = CameraServer.getInstance();
			s.setQuality(50);
			s.startAutomaticCapture("cam0");
		}
		else{
			NIVision.IMAQdxConfigureGrab(session);
			NIVision.IMAQdxStartAcquisition(session);
			NIVision.IMAQdxGrab(session, frame, 1);
			RawData data = NIVision.imaqFlatten(frame,
					NIVision.FlattenType.FLATTEN_IMAGE,
					NIVision.CompressionType.COMPRESSION_JPEG, 10 * 50);
			ByteBuffer buffer = data.getBuffer();
		}		
	}
	
	public void stopCamera(){
        NIVision.IMAQdxStopAcquisition(session);
	}
	
	public boolean isTote(){
		return false;
	}	
}
