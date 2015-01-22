package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;

/*
 * CrabDrive allocation
 */
public class Drive {

    private CANTalon frontLeft;
    private CANTalon frontRight;
    private CANTalon rearLeft;
    private CANTalon rearRight;

    
    public Drive()
    {
    	frontLeft = new CANTalon(10);
    	frontRight = new CANTalon(20);
    	rearLeft = new CANTalon(30);
    	rearRight = new CANTalon(40);
    }
  /** 
   *  This method allows for driving forward and strafing.
   */
  public void setDrive(double forward, double strafe) {
  }

  /** 
   *  This method allows for sideways strafing and rotating.
   */
  public void setTurn(double strafe, double turn) {
  }

  public void processDrive() {
  }

  /** 
   *  This method allows for driving forward and rotating.
   */
  public void setTurnDrive(double forward, double turn) {
  }

}