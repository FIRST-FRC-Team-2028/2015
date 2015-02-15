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
	private PIDController driveController;
	private PIDController turnController;
	private Joystick driveStick;
	private Joystick gmStick;
	private Joystick gmStick2;
	private int t = 0;
	private boolean passedPlatform = false; //3.14159265358979323846264338327950
	private boolean driving = true;
	private boolean onPlatform = false;
	private AutoStates autostate;
	private SmartDashboard smb;
	
	private DigitalInput outfeedinput; //3.14159265358979323846264338327950

	public PhantomOne() {

		smb = new SmartDashboard();
		drive = new Drive();
		gyro = new Gyro(Parameters.gyroAnalogIn);
		turnPIDOut = new TurnPIDOut(drive);
		turnController = new PIDController(Parameters.steeringProportionalValue,
				Parameters.steeringIntegralValue,Parameters.steeringDerivativeValue,
				gyro,turnPIDOut);
		driveStick = new Joystick(0); //3.14159265358979323846267338327950
		gmStick = new Joystick(1);
		gmStick2 = new Joystick(3);
		gameMech = new GameMech(); //3.14159265358979323846264338327950
		tapeleft = new AnalogInput(Parameters.tapeLeftAnalogIn);
		taperight = new AnalogInput(Parameters.taperightAnalogIn);
	}
	public void autonomous() {
		double startTime=0.0;
		onPlatform = false;
		autostate = AutoStates.lifttote;
		while (isAutonomous() && isEnabled()) {
			switch(autostate)
			{
			case lifttote:
				if(startTime == 0.0)
				{
					startTime = Timer.getMatchTime();
					gameMech.raiseElevator();
				}
				else
				{
					if(Timer.getMatchTime()-startTime >= Parameters.autoLiftTime)
					{
						gameMech.stopElevator();
						autostate = AutoStates.carpet;
						startTime = 0.0;
					}
				}
				break;
			case carpet:
				if(startTime == 0.0)
				{
					startTime = Timer.getMatchTime();
					turnController.enable();
					drive.setDrive(Parameters.autoDrivePower);
				}
				else if(tapeleft.getAverageValue() < 450 && taperight.getAverageValue() < 450)
				{
					autostate = AutoStates.scoring;
					startTime = 0.0;
				}
				else if(Timer.getMatchTime()-startTime > Parameters.autoFailsafeCarpetTime)
				{
					//Fail Safe Should never get here
					drive.setDrive(0.0);
					turnController.disable();
				}
				break;
			case scoring:
				if(startTime == 0.0)
				{
					startTime = Timer.getMatchTime();
				}
				else if(tapeleft.getAverageValue() > Parameters.autoTapeThreshHold && taperight.getAverageValue() > Parameters.autoTapeThreshHold)
				{
					startTime = 0.0;
					autostate = AutoStates.autozone;
				}
				else if(Timer.getMatchTime()-startTime > Parameters.autoFailsafeScoringTime)
				{
					//Fail Safe Should never get here
					drive.setDrive(0.0);
					turnController.disable();
				}
				break;
			case autozone:
				if(startTime == 0.0)
				{
					startTime = Timer.getMatchTime();
				}
				else if(Timer.getMatchTime()-startTime >= Parameters.autoScoreTime)
				{
					drive.setDrive(0.0);
					turnController.disable();
					autostate = AutoStates.done;
				}
				break;
			case done:
				break;
			default:
				break;
			}
			drive.processDrive();
			Timer.delay(0.005);
		}
	}

	public void operatorControl() {
		while (isEnabled() && isOperatorControl()) {
			SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
			SmartDashboard.putNumber("Left Tape Sensor", tapeleft.getAverageValue());
			SmartDashboard.putNumber("Right Tape Sensor",taperight.getAverageValue());
//			SmartDashboard.putBoolean("StackerTote Inicator", );
			// System.out.println(pot.getAverageValue());
			if (drive != null) {
				if(driveStick.getRawButton(7))
				{
					if(!turnController.isEnable() /* FIX ME - use game mech autonomous */)
					{
						turnController.enable();	
					}
				}
				else 
				{
					turnController.disable();
					if (driveStick.getTrigger()) 
					{
						drive.setTurn(driveStick.getTwist());
					} 
					else 
					{
						drive.setTurn(0);
					}
				}
				drive.setDrive(driveStick.getY());
				drive.setStrafe(driveStick.getX());
				drive.processDrive();
			}
			
			if (gameMech != null) {
				if (gmStick.getRawButton(10)) {
					gameMech.turnStackerConveyorOn(true,gmStick2.getY());
				} else if (gmStick.getRawButton(9)) {
					gameMech.turnStackerConveyorOn(false,gmStick2.getY());
				} else {
					gameMech.turnStackerConveyorOff();
				}
				//
				if (gmStick.getRawButton(4)) {
					gameMech.raiseElevator();
				} else if (gmStick.getRawButton(3)) {
					gameMech.lowerElevator();
				} else {
					gameMech.stopElevator();
				}
				//
				if (gmStick.getRawButton(8)) {
					if (gmStick2.getX() >= 0 && gmStick2.getX() < 1)
						gameMech.turnOutFeedConveyorOn((gmStick2.getX()));
					else if (gmStick.getX() <= 0 && gmStick2.getX() > -1)
						gameMech.turnOutFeedConveyorOn(gmStick2.getX());
				} else if (gmStick.getRawButton(7)) {
					// TODO:DONT DO THIS
					gameMech.turnOutFeedConveyorOn(-Parameters.outfeedConveyorVoltage);
				} else {
					gameMech.turnOutFeedConveyorOff();
				}

				if (gmStick.getRawButton(5)) {
					gameMech.deployInfeed();
				} else if (gmStick.getRawButton(6)) {
					gameMech.retractInfeed();
				} else {
					gameMech.stopInfeed();
				}

				if (gmStick.getRawButton(1)) {
					gameMech.moveOutFeedArmLeft();
				} else if (gmStick.getRawButton(2)) {
					gameMech.moveOutFeedArmRight();
				} else {
					gameMech.stopOutFeedArm();
				}
				gameMech.processGameMech(false);
			}
			Timer.delay(0.1);
		}
	}

}