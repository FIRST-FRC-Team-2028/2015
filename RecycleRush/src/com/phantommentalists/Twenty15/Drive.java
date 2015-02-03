package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

/*
 * CrabDrive allocation
 */
public class Drive {

	private CANTalon frontLeft;
	private CANTalon frontRight;
	private CANTalon rearLeft;
	private CANTalon rearRight;
	// private TurnPIDOut turnPIDOut;
	// private DrivePIDOut drivePIDOut;
	private double drive;
	private double turn;
	private double strafe;

	public Drive() {
		if(Parameters.frontLeftCanId != 0)
		{
			frontLeft = new CANTalon(Parameters.frontLeftCanId);
			frontLeft.changeControlMode(ControlMode.Voltage);
			frontLeft.enableControl();
		}
		if(Parameters.frontRightCanId != 0)
		{
			frontRight = new CANTalon(Parameters.frontRightCanId);
			frontRight.changeControlMode(ControlMode.Voltage);
			frontRight.reverseOutput(true);
			frontRight.enableControl();
		}
		if(Parameters.rearLeftCanId != 0)
		{
			rearLeft = new CANTalon(Parameters.rearLeftCanId);
			rearLeft.changeControlMode(ControlMode.Voltage);
			rearLeft.enableControl();
		}
		if(Parameters.rearRightCanId != 0)
		{
			rearRight = new CANTalon(Parameters.rearRightCanId);
			rearRight.changeControlMode(ControlMode.Voltage);
			rearRight.reverseOutput(true);
			rearRight.enableControl();
		}
		drive = 0.0;
		turn = 0.0;
		strafe = 0.0;
	}

	/**
	 * This method sets the drive value.
	 */
	public void setDrive(double forward) {
		drive = forward;
	}

	/**
	 * This method sets the turn value.
	 */
	public void setTurn(double turn) {
		this.turn = turn;
	}

	public void processDrive(double x, double y) {
		double frontLeftOutput = 0;
		double frontRightOutput = 0;
		double rearLeftOutput = 0;
		double rearRightOutput = 0;
		if (isDeadband(x)) {
			if (!isDeadband(y)) {
				frontLeftOutput = y;
				frontRightOutput = y;
				rearLeftOutput = y;
				rearRightOutput = y;
			}
		} else {
			if(isDeadband(y))
			{
				frontLeftOutput = x;
				rearRightOutput = x;
				frontRightOutput = x * -1;
				rearLeftOutput = x * -1;
			} else {
//				if(x < 0)
//				{
//					frontLeftOutput = x - y;
//					rearRightOutput = x - y;
//					frontRightOutput = x;
//					rearLeftOutput = x;
//				}
//				if(x > 0)
//				{
//					frontLeftOutput = x;
//					rearRightOutput = x;
//					frontRightOutput = x - y;
//					rearLeftOutput = x - y;
//				}
			}
		}

		if(frontLeft != null)frontLeft.set(frontLeftOutput * Parameters.maxMotorVoltage);
		if(frontRight != null)frontRight.set(frontRightOutput * Parameters.maxMotorVoltage);
		if(rearLeft != null)rearLeft.set(frontLeftOutput * Parameters.maxMotorVoltage);
		if(rearRight != null)rearRight.set(frontLeftOutput * Parameters.maxMotorVoltage);

	}

	/**
	 * This method sets the strafe value.
	 */
	public void setStrafe(double strafe) {
		this.strafe = strafe;
	}

	public boolean isDeadband(double val) {
		return (val < 0.05 && val > -0.05);
	}

}