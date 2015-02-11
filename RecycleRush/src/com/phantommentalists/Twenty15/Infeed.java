package com.phantommentalists.Twenty15;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

/*
 * Author: Christopher D. Hooks
 */
public class Infeed {

	private CANTalon infeedDeploy;

	public Infeed() {
		infeedDeploy = new CANTalon(Parameters.infeedCANId);
		infeedDeploy.changeControlMode(ControlMode.PercentVbus);
		infeedDeploy.enableControl();
	}

	public void deployInfeed() {
		infeedDeploy.set(Parameters.infeedPower);

	}

	public void retractInfeed() {
		infeedDeploy.set(-Parameters.infeedPower);
	}

}