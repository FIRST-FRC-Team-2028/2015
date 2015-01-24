package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;

/*
 * Author: Hunter Lawrence
 */
public class Elevator {

      private CANTalon leftMotor;
      private CANTalon rightMotor;
  public Elevator(int leftCANID,int rightCANID)
  {
	  leftMotor = new CANTalon(leftCANID);
	  rightMotor = new CANTalon(rightCANID);
	  
	  //leftMotor.
	  
	  leftMotor.enableControl();
	  rightMotor.enableControl();
  }
  /** 
   *  This method moves the elevator side to the up position.
   */
  public void goUp() {
	  
  }

  /** 
   *  This method moves the elevator side to the down position
   */
  public void goDown() {
  }

  /** 
   *  This method return true if the elevator side is up, false otherwise.
   */
  public boolean isUp() {
  return false;
  }

  /** 
   *  This method returns true if the elevator side is down, false otherwise.
   */
  public boolean isDown() {
  return false;
  }

}