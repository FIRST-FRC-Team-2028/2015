package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

/*
 */
public class PhantomOne extends SampleRobot {

    private Drive drive;
    private GameMech gameMech;
    private Gyro gyro;
    private Odometer odometer;
    private PIDController driveController;
    private PIDController turnController;
    private Joystick gameStick;
    private Joystick launchPad;
    private Joystick driveStick;
    private AnalogInput tapeLeft;
    private AnalogInput tapreRight;
    private int t = 0;
    private boolean passedPlatform = false;
    private boolean driving = true;
    private boolean onPlatform = false;
    double angle;

    public PhantomOne()
    {
    	gyro = new Gyro(0);
    	gyro.setPIDSourceParameter(PIDSourceParameter.kAngle);
    	drive = new Drive();
    	gameStick = new Joystick(0);
    	gameMech = new GameMech();
    }
    
  public void autonomous() {
	  onPlatform = false;
	  while(isAutonomous() && isEnabled())
	  {
//		  t++;
//		  if(t < 30)
//		  {
//			  drive.setDrive(-0.45);
//		  } else if(t == 30)
//		  {
//			  drive.setDrive(0);
//		  }
//		  else if(t < 47 && t > 35){
//			  drive.setTurn(0.35);
//		  } else {
//			  drive.setTurn(0);
//			  drive.setDrive(0);
//			  drive.setStrafe(0);
//		  }
		  if(tapeLeft.getAverageValue() < 450)onPlatform = true;
		  if(!onPlatform)
		  {
			  drive.setDrive(-0.25);
		  } else if(tapeLeft.getAverageValue() > 450 && onPlatform)
		  {
			  drive.setDrive(0);
		  } else {
			  drive.setDrive(-0.25);
		  }
		  drive.processDrive();
		  Timer.delay(0.1);
	  }
  }

  public void operatorControl() {
	  while(isEnabled() && isOperatorControl())
	  {
//		  if(driveStick.getTrigger()){
//			  drive.setTurn(driveStick.getTwist());
//		  }
//		  else {
//			  drive.setTurn(0);
//		  }
//		  drive.setDrive(driveStick.getY());
//		  drive.setStrafe(driveStick.getX());
//		  drive.processDrive();
//		  System.out.println("angle : " + gyro.getAngle());
		  if(gameStick.getRawButton(3))
		  {
			  gameMech.stacker.moveElevatorDown();
		  } else if(gameStick.getRawButton(4))
		  {
			  gameMech.stacker.moveElevatorUp();
		  }
		  Timer.delay(0.05);
	  }
  }
}