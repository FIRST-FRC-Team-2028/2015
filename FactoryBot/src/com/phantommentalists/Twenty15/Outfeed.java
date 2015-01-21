package com.phantommentalists.Twenty15;

import java.util.Vector;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;

/*
 */
public class Outfeed {

      private VictorSP pusher;
    private VictorSP roller;
    private DigitalInput leftLimit;
    private DigitalInput rightLimit;
    private DigitalInput toteOut;
    public Vector  outfeedDeploy;

  /** 
   *  This method will move a stack forward in the outfeed.
   */
  public void moveStackForward() {
  }

  /** 
   *  This method moves the leadscrew left to push a stack left.
   */
  public void moveStackLeft() {
  }

  /** 
   *  This method moves the leadscrew right to push a stack right.
   */
  public void moveStackRight() {
  }

  /** 
   *  This method returns true if the stack has left the outfeed, false otherwise.
   */
  public boolean isStackAllTheWayOut() {
  return false;
  }

  /** 
   *  This method retracts the pushing arm.
   *  NOTE : MAY NOT BE USED/NECESSARY.
   */
  public void retractPusher() {
  }

  /** 
   *  This method deploys the outfeed.
   */
  public void deployOutfeed() {
  }

  /** 
   *  This method returns true if a stack is in the outfeed, false otherwise.
   */
  public boolean isStackInOutfeed() {
  return false;
  }

}