/**
 * 
 */
package com.github.mlaursen.projectm;

import com.github.mlaursen.robot.RobotAction;
import com.github.mlaursen.robot.RobotKey;

/**
 * @author mlaursen
 *
 */
public enum Action implements RobotKey {
	UP,
	DOWN,
	LEFT,
	RIGHT,
	START,
	B,
	A;
	
	public RobotAction getRobotAction() {
		return new RobotAction(this);
	}
	
	public RobotAction getRobotAction(int wait) {
		return new RobotAction(this, wait);
	}
	
  @Override
  public int getKeyCode() {
	  return UserSettings.getKey(this);
  }
}
