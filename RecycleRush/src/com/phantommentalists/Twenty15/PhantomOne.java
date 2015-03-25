package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 */
public class PhantomOne extends SampleRobot {

	private Drive drive;
	private GameMech gameMech;
	private Gyro gyro;
	private Odometer odometer;
	private AnalogInput taperight;
	private AnalogInput tapeleft;
	private TurnPIDOut turnPIDOut;
	private PIDController turnController;
	private Joystick driveStick;
	private Joystick buttonStick;
	private Joystick switchStick;
	private Joystick axisStick;
	private int t = 0;
	private boolean passedPlatform = false; // 3.14159265358979323846264338327950
	private boolean driving = true;
	private boolean onPlatform = false;
	private AutoStates autostate;
	private Timer timer;
	private DigitalInput outfeedinput; // 3.14159265358979323846264338327950
	private boolean autopilot = false;
	private boolean automove = false;
	private boolean opinit = false;

	public PhantomOne() {

		drive = new Drive();
		gyro = new Gyro(Parameters.gyroAnalogIn);
		turnPIDOut = new TurnPIDOut(drive);
		turnController = new PIDController(
				Parameters.steeringProportionalValue,
				Parameters.steeringIntegralValue,
				Parameters.steeringDerivativeValue, gyro, turnPIDOut);
		driveStick = new Joystick(Parameters.driveStickId); // 3.14159265358979323846267338327950
		buttonStick = new Joystick(Parameters.buttonStickId);
		switchStick = new Joystick(Parameters.switchStickId);
		axisStick = new Joystick(Parameters.axisStickId);
		gameMech = new GameMech(); // 3.14159265358979323846264338327950
		tapeleft = new AnalogInput(Parameters.tapeLeftAnalogIn);
		taperight = new AnalogInput(Parameters.taperightAnalogIn);
		timer = new Timer();
	}

	public void autonomous() {
		double startTime = 0.0;
		onPlatform = false;
		autostate = AutoStates.lifttote;
		gyro.reset();
		timer.reset();
		timer.start();
		int autoChoice = decypher(axisStick.getAxis(Parameters.AutoSelect));
		boolean rc;
		boolean tote;
		while (isAutonomous() && isEnabled()) {
			SmartDashboard.putNumber("Match Time", timer.get());
			SmartDashboard.putString("Autostate ", autostate.toString());
			turnController.disable();
			switch (autostate) {
			case lifttote:
				if(autoChoice==4)
				{
					autostate = AutoStates.carpet;
					break;
				}
				if(autoChoice == 1)
				{
					autostate = AutoStates.done;
					break;
				}
				if (startTime == 0.0) {
					startTime = timer.get();
					gameMech.raiseElevator();
				} else {
					rc = gameMech.isElevatorUp() && autoChoice == 3;
					tote = timer.get()-startTime >= Parameters.autoLiftTime && autoChoice == 2;
					if (rc || tote) {
						gameMech.stopElevator();
						autostate = AutoStates.carpet;
						startTime = 0.0;
					}
				}
				break;
			case carpet:
				if (startTime == 0.0) {
					startTime = timer.get();
					System.err.println(timer.get());
					turnController.enable();
					System.out.println("set drive");
				} else if (tapeleft.getAverageValue() < 450
						&& taperight.getAverageValue() < 450) {
					autostate = AutoStates.scoring;
					startTime = 0.0;
				} else if (timer.get() - startTime > Parameters.autoFailsafeCarpetTime) {
					// Fail Safe Should never get here
					 drive.setDrive(0.0);
					 turnController.disable();
				}
				if(autoChoice == 4)
				{
					drive.setDrive(-0.5);
				}else{
					drive.setDrive(-0.5);
				}
				break;
			case scoring:
				if (startTime == 0.0) {
					startTime = timer.get();
				} else if (tapeleft.getAverageValue() > Parameters.autoTapeThreshHold
						&& taperight.getAverageValue() > Parameters.autoTapeThreshHold) {
					startTime = 0.0;
					autostate = AutoStates.autozone;
				} else if (timer.get() - startTime > Parameters.autoFailsafeScoringTime) {
					// Fail Safe Should never get here
					drive.setDrive(0.0);
					turnController.disable();
				} else {
					drive.setDrive(-0.35);
				}
				break;
			case autozone:
				if (startTime == 0.0) {
					startTime = timer.get();
				} else if (timer.get() - startTime >= Parameters.autoScoreTime) {
					drive.setDrive(0.0);
					turnController.disable();
					autostate = AutoStates.done;
				}
				break;
			case done:
				timer.stop();
				drive.setDrive(0.0);
				if(autoChoice != 4 || autoChoice != 1)
				{
					gameMech.lowerElevator();
				}
				else
				{
					gameMech.raiseElevator();
				}
				break;
			default:
				break;
			}
			drive.processDrive();
			Timer.delay(0.05);
		}
	}

	public void operatorControl() {
		double mult = 1;
		timer.reset();
		timer.start();
		double starttime = 0.0;
		while (isEnabled() && isOperatorControl()) {
			SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
			SmartDashboard.putNumber("Left Tape Sensor",
					tapeleft.getAverageValue());
			SmartDashboard.putNumber("Right Tape Sensor",
					taperight.getAverageValue());
			SmartDashboard.putNumber("Joystick x:", driveStick.getX());
			SmartDashboard.putNumber("JoyStick y:", driveStick.getY());
			SmartDashboard.putBoolean("Gyro", turnController.isEnable());
			// SmartDashboard.putBoolean("StackerTote Inicator", );
			if(!opinit)
			{
				opinit = true;
				gameMech.deployInfeed();
			}
			if(driveStick.getRawButton(2))
			{
				mult = Parameters.driveDampMult;
			} else {
				mult = 1;
			}
			if (drive != null) {
				if (driveStick.getRawButton(11)) {
					gyro.reset();
				}
//				if(driveStick.getRawButton(7))
//				{
//					turnController.enable();
//				}
//				else
//				{
//					turnController.disable();
//				}
				if (driveStick.getTrigger()) {
					drive.setTurn(driveStick.getTwist() * mult);
				} else {
					drive.setTurn(0);
				}
				if(!drive.isDeadband(driveStick.getY()) || !drive.isDeadband(driveStick.getX()))
				{
					automove = false;
				}
				else if(automove)
				{
					if(!gameMech.isInfeedDeployed())
					{
						gameMech.deployInfeed();
					}
					else
					{
						if(starttime == 0.0)
						{
							starttime = timer.get();
						}
						else if(timer.get() - starttime < Parameters.teleautomovetime)
						{
							drive.setDrive(0.5);
						}
						else
						{
							automove = false;
						}
					}
				}
				drive.setDrive(driveStick.getY() * mult);
				drive.setStrafe(driveStick.getX() * mult);
				drive.processDrive();
			}

			if (gameMech != null) {
				if (switchStick.getRawButton(1)) {
//					turnController.enable();
					gameMech.setAutonomousStacking(true);
				} else {
					gameMech.setAutonomousStacking(false);
					turnController.disable();
					if (buttonStick.getRawButton(10)) {
						gameMech.turnStackerConveyorOn(true, switchStick.getY());
					} else if (buttonStick.getRawButton(9)) {
						gameMech.turnStackerConveyorOn(false, switchStick.getY());
					} else {
						gameMech.turnStackerConveyorOff();
					}
					//
					if (buttonStick.getRawButton(4)) {
						gameMech.raiseElevator();
					} else if (buttonStick.getRawButton(3)) {
						gameMech.lowerElevator();
					} else {
						gameMech.stopElevator();
					}
					//
					if (buttonStick.getRawButton(8)) {
//						if (gmStick2.getX() >= 0 && gmStick2.getX() < 1)
							gameMech.turnOutFeedConveyorOn(true);
//						else if (gmStick.getX() <= 0 && gmStick2.getX() > -1)
//							gameMech.turnOutFeedConveyorOn(true);
					} else if (buttonStick.getRawButton(7)) {
						// TODO:DONT DO THIS
						gameMech.turnOutFeedConveyorOn(false);
					} else {
						gameMech.turnOutFeedConveyorOff();
					}
	
					if (buttonStick.getRawButton(5)) {
						gameMech.deployInfeed();
					} else if (buttonStick.getRawButton(6)) {
						gameMech.retractInfeed();
					} else {
						gameMech.stopInfeed();
					}
					if (buttonStick.getRawButton(1)) {
						gameMech.moveOutFeedArmLeft();
						if(!turnController.isEnable())
						{
							turnController.enable();
							gyro.reset();
						}
					} else if (buttonStick.getRawButton(2)) {
						gameMech.moveOutFeedArmRight();
						if(!turnController.isEnable())
						{
							turnController.enable();
							gyro.reset();
						}
					} else {
						gameMech.stopOutFeedArm();
						if(turnController.isEnable())turnController.disable();
					}
				}
				gameMech.processGameMech(decypher(axisStick
						.getAxis(Parameters.stackHeightSelect)));

			}
			Timer.delay(0.1);
		}
	}

	public int decypher(double val) {
		if (val < 0)
			return 1+(int) ((((val + 1) / 2) * 10)+0.05);
		else {
			return 1+(int) (5 + (val * 4)+0.05);
		}
	}

}