package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;

/*
 * Author: Christopher D. Hooks
 */
public class Outfeed {

    private CANTalon pusher;
    private CANTalon roller;
    private DigitalInput toteOut;

    public Outfeed(){
    	toteOut = new DigitalInput(Parameters.outfeedToteLimit);
    	
    	pusher=new CANTalon(Parameters.outfeedArmCANId);
    	roller=new CANTalon(Parameters.outfeedConveyorCANId);
    	
    	pusher.changeControlMode(ControlMode.Voltage);
    	roller.changeControlMode(ControlMode.Voltage);
    	
    	pusher.enableLimitSwitch(true, true);
    	
    }
    
  /** 
   *  This method will move a stack forward in the outfeed.
   */
  public void moveStackForward() {
	  roller.set(Parameters.outfeedConveyorVoltage);
  }

  /** 
   *  This method moves the leadscrew left to push a stack left.
   */
  public void moveStackLeft() {
	  pusher.set(-Parameters.outfeedPusherVoltage);
  }

  /** 
   *  This method moves the leadscrew right to push a stack right.
   */
  public void moveStackRight() {
	  pusher.set(Parameters.outfeedPusherVoltage);
  }

  /** 
   *  This method returns true if the stack has left the outfeed, false otherwise.
   */
  public boolean isStackAllTheWayOut() {
	  return toteOut.get();
  }
  
  /** 
   *  This method returns true if a stack is in the outfeed, false otherwise.
   */
  public boolean isStackInOutfeed() {
	  return toteOut.get();
  }

}