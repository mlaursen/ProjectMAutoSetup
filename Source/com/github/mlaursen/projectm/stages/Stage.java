/**
 * 
 */
package com.github.mlaursen.projectm.stages;

import com.github.mlaursen.projectm.ScreenPosition;
import com.github.mlaursen.robot.Debugable;

/**
 * @author mlaursen
 *
 */
public class Stage implements Debugable {
  
  private boolean isEnabled;
  private StageName stageName;
  private ScreenPosition position;
  
  /**
   * @param stageName
   * @param isEnabled
   */
  public Stage(StageName stageName, boolean isEnabled) {
    this.stageName = stageName;
    this.isEnabled = isEnabled;
    this.position = stageName.getPosition();
  }
  
  /**
   * @return the isEnabled
   */
  public boolean isEnabled() {
    return isEnabled;
  }
  
  /**
   * @return the position
   */
  public ScreenPosition getPosition() {
    return position;
  }
  
  /**
   * @return the stageName
   */
  public StageName getStageName() {
    return stageName;
  }
  
  @Override
  public String toString() {
    return this.stageName.pretty() + " " + (isEnabled ? "enabled" : "disabled");
  }
  
  /* (non-Javadoc)
   * @see com.github.mlaursen.robot.Debugable#getLogMessage()
   */
  @Override
  public String getLogMessage() {
    return "[STAGES]: " + this.stageName.pretty() + " has been enabled.";
  }
}
