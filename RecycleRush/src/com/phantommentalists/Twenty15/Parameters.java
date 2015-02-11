package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.Joystick.AxisType;

public class Parameters {
	
  public static final int frontLeftCanId = 11;

  public static final int frontRightCanId = 12;

  public static final int rearLeftCanId = 13;

  public static final int rearRightCanId = 14;
  
  public static final int stackerLeftCANId = 23;
  
  public static final int stackerRightCANId = 22;
  
  public static final int stackerConveyorCANId = 21;
  
//  Outfeed Values
   
  public static final int outfeedConveyorCANId = 31;
  public static final int outfeedArmCANId = 30;
  public static final int outfeedToteLimitSwitch = -1;
  public static final double outfeedConveyorVoltage = 12;
  public static final double outfeedPusherVoltage = 12;

  public static final int steeringProportionalValue = 0;

  public static final int steeringIntegralValue = 0;

  public static final int steeringDerivativeValue = 0;

  public static final double maxMotorVoltage = 12.0;
  
  //Button Values by Joystick
  
  	//GameMech
  	public static final int buttonPusherLeft = 1;
  	
  	public static final int buttonPusherRight = 2;
  	
  	public static final int buttonOutfeedConveyorFwd = 8;
  	
  	public static final int buttonOutfeedConveyorRev = 7;
  	
  	public static final int buttonStackerUp = 4;
  	
  	public static final int buttonStackerDown = 3;
  	
  	public static final int buttonStackerConveyorFwd = 10;
  	
  	public static final int buttonStackerConveyorRev = 9;
  	
  	public static final int buttonInfeedUp = 6;
  	
  	public static final int buttonInfeedDown = 5;
  	
  	public static final AxisType axisInfeedIndicator = AxisType.kX;
}
