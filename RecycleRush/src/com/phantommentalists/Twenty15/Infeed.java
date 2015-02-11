package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

/*
 */
public class Infeed {

      private CANTalon infeedDeploy;

      public Infeed()
      {
    	  infeedDeploy = new CANTalon(Parameters.infeedCANId);
    	  infeedDeploy.changeControlMode(ControlMode.PercentVbus);
    	  infeedDeploy.enableControl();
      }
      
  public void deployInfeed() {
	  
  }

}