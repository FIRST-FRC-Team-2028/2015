package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

/*
 */
public class Stacker {

    private VictorSP rollerMotor;
    private StackerState state;
    private Elevator leftElevator;
    private Solenoid outfeedPin;
    private Elevator rightElevator;
    private DigitalInput toteIndicator;

  /** 
   *  This method indicates if the Stacker is empty. Returns true if the stacker is empty, false otherwise.
   */
  public boolean isEmpty() {
  return false;
  }

  /** 
   *  This method instructs the stacker to build a stack of totes given the supplied height.
   */
  public void createStack(int height) {
  }

  /** 
   *  Returns true when the current stack is finished, false otherwise
   */
  public boolean isStackDone() {
  return false;
  }

  /** 
   *  This method will empty the stacker by lowering the current stack (if necessary) and turning on the conveyor/rollers.
   */
  public void emptyStacker() {
  }

  /** 
   *  This method will open the outfeed locking pin.
   */
  public void openOutfeed() {
  }

  /** 
   *  This method returns true when the elevator is in the up position when we are building a stack or 
   *  the stacker is empty, false otherwise. This method will be called by GameMech when determining
   *  whether to light the red or green indicators.
   */
  public boolean isStackerReadyForTote() {
  return false;
  }

  /** 
   *  This method moves the elevator to the up position.
   */
  public void moveElevatorUp() {
  }

  /** 
   *  This method moves the elevator to the down position.
   */
  public void moveElevatorDown() {
  }

  /** 
   *  This method closes the outfeed locking pin.
   */
  public void closeOutfeed() {
  }

  /** 
   *  This method returns the Stacker's current state.
   */
  public StackerState getState() {
  return null;
  }

  /** 
   *  This method returns true if the Stacker is building a stack (autonomously), false otherwise.
   */
  public boolean isStackUnderConstruction() {
  return false;
  }

  /** 
   *  This method returns true if the elevator is up, false otherwise.
   */
  public boolean isElevatorUp() {
  return false;
  }

  /** 
   *  This method returns true if the elevator is down, false otherwise.
   */
  public boolean isElevatorDown() {
  return false;
  }

  public boolean toteReadyForStack() {
  return false;
  }

}