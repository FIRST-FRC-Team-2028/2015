package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.PIDOutput;

public class DrivePIDOut implements PIDOutput {

	Drive drive;
	
	public DrivePIDOut(Drive d)
	{
		this.drive = d;
	}
  
  public void pidWrite(double output) {
	  drive.setDrive(output);
  }

}