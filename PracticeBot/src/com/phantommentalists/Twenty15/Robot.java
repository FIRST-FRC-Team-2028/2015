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
	Joystick rightStick = new Joystick(1);
	Joystick leftStick = new Joystick(0);
	Joystick launchPad = new Joystick(2);
	CANJaguar leftOne;
	CANJaguar leftTwo;
	CANJaguar rightOne;
	CANJaguar rightTwo;
	Solenoid high = new Solenoid(0);
	Solenoid low = new Solenoid(1);
	BuiltInAccelerometer accel;
	double deltaX = 0;
	double deltaY = 0;

	public Robot() {
		leftOne = new CANJaguar(34);
		leftOne.setPercentMode();
		leftOne.configMaxOutputVoltage(12.0);
		leftOne.enableControl();
		leftTwo = new CANJaguar(10);
		leftTwo.setPercentMode();
		leftTwo.configMaxOutputVoltage(12.0);
		leftTwo.enableControl();
		rightOne = new CANJaguar(12);
		rightOne.setPercentMode();
		rightOne.configMaxOutputVoltage(12.0);
		rightOne.enableControl();
		rightTwo = new CANJaguar(43);
		rightTwo.configMaxOutputVoltage(12.0);
		rightTwo.setPercentMode();
		rightTwo.enableControl();
		low.set(true);
		high.set(false);
		accel = new BuiltInAccelerometer();
		accel.setRange(Range.k2G);
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
	 * At present, this is for the prctice bot which has been fitted with the 2015 control system. It is tank drive, with
	 * two joysticks, one for each side. It has a two speed gearbox, and is capable of shifting gears, and will indicate which 
	 * gear it is in through the TI Launcher provided in the 2015 KOP.
	 * 
	 */
	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {
			if (leftStick.getY() < (-0.05) || leftStick.getY() > 0.05) {
				leftOne.set(leftStick.getY() * -1);
				leftTwo.set(leftStick.getY() * -1);

			} else {
				leftOne.set(0);
				leftTwo.set(0);
			}
			if (rightStick.getY() < (-0.05) || rightStick.getY() > 0.05) {
				rightOne.set(rightStick.getY());
				rightTwo.set(rightStick.getY());
			} else {
				rightOne.set(0);
				rightTwo.set(0);
			}
			if (rightStick.getTrigger()) {
				low.set(false);
				high.set(true);
				launchPad.setOutput(1, true);
			} else if (leftStick.getTrigger()) {
				low.set(true);
				high.set(false);
				launchPad.setOutput(1, false);
			}
			Timer.delay(0.1);
		}
	}

	/**
	 * Runs during test mode
	 */
	public void test() {
	}
}