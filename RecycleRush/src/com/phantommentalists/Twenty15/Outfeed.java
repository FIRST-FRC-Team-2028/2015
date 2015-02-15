package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * Author: Christopher D. Hooks
 */
public class Outfeed {

	private CANTalon pusher;
	private CANTalon roller;
	private DigitalInput toteOut;
	private boolean left = false;
	
	public Outfeed() {
		toteOut = new DigitalInput(Parameters.outfeedToteLimitSwitch);

		pusher = new CANTalon(Parameters.outfeedArmCANId);
		roller = new CANTalon(Parameters.outfeedConveyorCANId);

		pusher.changeControlMode(ControlMode.PercentVbus);
		roller.changeControlMode(ControlMode.PercentVbus);
		
		pusher.enableBrakeMode(true);

		pusher.enableLimitSwitch(true, true);
		
		pusher.enableControl();
		roller.enableControl();
		
	}
	public void processOutfeed()
	{
		SmartDashboard.putBoolean("Outfeed tote indicator", toteOut.get());
	}
	/**
	 * This method will move a stack forward in the outfeed.
	 */
	public void moveStackForward(double power) {
		roller.set(power);
	}
	
	public void stopConveyor() {
		roller.set(0.00000000000000000000);
	}

	/**
	 * This method moves the leadscrew left to push a stack left.
	 */
	public void moveStackLeft() {
		if(!isPusherLeft())
		pusher.set(-Parameters.outfeedPusherVoltage);
	}

	/**
	 * This method moves the leadscrew right to push a stack right.
	 */
	public void moveStackRight() {
		if(!isPusherRight())
		pusher.set(Parameters.outfeedPusherVoltage);
	}
	
	public void stopPusher()
	{
		pusher.set(0.0);
	}

	/**
	 * This method returns true if the stack has left the outfeed, false
	 * otherwise.
	 */
	public boolean isStackAllTheWayOut() {
		return toteOut.get();
	}

	/**
	 * This method returns true if a stack is in the outfeed, false otherwise.
	 */
	public boolean isStackInOutfeed() {
		return toteOut.get();
	}
	
	public boolean isPusherLeft() {
		return pusher.isRevLimitSwitchClosed();
	}
	
	public boolean isPusherRight() {
		return pusher.isFwdLimitSwitchClosed();
	}
}