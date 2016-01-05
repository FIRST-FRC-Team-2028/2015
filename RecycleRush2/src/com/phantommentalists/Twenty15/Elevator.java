package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * Author: Hunter Lawrence
 */
public class Elevator {

  private enum State{
	up,down,stop
  }
  private CANTalon leftMotor;
  private CANTalon rightMotor;
  private AnalogInput rightpot;
  private AnalogInput leftpot;
  private State state = State.stop;
  private int deadband = 100;
  private double rightV,leftV;
  private int curL=0,curR=0;
  private int lastL = 0,lastR=0;
  private int sumL =0,sumR=0;
  private Compressor mag;
  public Elevator(int leftCANID,int rightCANID)
  {
	  
	  mag = new Compressor();
	  mag.setClosedLoopControl(false);
	  
	  leftMotor = new CANTalon(leftCANID);
	  rightMotor = new CANTalon(rightCANID);
	  
	  if(Parameters.stackerElevatorSpeed)
	  {
		  leftMotor.changeControlMode(ControlMode.Speed);
		  rightMotor.changeControlMode(ControlMode.Speed);
		  
		  leftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		  rightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		  
		  rightMotor.setPID(3, 0.065, 0.05);
		  leftMotor.setPID(3, 0.065, 0.05);
	  }
	  else
	  {
		  leftMotor.changeControlMode(ControlMode.PercentVbus);
		  rightMotor.changeControlMode(ControlMode.PercentVbus);
	  }
	  
	  leftMotor.enableBrakeMode(true);
	  rightMotor.enableBrakeMode(true);
	  
	  leftMotor.enableLimitSwitch(true, true);
	  rightMotor.enableLimitSwitch(true, true);
	  
	  leftMotor.enableControl();
	  rightMotor.enableControl();
  }
  public void processElevator()
  {
//	  curL = leftpot.getValue();
//	  curR = rightpot.getValue();
	  SmartDashboard.putBoolean("Stacker up left limit", leftMotor.isRevLimitSwitchClosed());
	  SmartDashboard.putBoolean("Stacker up right limit", rightMotor.isFwdLimitSwitchClosed());
	  SmartDashboard.putBoolean("Stacker down left limit", leftMotor.isFwdLimitSwitchClosed());
	  SmartDashboard.putBoolean("Stacker down right limit", rightMotor.isRevLimitSwitchClosed());
	  SmartDashboard.putBoolean("isUp", isUp());
	  SmartDashboard.putBoolean("isDown",isDown());
	  SmartDashboard.putNumber("Left Elev", leftMotor.getOutputCurrent());
	  SmartDashboard.putNumber("Right Elev",rightMotor.getOutputCurrent());
//	  SmartDashboard.putNumber("Right Pot",rightpot.getValue());
	  SmartDashboard.putNumber("Left Speed",leftMotor.getClosedLoopError());
//	  SmartDashboard.putNumber("Left Sum", sumL);
//	  SmartDashboard.putNumber("Right Num", sumR);
//	  System.out.println(rightpot.getValue());
//	  switch(state)
//	  {
//	  case up:
//		  calculateValues();
//		  if(sumR > sumL+deadband)
//		  {
//			  rightV -= 0.02;
//			  if(rightV < 0.6)
//			  {
//				  rightV = 0.6;
//			  }
//		  }
//		  if(sumR < sumL-deadband)
//		  {
//			  rightV += 0.02;
//			  if(rightV > 1.0)
//			  {
//				  rightV = 1.0;
//			  }
//		  }
//		  leftMotor.set(Parameters.stackerElevatorLeftUpVoltage*leftV);
//		  rightMotor.set(Parameters.stackerElevatorRightUpVoltage*rightV);
//		  break;
//	  case down:
//		  leftMotor.set(Parameters.stackerElevatorLeftDownVoltage);
//		  rightMotor.set(Parameters.stackerElevatorRightDownVoltage);
//		  break;
//	  }
//	  if(leftMotor.isRevLimitSwitchClosed() || rightMotor.isFwdLimitSwitchClosed())
//	  {
//		  setMag(true);
//	  }
//	  if(isUp() || isDown())
//	  {
//		  stop();  
//	  } 
//	  if (state == State.up)
//	  {
//		  leftMotor.set(Parameters.stackerElevatorLeftUpVoltage);
//	  }
//	  else if(state == State.down)
//	  {
//		  leftMotor.set(Parameters.stackerElevatorLeftDownVoltage);
//	  }
//	  else
//	  {
//		  leftMotor.set(0);
//	  }
  }
  
  public void setMag(boolean on)
  {
	  if(on)
	  {
		  mag.start();
	  }
	  else
	  {
		  mag.stop();
	  }
  }
  
  /** 
   *  This method moves the elevator side to the up position.
   */
  public void goUp() {
	  state = State.up;
	  if(Parameters.stackerElevatorSpeed)
	  {
		  leftMotor.set(Parameters.stackerElevatorLeftUpSpeed);
		  rightMotor.set(Parameters.stackerElevatorRightUpSpeed);
	  }
	  else
	  {
		  leftMotor.set(Parameters.stackerElevatorLeftUpVoltage);
		  rightMotor.set(Parameters.stackerElevatorRightUpVoltage);
	  }
  }

  /** 
   *  This method moves the elevator side to the down position
   */
  public void goDown() {
//	  setMag(false);
	  state = State.down;
	  if(Parameters.stackerElevatorSpeed)
	  {
		  leftMotor.set(Parameters.stackerElevatorLeftDownSpeed);
		  rightMotor.set(Parameters.stackerElevatorRightDownSpeed);		  
	  }
	  else
	  {
		  leftMotor.set(Parameters.stackerElevatorLeftDownVoltage);
		  rightMotor.set(Parameters.stackerElevatorRightDownVoltage);
	  }
  }
  /**
   *  This method stops the elevator side
   */
  public void stop()
  {
	  state = State.stop;
	  leftMotor.set(0.0);
	  rightMotor.set(0.0);
  }
  public boolean isRaising()
  {
	  if(leftMotor.getBusVoltage() > 0.0 && rightMotor.getBusVoltage() > 0.0)
	  {
		  return true;
	  }
	  else
	  {
		  return false;
	  }
  }
  public boolean isLowering()
  {
	  if(leftMotor.getBusVoltage() > 0.0 && rightMotor.getBusVoltage() > 0.0)
	  {
		  return true;
	  }
	  else
	  {
		  return false;
	  }
  }
  /** 
   *  This method return true if the elevator side is up, false otherwise.
   */
  public boolean isUp() {
	  //TODO: Just thrown in there fwd/rev limit switches not known
	  if(leftMotor.isRevLimitSwitchClosed() && rightMotor.isFwdLimitSwitchClosed())
		  return true;
	  else
		  return false;
  }

  /** 
   *  This method returns true if the elevator side is down, false otherwise.
   */
  public boolean isDown() {
	  //TODO: Same as the TODO in isUp()
	  if(leftMotor.isFwdLimitSwitchClosed() && rightMotor.isRevLimitSwitchClosed())
		  return true;
	  else
		  return false;
  }

}