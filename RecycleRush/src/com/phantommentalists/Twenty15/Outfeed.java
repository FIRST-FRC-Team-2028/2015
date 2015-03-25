package com.phantommentalists.Twenty15;

import java.util.Timer;
import java.util.TimerTask;

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
	private boolean rightfull = false;
	private TimerTask timertask;
	private TimerTask timertask2;
	private Timer timer;
	private Timer timer2;
	private boolean timerdone = false;
	private boolean autopilot = false;
	private OutfeedStates state;
	private boolean isStackOff = false;
	private boolean stackMoving = false;
	private boolean left = false;
	private boolean movingfast = false;
	private boolean movestack = false;
	private boolean timer1sech = false;
	private boolean timer2sech = false;
	private int stacks = 0;
	
	private class delay extends TimerTask
	{
		public void run() {
			timerdone = true;
		}
		
	}
	
	private class delay2 extends TimerTask
	{
		public void run(){
			isStackOff = true;
			System.out.println("delay2");
		}
	}
	
	public Outfeed() {
		state = OutfeedStates.ready;
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
		SmartDashboard.putNumber("Outfeed current",pusher.getOutputCurrent());
		SmartDashboard.putNumber("Outfeed roller voltage",roller.getOutputVoltage());
		SmartDashboard.putNumber("Outfeed stacks right",stacks);
		SmartDashboard.putBoolean("Outfeed can left", rightfull);
		
		if(autopilot)
		{
//			if(!pusher.isFwdLimitSwitchClosed() || !pusher.isRevLimitSwitchClosed())
//			{
//				state = OutfeedStates.movingarm;
//			}
//			else if(roller.getOutputVoltage() > 0 || !toteOut.get())
//			{
//				state = OutfeedStates.conveying;
//			}
//			else
//			{
//				state = OutfeedStates.ready;
//			}
//			
			if(isStackOff)
			{
				stopConveyor();
				stacks++;
				System.out.println("stack off");
//				if(stacks == 2 || stacks ==4)
//					moveStackRight();
//				else
//				moveStackLeft();
				timer = null;
				timer2 = null;
				timertask = null;
				timertask2 = null;
				timer1sech=false;
				timer2sech=false;
				stackMoving = true;
				isStackOff = false;
				timerdone = false;
				movingfast = false;
			}
		}
	}
	/**
	 * This method will move a stack forward in the outfeed.
	 */
	public void moveStackForward(boolean fwd,int stackheight) {
		stackMoving = true;
		if(!fwd)
		{
			roller.set(-Parameters.outfeedConveyorVoltageSlow);
		}
		else if(toteOut.get() && !timerdone)
		{
			movingfast = false;
			roller.set(Parameters.outfeedConveyorVoltageSlow);
		}
		else if(!toteOut.get() && !timerdone)
		{
			roller.set(0.0);
			if(stackheight == 5)
			{
				if(timertask==null)
				{
					timertask = new delay();
				}
				if(timer==null)
				{
					timer = new Timer();
				}
				if(timer1sech == false)
				{
					timer.schedule(timertask, Parameters.feedConveyorPuaseDelay);
					timer1sech = true;
				}
			}
			else
			{
				timerdone = true;
			}
		}
		else if(timerdone && !toteOut.get())
		{
			movingfast = true;
			roller.set(Parameters.outfeedConveyorVoltageFast);
			isStackOff = false;
			if(timertask2 == null)
			{
				timertask2 = new delay2();
			}
			if(timer2 == null)
			{
				timer2 = new Timer();
			}
			if(timer2sech == false)
			{
				timer2.schedule(timertask2, 2000);
				timer2sech = true;
			}
		}
		else if(isStackOff && toteOut.get())
		{
			if(isPusherLeft())
			{
				moveStackRight();
			}
			else if(isPusherRight())
			{
				moveStackLeft();
			}
			movingfast = false;
			timerdone = false;
		}
	}
	
	public boolean isStackOff()
	{
		return isStackOff;
	}
	
	public void stopConveyor() {
		roller.set(0.00000000000000000000); // Not enough decimal places
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
	
	public void setAutoPilot(boolean value)
	{
		autopilot = value;
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
	
	public boolean isConveying()
	{
		return (state == OutfeedStates.conveying);
	}
	
	public boolean isMovingArm()
	{
		return (state == OutfeedStates.movingarm);
	}
	
	public boolean isReady()
	{
		return (state == OutfeedStates.ready);
	}
}