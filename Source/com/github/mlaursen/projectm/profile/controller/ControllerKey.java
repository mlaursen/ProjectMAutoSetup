/**
 * 
 */
package com.github.mlaursen.projectm.profile.controller;

import java.util.List;
import java.util.ArrayList;

import com.github.mlaursen.projectm.Action;
import com.github.mlaursen.projectm.ScreenPosition;
import com.github.mlaursen.projectm.logging.Debug;
import com.github.mlaursen.projectm.utils.StringUtils;
import com.github.mlaursen.robot.RobotAction;

/**
 * @author mlaursen
 *
 */
public class ControllerKey extends ScreenPosition implements com.github.mlaursen.robot.Debugable {
  
  private ControllerKeyName name;
  private ControllerBinding binding;
  private ControllerBinding defaultBinding;
  public ControllerKey(ControllerKeyName name, ControllerBinding binding) {
    super(name.getX(), name.getY());
    this.name = name;
    this.binding = binding;
    this.defaultBinding = binding;
  }
  
  public void setBinding(ControllerBinding binding) {
    this.binding = binding;
  }
  
  /**
   * Gets the List of {@link RobotAction} required to re-map the 
   * controls for a profile
   * @return a List of {@link RobotAction}
   */
  public List<RobotAction> getRemapControls() {
    List<RobotAction> actions = new ArrayList<>();
    int x = getNumberOfChanges();
    Action dir = x > 0 ? Action.LEFT : Action.RIGHT;
    for(int i = 0; i < Math.abs(x); i++) {
      actions.add(dir.getRobotAction());
    }
    actions.add(Action.B.getRobotAction());
    return actions;
  }
  
  /**
   * @see ControllerBinding#getNumberOfInputTo(ControllerBinding)
   * @return a number of changes
   */
  private int getNumberOfChanges() {
    if((defaultBinding.isLimitedToCStick() && binding.isLimtiedToNonCStick()) 
        || (defaultBinding.isLimtiedToNonCStick() && binding.isLimitedToCStick())) {
      Debug.alywasDebug(this);
      return 0;
    }
    else {
      return binding.getNumberOfInputTo(defaultBinding);
    }
  }
  
  public boolean isNotDefault() {
    return !this.defaultBinding.equals(this.binding);
  }
  
  /* (non-Javadoc)
   * @see com.github.mlaursen.projectm.logging.Debugable#getLogMessage()
   */
  @Override
  public String getLogMessage() {
    return "The given binding: " + binding.name() + " is an invalid binding for " + this.name;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return StringUtils.getEnumPropertyName(name) + " = " + StringUtils.getEnumPropertyName(binding);
  }
  
}
