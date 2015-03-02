package com.phantommentalists.Twenty15;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    private boolean autopilot = false;
    private boolean canAddTote = true;
    private boolean centered = false;
    
  public class delay extends TimerTask
  {
	  public void run()
	  {
		  centered = true;
	  }
  }

  public Stacker()
  {
	  state = StackerState.Unknown;
	  toteIndicator = new DigitalInput(2);
	  elevator = new Elevator(Parameters.stackerLeftCANId,Parameters.stackerRightCANId);
	  conveyorMotor = new CANTalon(Parameters.stackerConveyorCANId);
	  conveyorMotor.changeControlMode(ControlMode.PercentVbus);
	  conveyorMotor.enableControl();
  }
  public void processStacker(int height)
  {
	  desiredstackheight = height;
	  SmartDashboard.putBoolean("Stacker tote indicator", toteIndicator.get());
	  SmartDashboard.putNumber("Stack count", currentstackheight);
	  SmartDashboard.putNumber("Desired Stack", desiredstackheight);
	  SmartDashboard.putString("Stacker state", state.toString());
	  SmartDashboard.putNumber("Stacker Conveyor Current", conveyorMotor.getOutputCurrent());
	  if(autopilot)
	  {
		  if(state == StackerState.Unknown)
		  {
			  moveElevatorUp();
		  }
		  else if(isElevatorUp() && state == StackerState.RaiseingElevator)
		  {
			  state = StackerState.WaitingForTote;
			  canAddTote = true;
//			  turnConveyorOn(true);
		  }
		  else if(state == StackerState.WaitingForTote && !toteIndicator.get())
		  {
			  moveElevatorDown();
		  }
		  
		  else if(state == StackerState.TotePickedUp)
		  {
			  if(!isStackDone())
			  {
				  moveElevatorUp();
			  }
		  }
		  else if(state == StackerState.LoweringElevator && isElevatorDown())
		  {
			  state = StackerState.TotePickedUp;
			  if(!toteIndicator.get() && canAddTote)
			  {
				  currentstackheight++;
				  canAddTote = false;
			  }
		  }
		  else if(state == StackerState.Unloading)
		  {
			  if(isConveyorOn() && toteIndicator.get())
			  {
				  moveElevatorUp();
				  currentstackheight = 0;
			  }
			  else if(!isConveyorOn())
			  {
				  emptyStacker();
			  }
		  }
		  if(state != StackerState.Unloading)
		  {
			  if(toteIndicator.get())
			  {
				  turnConveyorOn(true);
			  }
			  else
			  {
				  turnConveyorOn(false);
			  }
		  }
	  }
	  else
	  {
		  if(!toteIndicator.get() && isElevatorDown() && canAddTote)
		  {
			  currentstackheight++;
			  canAddTote = false;
		  }
		  else if(isElevatorUp() && !canAddTote)
		  {
			  canAddTote = true;
		  }
	  }
	  elevator.processElevator();
  }
  
  public void resetStackerState()
  {
	  state = StackerState.Unknown;
  }
  
  public void setStackToZero()
  {
	  currentstackheight = 0;
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
	  if(currentstackheight >= desiredstackheight)
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
	  if(state == StackerState.TotePickedUp)
	  {
		  conveyorMotor.set(Parameters.stackerConveyorVoltage);
		  state = StackerState.Unloading;
	  }
	  else if(state == StackerState.TotePickedUp)
	  {
		  conveyorMotor.set(Parameters.stackerConveyorVoltage);
		  state = StackerState.Unloading;
	  }
//	  if(state == StackerState.WaitingForTote)
//	  {
//		  moveElevatorDown();
//		  state = StackerState.Unloading;
//	  }
//	  else if(isElevatorDown())
//	  {
//		  state = StackerState.Unloading;
//	  }
//	  else if(state == StackerState.Unloading)
//	  {
//		  conveyorMotor.set(Parameters.stackerConveyorVoltage);
//	  }
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
	  turnConveyorOn(false);
	  state = StackerState.LoweringElevator;
  }
  
  public void stopElevator()
  {
	  elevator.stop();
  }
  
  public void turnConveyorOn(boolean fwd)
  {
	  if(autopilot)
	  {
//		  if(toteIndicator.get())
//		  {
		  if(fwd)
			  conveyorMotor.set(Parameters.stackerConveyorVoltage);
		  else
			  conveyorMotor.set(-Parameters.stackerConveyorVoltage);

//		  }
//		  else
//		  {
//			  turnConveyorOff();
//		  }
	  }
	  else
	  {		
		  if(fwd)
		  {
			  if(elevator.isUp() && toteIndicator.get())
			  {
				  conveyorMotor.set(Parameters.stackerConveyorVoltage);
			  }
			  else if(elevator.isDown())
			  {
				  conveyorMotor.set(Parameters.stackerConveyorVoltage);
			  }
			  else
			  {
				  turnConveyorOff();
			  }
		  }
		  else
		  {
//			  if(elevator.isUp() && toteIndicator.get())
//			  {
				  conveyorMotor.set(-Parameters.stackerConveyorVoltage);
//			  }
//			  else if(elevator.isDown())
//			  {
//				  conveyorMotor.set(-Parameters.stackerConveyorVoltage);
//			  }
//			  else
//			  {
//				  turnConveyorOff();
//			  }
		  }
	  }
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
	  return elevator.isUp();
  }

  /** 
   *  This method returns true if the elevator is down, false otherwise.
   */
  public boolean isElevatorDown() {
	  return elevator.isDown();
  }
  
  public void setAutoPilot(boolean value)
  {
	  autopilot = value;
  }

  public boolean toteReadyForStack() {
  return false;
  }
  
  public boolean isWaiting()
  {
	  return (state == StackerState.WaitingForTote);
  }
  
  public boolean isRaising()
  {
	  return (state == StackerState.RaiseingElevator);
  }
  
  public boolean isLowering()
  {
	  return (state == StackerState.LoweringElevator);
  }
  
  public boolean isTotePickedUp()
  {
	  return (state == StackerState.TotePickedUp);
  }
  
  public boolean isUnloading()
  {
	  return (state == StackerState.Unloading);
  }
  
  public boolean isUnknown()
  {
	  return (state == StackerState.Unknown);
  }
}
