package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.RobotDrive;

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
		if (Parameters.frontLeftCanId != 0) {
			frontLeft = new CANTalon(Parameters.frontLeftCanId);
			frontLeft.changeControlMode(ControlMode.PercentVbus);
			frontLeft.enableBrakeMode(true);
			frontLeft.enableControl();
			System.out.println("Front left initialized");
		}
		if (Parameters.frontRightCanId != 0) {
			frontRight = new CANTalon(Parameters.frontRightCanId);
			frontRight.changeControlMode(ControlMode.PercentVbus);
			frontRight.enableBrakeMode(true);
			frontRight.enableControl();
			System.out.println("Front Right initialized");
		}
		if (Parameters.rearLeftCanId != 0) {
			rearLeft = new CANTalon(Parameters.rearLeftCanId);
			rearLeft.changeControlMode(ControlMode.PercentVbus);
			rearLeft.enableBrakeMode(true);
			rearLeft.enableControl();
			System.out.println("Rear Left initialized");
		}
		if (Parameters.rearRightCanId != 0) {
			rearRight = new CANTalon(Parameters.rearRightCanId);
			rearRight.changeControlMode(ControlMode.PercentVbus);
			rearRight.enableBrakeMode(true);
			rearRight.enableControl();
			System.out.println("Rear Righ Initialized");
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

	public void processDrive() {
		if(isDeadband(drive))drive = 0;
		if(isDeadband(strafe))strafe = 0;
		drive *= -1;
		double frontLeftOutput = 0;
		double frontRightOutput = 0;
		double rearLeftOutput = 0;
		double rearRightOutput = 0;
		double rotated[] = rotateVector(strafe, drive, 0);
		double xIn = rotated[0];
		double yIn = rotated[1];
		frontLeftOutput = xIn + yIn + turn;
		frontRightOutput = yIn - xIn - turn;
		rearLeftOutput = yIn - xIn + turn;
		rearRightOutput = xIn + yIn - turn;
		frontLeft.set(frontLeftOutput);
		frontRight.set(frontRightOutput * -1);
		rearLeft.set(rearLeftOutput);
		rearRight.set(rearRightOutput * -1);

	}

	/**
	 * This method sets the strafe value.
	 */
	public void setStrafe(double strafe) {
		this.strafe = strafe;
	}
	
	protected static double[] rotateVector(double x, double y, double angle) {
        double cosA = Math.cos(angle * (3.14159 / 180.0));
        double sinA = Math.sin(angle * (3.14159 / 180.0));
        double out[] = new double[2];
        out[0] = x * cosA - y * sinA;
        out[1] = x * sinA + y * cosA;
        return out;
    }

	public boolean isDeadband(double val) {
		return (val < 0.05 && val > -0.05);
	}

}