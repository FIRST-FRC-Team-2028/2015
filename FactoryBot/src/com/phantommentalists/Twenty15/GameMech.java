package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.Relay;

/*
 */
public class GameMech {

	private boolean autoStacking;

    private Stacker stacker;
    private GameMechState state;
    private Outfeed outfeed;
    private Infeed infeed;
    private Relay readyLight;

  /** 
   *  This method will deploy all parts of the game mechanism which need to be deployed.
   */
  public void deployGameMech() {
  }

  /** 
   *  This method handles the unloading of a finished stack.
   */
  public void unload() {
  }

  public void processGameMech() {
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
  return false;
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