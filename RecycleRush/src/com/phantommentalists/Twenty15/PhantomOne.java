package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.AnalogInput;
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
    private Joystick gmStick;
    private Joystick gmStick2;
    private AnalogInput pot;
    private int t = 0;
    private boolean passedPlatform = false;
    private boolean driving = true;
    private boolean onPlatform = false;

    public PhantomOne()
    {
    	 
    	//drive = new Drive();
    	driveStick = new Joystick(0);
    	gmStick = new Joystick(1);
    	gmStick2 = new Joystick(3);
    	pot = new AnalogInput(0);
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
		  if(pot.getAverageValue() < 450)onPlatform = true;
		  if(!onPlatform)
		  {
			  drive.setDrive(-0.25);
		  } else if(pot.getAverageValue() > 450 && onPlatform)
		  {
			  drive.setDrive(0);
		  } else {
			  drive.setDrive(-0.25);
		  }
		  drive.processDrive();
		  Timer.delay(0.005);
	  }
  }

  public void operatorControl() {
	  while(isEnabled() && isOperatorControl())
	  {
//		  System.out.println(pot.getAverageValue());
//		  if(driveStick.getTrigger()){
//			  drive.setTurn(driveStick.getTwist());
//		  }
//		  else {
//			  drive.setTurn(0);
//		  }
		  if(gmStick.getRawButton(10))
		  {
			  gameMech.turnStackerConveyorOn(true);
		  }
		  else if(gmStick.getRawButton(9))
		  {
			  gameMech.turnStackerConveyorOn(false);
		  }
		  else
		  {
			  gameMech.turnStackerConveyorOff();
		  }
		  //
		  if(gmStick.getRawButton(4))
		  {
			  gameMech.raiseElevator();
		  }
		  else if(gmStick.getRawButton(3))
		  {
			  gameMech.lowerElevator();
		  }
		  else
		  {
			  gameMech.stopElevator();
		  }
		  //
		  if(gmStick.getRawButton(8))
		  {
			  if(gmStick2.getX() >= 0 && gmStick2.getX() < 1)
				  gameMech.turnOutFeedConveyorOn((gmStick2.getX()));
			  else if(gmStick.getX() <= 0 && gmStick2.getX() > -1)
				  gameMech.turnOutFeedConveyorOn(gmStick2.getX());
		  }
		  else if(gmStick.getRawButton(7))
		  {
			  //TODO:DONT DO THIS
			  gameMech.outfeed.roller.set(-Parameters.outfeedConveyorVoltage);
		  }
		  else
		  {
			  gameMech.turnOutFeedConveyorOff();
		  }
		  
//		  drive.setDrive(driveStick.getY());
//		  drive.setStrafe(driveStick.getX());
//		  drive.processDrive();
		  Timer.delay(0.05);
	  }
  }

}