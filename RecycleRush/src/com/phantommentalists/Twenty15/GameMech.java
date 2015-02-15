package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.Solenoid;

/*
 */
public class GameMech {

  public boolean autoStacking;

    private Stacker stacker;
    private GameMechState state;
    public Outfeed outfeed;
    private Infeed infeed;
    private Solenoid readyLight;
    private boolean fwd = true;
    private boolean autonomous = false;

  public GameMech()
  {
	  stacker = new Stacker();
	  outfeed = new Outfeed();
	  infeed = new Infeed();
  }
  
  public void raiseElevator()
  {
	  stacker.moveElevatorUp();
  }
  
  public void lowerElevator()
  {
	  stacker.moveElevatorDown();
  }
  
  public void stopElevator()
  {
	  stacker.stopElevator();
  }
  
  public void turnStackerConveyorOn(boolean fwd,double speed)
  {
	  if (fwd)
	  {
		  stacker.turnConveyorOn(speed);
	  }
	  else 
	  {
		  stacker.turnConveyorOn(-speed);
	  }
  }
  
  public void turnStackerConveyorOff()
  {
	  stacker.turnConveyorOff();
  }
  
  /** 
   *  This method will deploy all parts of the game mechanism which need to be deployed.
   */
  public void deployInfeed() {
	  infeed.deployInfeed();
  }
  
  public void retractInfeed()
  {
	  infeed.retractInfeed();
  }
  
  public void stopInfeed()
  {
	  infeed.stopInfeed();
  }
  
  public void turnOutFeedConveyorOn(double power)
  {
	  outfeed.moveStackForward(power);
  }
  
  public void turnOutFeedConveyorOff()
  {
	  outfeed.stopConveyor();
  }
  
  public void moveOutFeedArmLeft()
  {
	  outfeed.moveStackLeft();
  }
  
  public void moveOutFeedArmRight()
  {
	  outfeed.moveStackRight();
  }
  
  public void stopOutFeedArm()
  {
	  outfeed.stopPusher();
  }

  /** 
   *  This method handles the unloading of a finished stack.
   */
  public void unload() {
  }

  public void processGameMech(boolean auto) {
//	  if(auto)
//	  {
//		  autonomous = true;
//	  }
//	  else
//	  {
//		  autonomous = false;
//	  }
	  
	  stacker.processStacker();
	  outfeed.processOutfeed();
  }

  /** 
   *  This method returns true if the game mechanisms are empty, false otherwise.
   */
  public boolean isEmpty() {
  return false;
  }

  /** 
   *  This method instructs the stacker to create stacks of given height
   */
  public void createStack(int height) {
  }

  /** 
   *  This method returns true when the stacker is ready for a tote, false otherwise. This method will be called by PhantomOne when determining
   *  whether to light the red or green indicators.
   */
  public boolean isGameMechReadyForTote() {
  return stacker.isStackerReadyForTote();
  }

  /** 
   *  This method returns true if the robot is autonomously stacking totes, false otherwise.
   */
  public boolean isAutonomousStacking() {
  return false;
  }

  /** 
   *  This method tells the game mech whether or not to create stacks autonomously.
   */
  public void setAutonomousStacking(boolean auto) {
  }

}