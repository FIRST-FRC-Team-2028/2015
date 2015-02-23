package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

/*
 * Author: Christopher D. Hooks
 */
public class Infeed {

      private CANTalon infeedDeploy;
      private InfeedStates state;

      public Infeed()
      {
    	  state = InfeedStates.retracted;
    	  infeedDeploy = new CANTalon(Parameters.infeedCANId);
    	  infeedDeploy.changeControlMode(ControlMode.PercentVbus);
    	  infeedDeploy.enableControl();
      }
      
  public void processInfeed()
  {
	  if(infeedDeploy.isFwdLimitSwitchClosed())
	  {
		  state = InfeedStates.deployed;
	  }
	  else
	  {
		  state = InfeedStates.retracted;
	  }
  }
      
  public void deployInfeed() {
	  infeedDeploy.set(Parameters.infeedPower);
	  
  }
  public void retractInfeed() {
	  infeedDeploy.set(-Parameters.infeedPower);
  }
  
  public void stopInfeed()
  {
	  infeedDeploy.set(0.0);
  }
  
  public boolean isDeployed()
  {
	  return (state == InfeedStates.deployed);
  }
  
  public boolean isRetracted()
  {
	  return (state == InfeedStates.retracted);
  }

}