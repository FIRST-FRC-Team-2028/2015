package com.phantommentalists.Twenty15;

public class Parameters {
	
  public static final int frontLeftCanId = 11;

  public static final int frontRightCanId = 12;

  public static final int rearLeftCanId = 13;

  public static final int rearRightCanId = 14;
  
//  Outfeed Values
   
  public static final int outfeedConveyorCANId = 31;
  public static final int outfeedArmCANId = 30;
  public static final int outfeedToteLimitSwitch = -1;
  public static final double outfeedConveyorVoltage = 0.5;
  public static final double outfeedPusherVoltage = 1;
  
  public static final int stackerLeftCANId = 22;
  public static final int stackerRightCANId = 23;
  public static final int stackerConveyorCANId = 21;
  public static final double stackerConveyorVoltage = 1.0;
  public static final double stackerElevatorUpVoltage = 0.5;
  public static final double stackerElevatorDownVoltage = -stackerElevatorUpVoltage;

  public static final int steeringProportionalValue = 0;

  public static final int steeringIntegralValue = 0;

  public static final int steeringDerivativeValue = 0;

  public static final double maxMotorVoltage = 12.0;

  public static int infeedCANId = 40;

  public static double infeedPower = 0.5;

}
