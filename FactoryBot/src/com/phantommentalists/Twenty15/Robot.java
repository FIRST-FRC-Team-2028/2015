package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

/**
 * This is a demo program showing the use of the RobotDrive class. The
 * SampleRobot class is the base of a robot application that will automatically
 * call your Autonomous and OperatorControl methods at the right time as
 * controlled by the switches on the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */
public class Robot extends SampleRobot {

	public Robot() {

	}

	/**
	 * Drive left & right motors for 2 seconds then stop
	 */
	public void autonomous() {

	}

	/**
	 * operatorControl()
	 * 
	 * This method is called every time the robot goes into Operator Control (Teleop).
	 * 
	 */
	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {
			Timer.delay(0.1);
		}
	}

	/**
	 * Runs during test mode
	 */
	public void test() {
	}
}