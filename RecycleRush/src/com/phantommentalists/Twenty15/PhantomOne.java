package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
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
    private Joystick driveStick;

    public PhantomOne()
    {
    	drive = new Drive();
    	driveStick = new Joystick(0);
    }
    
  public void autonomous() {
  }

  public void operatorControl() {
	  while(isEnabled() && isOperatorControl())
	  {
		  drive.processDrive(driveStick.getX(), driveStick.getY());
		  Timer.delay(0.05);
	  }
  }

}