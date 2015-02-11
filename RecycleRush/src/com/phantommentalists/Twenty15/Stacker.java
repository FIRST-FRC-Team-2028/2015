package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;

/*
 * Author: Hunter Lawrence
 */
public class Stacker {

    private CANTalon conveyorMotor;
    private StackerState state;
    private Elevator elevator;
    private DigitalInput toteIndicator;
    private int currentstackheight = 0;
    private int desiredstackheight = -1;
    private boolean autonomous = false;

  public Stacker()
  {
//	  state = StackerState.Unknown;
	  elevator = new Elevator(Parameters.stackerLeftCANId,Parameters.stackerRightCANId);
	  conveyorMotor = new CANTalon(Parameters.stackerConveyorCANId);
	  conveyorMotor.changeControlMode(ControlMode.PercentVbus);
	  conveyorMotor.enableControl();
  }
  public void processStacker()
  {
	  if(toteIndicator.get() && isElevatorDown())
	  {
		  currentstackheight++;
	  }
	  if(state == StackerState.Unknown)
	  {
		  moveElevatorUp();
	  }
	  if(isElevatorUp() && state == StackerState.RaiseingElevator)
	  {
		  state = StackerState.WaitingForTote;
	  }
	  if(state == StackerState.LoweringElevator && isElevatorDown())
	  {
		  state = StackerState.TotePickedUp;
	  }
	  if(state == StackerState.Unloading)
	  {
		  if(isConveyorOn() && !toteIndicator.get())
		  {
			  moveElevatorUp();
			  currentstackheight = 0;
		  }
		  else if(!isConveyorOn())
		  {
			  emptyStacker();
		  }
	  }
	  elevator.processElevator();
  }
  /** 
   *  This method indicates if the Stacker is empty. Returns true if the stacker is empty, false otherwise.
   */
  public boolean isEmpty() {
  return (currentstackheight == 0 && !toteIndicator.get());
  }

  /** 
   *  This method instructs the stacker to build a stack of totes given the supplied height.
   */
  public void createStack(int height) {
	  desiredstackheight = height;
  }

  /** 
   *  Returns true when the current stack is finished, false otherwise
   */
  public boolean isStackDone() {
	  if(currentstackheight == desiredstackheight)
	  {
		  return true;
	  }
	  else
	  {
		  return false;
	  }
  }

  /** 
   *  This method will empty the stacker by lowering the current stack (if necessary) and turning on the conveyor/rollers.
   */
  public void emptyStacker() {
	  if(state == StackerState.WaitingForTote)
	  {
		  moveElevatorDown();
		  state = StackerState.Unloading;
	  }
	  else if(state == StackerState.Unloading && isElevatorDown())
	  {
		  conveyorMotor.set(1.0);
	  }
  }
  /** 
   *  This method returns true when the elevator is in the up position when we are building a stack or 
   *  the stacker is empty, false otherwise. This method will be called by GameMech when determining
   *  whether to light the red or green indicators.
   */
  public boolean isStackerReadyForTote() {
	  if(state == StackerState.WaitingForTote && isConveyorOn())
	  {
		  return true;
	  }
	  else
	  {
		  return false;
	  }
  }

  /** 
   *  This method moves the elevator to the up position.
   */
  public void moveElevatorUp() {
	  elevator.goUp();
	  state = StackerState.RaiseingElevator;
  }

  /** 
   *  This method moves the elevator to the down position.
   */
  public void moveElevatorDown() {
	  elevator.goDown();
	  state = StackerState.LoweringElevator;
  }
  
  public void stopElevator()
  {
	  elevator.stop();
  }
  
  public void turnConveyorOn(boolean fwd)
  {
	  if(fwd)
		  conveyorMotor.set(Parameters.stackerConveyorVoltage);
	  else
		  conveyorMotor.set(-Parameters.stackerConveyorVoltage);
  }
  
  public void turnConveyorOff()
  {
	  conveyorMotor.set(0.0);
  }
  
  /** 
   *  This method returns the Stacker's current state.
   */
  public StackerState getState() {
	  return state;
  }
  public boolean isConveyorOn()
  {
	  if(conveyorMotor.getBusVoltage() != 0.0)
	  {
		  return true;
	  }
	  else
	  {
		  return false;
	  }
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
	  if(elevator.isUp())
		  return true;
	  else
		  return false;
  }

  /** 
   *  This method returns true if the elevator is down, false otherwise.
   */
  public boolean isElevatorDown() {
	  if(elevator.isDown())
		  return true;
	  else
		  return false;
  }

  public boolean toteReadyForStack() {
  return false;
  }

}
