package com.phantommentalists.Twenty15;

public class Parameters {
	
  public static final int frontLeftCanId = 11;

  public static final int frontRightCanId = 12;

  public static final int rearLeftCanId = 13;

  public static final int rearRightCanId = 14;
  
//  Outfeed Values
   
  public static final int outfeedConveyorCANId = 31;
  public static final int outfeedArmCANId = 30;
  public static final int outfeedToteLimitSwitch = 0;
  public static final double outfeedConveyorVoltage = 1.0;
  public static final double outfeedPusherVoltage = 1.0;
  
  public static final int stackerLeftCANId = 22;
  public static final int stackerRightCANId = 23;
  public static final int stackerConveyorCANId = 21;
  public static final double stackerConveyorVoltage = 1.0;
  public static final double stackerElevatorUpVoltage = 0.5;
  public static final double stackerElevatorDownVoltage = -stackerElevatorUpVoltage;

  public static final double steeringProportionalValue = 0.05;

  public static final double steeringIntegralValue = 0.001;

  public static final double steeringDerivativeValue = 0.001;

  public static final double maxMotorVoltage = 12.0;

  public static final int infeedCANId = 40;

  public static final double infeedPower = 1.0;
  
  public static final int gyroAnalogIn = 0;
  public static final int tapeLeftAnalogIn = 1;
  public static final int taperightAnalogIn = 2;
  
  public static final double autoLiftTime = 0.333;
  public static final double autoDrivePower = 0.5;
  public static final double autoFailsafeCarpetTime = 7.5;
  public static final double autoFailsafeScoringTime = 5.0;
  public static final double autoTapeThreshHold = 450;
  public static final double autoScoreTime = 0.1;

}
