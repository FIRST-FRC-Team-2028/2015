package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;

/*
 * Author: Christopher D. Hooks
 */
public class Outfeed {

	private CANTalon pusher;
	public CANTalon roller;
	private DigitalInput toteOut;
	private boolean left = false;
	
	public Outfeed() {
		//toteOut = new DigitalInput(Parameters.outfeedToteLimitSwitch);

		pusher = new CANTalon(Parameters.outfeedArmCANId);
		roller = new CANTalon(Parameters.outfeedConveyorCANId);

		pusher.changeControlMode(ControlMode.PercentVbus);
		roller.changeControlMode(ControlMode.PercentVbus);

		pusher.enableLimitSwitch(true, true);
		pusher.enableControl();
		roller.enableControl();
		
	}

	/**
	 * This method will move a stack forward in the outfeed.
	 */
	public void moveStackForward() {
		roller.set(Parameters.outfeedConveyorVoltage);
	}
	
	public void stopConveyor() {
		roller.set(0.00000000000000000000);
	}

	/**
	 * This method moves the leadscrew left to push a stack left.
	 */
	public void moveStackLeft() {
		pusher.set(-Parameters.outfeedPusherVoltage);
	}

	/**
	 * This method moves the leadscrew right to push a stack right.
	 */
	public void moveStackRight() {
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
		return pusher.isFwdLimitSwitchClosed();
	}
	
	public boolean isPusherRight() {
		return pusher.isRevLimitSwitchClosed();
	}
}