package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.Solenoid;

/*
 */
public class GameMech {

  public boolean autoStacking;

    private Stacker stacker;
    private GameMechState state;
    private Outfeed outfeed;
    private Infeed infeed;
    private Solenoid readyLight;
    private boolean fwd = true;
    private boolean autopilot = false;

  public GameMech()
  {
	  state = GameMechState.Unknown;
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
	  if(autopilot)
	  {
		  stacker.turnConveyorOn(fwd);
	  }
	  else 
	  {
		  if (fwd)
		  {
			  stacker.turnConveyorOn(fwd);
		  }
		  else 
		  {
			  stacker.turnConveyorOn(fwd);
		  }
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
  
  public void turnOutFeedConveyorOn(boolean fwd)
  {
	  outfeed.moveStackForward(fwd);
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

  public void processGameMech(int height) {
	  if(autopilot)
	  {
		  if(stacker.isStackDone())
		  {
			  stacker.emptyStacker();
			  outfeed.moveStackForward(true);
		  }
		  if(!outfeed.isConveying())
		  {
			  
		  }
	  }
	  if(infeed.isDeployed() && state == GameMechState.Unknown)
	  {
		  state = GameMechState.Deployed;
	  }
	  stacker.processStacker(height);
	  outfeed.processOutfeed();
	  infeed.processInfeed();
  }
  
  public void initAutoPilot()
  {
	  infeed.deployInfeed();
	  stacker.moveElevatorUp();
	  outfeed.moveStackRight();
  }
 
  public void setStackZero()
  {
	  if(outfeed.isConveying())
	  {
		  stacker.setStackToZero();
	  }
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
	  autopilot = auto;
	  stacker.setAutoPilot(auto);
	  outfeed.setAutoPilot(auto);
	  if(auto)
	  {
		  initAutoPilot();
	  }
  }

}