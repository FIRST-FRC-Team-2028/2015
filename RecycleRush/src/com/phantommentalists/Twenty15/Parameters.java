package com.phantommentalists.Twenty15;

import java.util.Date;

import edu.wpi.first.wpilibj.Joystick.AxisType;

public class Parameters {
	
  public static final int frontLeftCanId = 11;

  public static final int frontRightCanId = 12;

  public static final int rearLeftCanId = 13;

  public static final int rearRightCanId = 14;
  
//  Outfeed Values
   
  public static final int outfeedConveyorCANId = 31;
  public static final int outfeedArmCANId = 30;
  public static final int outfeedToteLimitSwitch = 0;
  public static final double outfeedConveyorVoltageSlow = 0.35;
  public static final double outfeedConveyorVoltageFast = .9;
  public static final int feedConveyorPuaseDelay = 1000;
  public static final double outfeedPusherVoltage = 1.0;
  
  public static final int stackerLeftCANId = 22;
  public static final int stackerRightCANId = 23;
  public static final int stackerConveyorCANId = 21;
  public static final int stackerToteIndicatorInput = 2;
  public static final double stackerConveyorVoltage = 1.0;
  public static final double stackerElevatorLeftUpVoltage = -1.0;
  public static final double stackerElevatorLeftDownVoltage = -stackerElevatorLeftUpVoltage;
  public static final double stackerElevatorRightUpVoltage = 0.92;
  public static final double stackerElevatorRightDownVoltage = -stackerElevatorRightUpVoltage;

  public static final double steeringProportionalValue = 0.05;

  public static final double steeringIntegralValue = 0.001;

  public static final double steeringDerivativeValue = 0.001;

  public static final double maxMotorVoltage = 12.0;

  public static final int infeedCANId = 40;

  public static final double infeedPower = 1.0;
  
  public static final int gyroAnalogIn = 0;
  public static final int tapeLeftAnalogIn = 3;
  public static final int taperightAnalogIn = 2;
  
  public static final double autoLiftTime = 0.6;
  public static final double autoDrivePower = 0.5;
  public static final double autoFailsafeCarpetTime = 3.0;
  public static final double autoFailsafeScoringTime = 3.0;
  public static final double autoTapeThreshHold = 450;
  public static final double autoScoreTime = 0.1;
  
  public static final double teleautomovetime = 2.5;
  
  public static final AxisType stackHeightSelect = AxisType.kX;
  public static final AxisType AutoSelect = AxisType.kY;
  
  public static final int driveStickId = 0;
  public static final int buttonStickId = 1;
  public static final int switchStickId = 2;
  public static final int axisStickId = 3;

  public static final double driveDampMult = 0.3;



}