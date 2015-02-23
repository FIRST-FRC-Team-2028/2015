package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.PIDOutput;

public class TurnPIDOut implements PIDOutput {

	Drive drive;
	
	public TurnPIDOut(Drive d)
	{
		this.drive = d;
	}
  
  public void pidWrite(double output) {
	  drive.setTurn(-output);
  }

}