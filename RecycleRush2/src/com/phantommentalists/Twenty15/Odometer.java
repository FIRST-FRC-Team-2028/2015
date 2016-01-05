package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.PIDSource;

public class Odometer implements PIDSource {

      public BuiltInAccelerometer  myBuiltInAccelerometer;

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return 0;
	}

}