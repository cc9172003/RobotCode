package org.usfirst.frc.team3952.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 0 = back right
   2 = nothing
	3 = climber
		//4 = front right
		//5 = nothing
		//6 = nothing
		//7 = agitator
		//8 = launcher motor
		//9 = left front
* 
* Rules which will keep this code easy to read
* 
* 0. Put decision logic (when should we shoot, when should we agitate) in Controller, put motor and 
* camera actions in Systems (MechanumWheels class, Shooter class) and keep the Robot class 
* as slim as possible. The less code there is in Robot, the easier it is to read.
* 1. Try to not pass controller in as an argument in method calls. That way,
* all the controller decision making is inside Robot. Itll make it easier to
* find out why certain things do what they do. If you pass in controller, you will have to
* look into that method, find out what it does and repeat for all other method calls 
* which accept controller as an argument. Note, this is just a part of Rule 0.
* 2. Keep Joystick, XboxController and others inside Controller class. This makes it easy to switch
* the actual controller without having to dig through a lot of code. If you want to switch controllers
* all you gotta do is change Controller class.
* 3. Prefix Controller boolean methods with should. For example
* 	public boolean shouldShoot(){
* 		//figures out whether it should shoot based on joy stick or xbox or something.
*   }
* 
* 
 */
public class Robot extends IterativeRobot {
	private Shooter shooter;
	private MechanumWheels mechWheels;
	
	private Controller controller;
	private AnalogUltrasonic analogUltrasonic;
	//private UsbCamera camera;
	
	private Task currentTask;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		mechWheels = new MechanumWheels(
				new Talon(9),
				new Talon(4),
				new Talon(1),
				new Talon(0)
		);
		shooter = new Shooter(
				new Talon(8),
				new Talon(7)
		);
		controller = new Controller();
		currentTask = new TelopTask(this);
		analogUltrasonic = new AnalogUltrasonic(2);
		
		//camera = CameraServer.getInstance().startAutomaticCapture();
		//camera.setResolution(640, 480);
		
	}
	

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	
	@Override
	public void teleopPeriodic() {
		if(controller.shouldStartLineUpToWall()){
			currentTask.cancel();
			currentTask = new LineUpTask(this);
			
		}

		//should be last of controller checking.
		if(controller.cancelTask()){
			currentTask.cancel();
			currentTask = new TelopTask(this);
		} 

		boolean done = currentTask.performTask();
		if(done){
			currentTask = new TelopTask(this);
		}  
		
		
		//smart dashboard stuffs
		SmartDashboard.putNumber("Ultrasonic Distance", Math.round(analogUltrasonic.getDistance()));
		SmartDashboard.putString("Current Task", currentTask.toString());
		
	}
	

	
	@Override
	public void testPeriodic() {
		
	}

	
	/**
	 * Getters
	 */
	public Shooter getShooter() {
		return shooter;
	}

	public MechanumWheels getMechWheels() {
		return mechWheels;
	}

	public Controller getController() {
		return controller;
	}
	
	public AnalogUltrasonic getAnalogUltrasonic(){
		return analogUltrasonic;
	}
	
	
}

