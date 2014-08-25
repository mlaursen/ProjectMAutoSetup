/**
 * 
 */
package com.github.mlaursen.robot;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.projectm.UserSettings;
import com.github.mlaursen.projectm.logging.Debug;

/**
 * @author mlaursen
 *
 */
public class Robot {
	
	private static final int DEFAULT_KEY_EVENT_DURATION = 35;
	
	/**
	 * This is how long a key will be pressed. If {@link #setKeyEventDuration(int)}
	 * is not called, <tt>{@value #DEFAULT_KEY_EVENT_DURATION}</tt> will be used
	 */
	private int keyEventDuration;
	private RobotSettings settings;
	private java.awt.Robot r;
	private List<RobotAction> actions = new ArrayList<>();
  public Robot(UserSettings settings) throws AWTException {
  	r = new java.awt.Robot();
  	this.settings = settings;
  	this.keyEventDuration = DEFAULT_KEY_EVENT_DURATION;
  }
  
  /**
   * Once the Robot has been set up. You can automate all the actions
   * with this command.  It will go through each {@link RobotAction}
   * and press the key for the {@link #keyEventDuration}. Once the key has
   * been pressed, it will delay for {@link UserSettings#getLatency() user specified latency}
   * and then wait for the {@link RobotAction#getWait() RobotAction's wait time}
   */
  public synchronized void automate() {
  	for(RobotAction a : actions) {
  		sendAction(a);
  	}
  }
  
  private synchronized void sendAction(RobotAction a) {
  	Debug.debug(a, settings);
  	
  	r.keyPress(a.getKey());
  	r.delay(keyEventDuration);
  	r.keyRelease(a.getKey());
  	
  	r.delay(settings.getLatency());
  	r.delay(a.getWait());
  }
  
  /**
   * @param keyEventDuration the keyEventDuration to set
   */
  public void setKeyEventDuration(int keyEventDuration) {
	  this.keyEventDuration = keyEventDuration;
  }
  
  /**
   * 
   * @param action a {@link RobotAction} to add the the list of actions
   */
  public void addAction(RobotAction action) {
  	this.actions.add(action);
  }
  
  /**
   * 
   * @param actions
   */
  public void addActions(List<RobotAction> actions) {
  	this.actions.addAll(actions);
  }
  
  /**
   * 
   * @param action
   * @param amount
   */
  public void addRepeatingAction(RobotAction action, int amount) {
  	for(int i = 0; i < amount; i++) {
  		addAction(action);
  	}
  }
  
  /**
   * 
   * @return
   */
  public List<RobotAction> getActions() {
  	return this.actions;
  }
}
