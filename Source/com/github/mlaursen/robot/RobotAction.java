/**
 * 
 */
package com.github.mlaursen.robot;

/**
 * @author mlaursen
 *
 */
public class RobotAction implements Debugable {
  
  private final int DEFAULT_WAIT_TIME = 25;
  
  
  private boolean isWaitOnly;
  private RobotKey key;
  private int wait;
  public RobotAction(RobotKey key) {
    this.key = key;
    this.wait = DEFAULT_WAIT_TIME;
    isWaitOnly = false;
  }
  
  public RobotAction(RobotKey key, int wait) {
    this.key = key;
    this.wait = wait;
    isWaitOnly = false;
  }
  
  public RobotAction(int wait) {
    isWaitOnly = true;
    this.key = null;
    this.wait = wait;
  }
  
  /**
   * @return the key
   */
  public int getKey() {
    return key.getKeyCode();
  }
  
  /**
   * @return the wait
   */
  public int getWait() {
    return wait;
  }
  
  /**
   * @param wait the wait to set
   */
  public void setWait(int wait) {
    this.wait = wait;
  }
  
  /**
   * @return the isWaitOnly
   */
  public boolean isWaitOnly() {
    return isWaitOnly;
  }
  
  @Override
  public String toString() {
    return String.format("RobotAction [key=%s, wait=%d]", key.toString(), wait);
  }
  
  @Override
  public String getLogMessage() {
    return toString();
  }
}
