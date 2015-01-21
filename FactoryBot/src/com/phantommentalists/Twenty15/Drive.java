package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;

/*
 * CrabDrive allocation
 */
public class Drive {

      private static CANTalon frontLeft;
    private CANTalon frontRight;
    private CANTalon rearLeft;
    private CANTalon rearRight;

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