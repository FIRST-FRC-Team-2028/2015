package com.phantommentalists.Recycle Rush;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;

/*
 */
public class PhantomOne extends SampleRobot {

    private Drive drive;
    public GameMech gameMech;
    public final Parameters parameters;
    private Gyro gyro;
    private Odometer odometer;
    private PIDController driveController;
    private PIDController turnController;

  public void autonomous() {
  }

  public void operatorControl() {
  }

}