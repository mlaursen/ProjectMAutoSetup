/**
 * 
 */
package com.github.mlaursen.projectm.profile;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.projectm.Action;
import com.github.mlaursen.projectm.profile.controller.ControllerSetup;
import com.github.mlaursen.robot.Debugable;
import com.github.mlaursen.robot.RobotAction;

/**
 * @author mlaursen
 *
 */
public class Profile implements Debugable {
	
	private ProfileName name;
	private ControllerSetup controllerSetup;
	public Profile(String name, ControllerSetup controllerSetup) {
		this.name = new ProfileName(name);
		this.controllerSetup = controllerSetup;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name.getName();
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name.setName(name);
	}
	/**
	 * @return the controllerSetup
	 */
	public ControllerSetup getControllerSetup() {
		return controllerSetup;
	}
	/**
	 * @param controllerSetup the controllerSetup to set
	 */
	public void setControllerSetup(ControllerSetup controllerSetup) {
		this.controllerSetup = controllerSetup;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RobotAction> getProfileActions() {
		List<RobotAction> actions = new ArrayList<>();
		actions.addAll(name.getCreateNameActions());
		actions.add(Action.START.getRobotAction());
		actions.add(Action.A.getRobotAction(400));
		actions.addAll(controllerSetup.setupController());
		return actions;
	}
	
  @Override
  public String getLogMessage() {
  	String s = "[INFO]: The following profile has been added to the profile creation list with the following controller setup.\n";
  	s += "[PROFILE]: " + name + "\n";
  	s += controllerSetup.toString();
  	return s + "\n";
  }
	
	
}
