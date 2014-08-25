/**
 * 
 */
package com.github.mlaursen.projectm.profile.controller;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.projectm.Action;
import com.github.mlaursen.projectm.ScreenPosition;
import com.github.mlaursen.robot.RobotAction;

/**
 * @author mlaursen
 *
 */
public class ControllerSetup {
  
  private ControllerKey bumperLeft = new ControllerKey(ControllerKeyName.BUMPER_LEFT, ControllerBinding.SHIELD);
  private ControllerKey bumperRight = new ControllerKey(ControllerKeyName.BUMPER_RIGHT, ControllerBinding.SHIELD);
  private ControllerKey z = new ControllerKey(ControllerKeyName.Z, ControllerBinding.GRAB);
  private ControllerKey y = new ControllerKey(ControllerKeyName.Y, ControllerBinding.JUMP);
  private ControllerKey x = new ControllerKey(ControllerKeyName.X, ControllerBinding.JUMP);
  private ControllerKey a = new ControllerKey(ControllerKeyName.A, ControllerBinding.ATTACK);
  private ControllerKey b = new ControllerKey(ControllerKeyName.B, ControllerBinding.SPECIAL);
  private ControllerKey dPadUp = new ControllerKey(ControllerKeyName.D_PAD_UP, ControllerBinding.UP_TAUNT);
  private ControllerKey dPadSides = new ControllerKey(ControllerKeyName.D_PAD_SIDES, ControllerBinding.SIDE_TAUNT);
  private ControllerKey dPadDown = new ControllerKey(ControllerKeyName.D_PAD_DOWN, ControllerBinding.DOWN_TAUNT);
  private ControllerKey cStick = new ControllerKey(ControllerKeyName.C_STICK, ControllerBinding.SMASH);
  
  private boolean isTapJump = true;
  private List<ControllerKey> keys = new ArrayList<>();
  public ControllerSetup() {
    keys.add(bumperLeft);
    keys.add(bumperRight);
    keys.add(z);
    keys.add(y);
    keys.add(x);
    keys.add(a);
    keys.add(b);
    keys.add(dPadUp);
    keys.add(dPadSides);
    keys.add(dPadDown);
    keys.add(cStick);
  }
  
  private boolean isNeedingChange() {
    for(ControllerKey k : keys) {
      if(k.isNotDefault()) {
        return true;
      }
    }
    return !isTapJump;
  }
  
  public List<RobotAction> setupController() {
    List<RobotAction> actions = new ArrayList<>();
    if(!isNeedingChange()) {
      return actions;
    }
    actions.add(Action.A.getRobotAction(600));
    
    ScreenPosition current = new ScreenPosition(0, 0);
    ScreenPosition tapJump = new ScreenPosition(0, 2);
    actions.addAll(current.getControlsTo(new ScreenPosition(0, 2)));
    actions.addAll(tapJump.getControlsTo(current));
    actions.add(Action.B.getRobotAction());
    for(ControllerKey k : keys) {
      if(k.isNotDefault()) {
        actions.addAll(current.getControlsTo(k));
        actions.addAll(k.getRemapControls());
        current = k;
      }
    }
    
    actions.add(Action.B.getRobotAction());
    actions.add(Action.LEFT.getRobotAction());
    actions.add(Action.A.getRobotAction());
    actions.add(Action.A.getRobotAction(600));
    actions.add(Action.B.getRobotAction(1500));
    return actions;
  }
  
  
  public void setBumperLeft(ControllerBinding binding) {
    if(binding != null) {
      this.bumperLeft.setBinding(binding);
    }
  }
  
  public void setBumperRight(ControllerBinding binding) {
    if(binding != null) {
      this.bumperRight.setBinding(binding);
    }
  }
  
  public void setZ(ControllerBinding binding) {
    if(binding != null) {
      this.z.setBinding(binding);
    }
  }
  
  public void setY(ControllerBinding binding) {
    if(binding != null) {
      this.y.setBinding(binding);
    }
  }
  
  public void setX(ControllerBinding binding) {
    if(binding != null) {
      this.x.setBinding(binding);
    }
  }
  
  public void setA(ControllerBinding binding) {
    if(binding != null) {
      this.a.setBinding(binding);
    }
  }
  
  public void setB(ControllerBinding binding) {
    if(binding != null) {
      this.b.setBinding(binding);
    }
  }
  
  public void setDPadUp(ControllerBinding binding) {
    if(binding != null) {
      this.dPadUp.setBinding(binding);
    }
  }
  
  public void setDPadSides(ControllerBinding binding) {
    if(binding != null) {
      this.dPadSides.setBinding(binding);
    }
  }
  
  public void setDPadDown(ControllerBinding binding) {
    if(binding != null) {
      this.dPadDown.setBinding(binding);
    }
  }
  
  public void setCStick(ControllerBinding binding) {
    if(binding != null) {
      this.cStick.setBinding(binding);
    }
  }
  
  /**
   * @return the isTapJump
   */
  public boolean isTapJump() {
    return isTapJump;
  }
  
  /**
   * @param isTapJump the isTapJump to set
   */
  public void setTapJump(boolean isTapJump) {
    this.isTapJump = isTapJump;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    String s = "[CONTOLLER SETUP]: ";
    s += "isTapJump = " + isTapJump + ", ";
    s += bumperLeft + ", ";
    s += bumperRight + "\n[CONTOLLER SETUP]: ";
    s += z + ", ";
    s += y + ", ";
    s += x + ", ";
    s += a + ", ";
    s += b + "\n[CONTOLLER SETUP]: ";
    s += dPadUp + ", ";
    s += dPadSides + ", ";
    s += dPadDown + ", ";
    s += cStick + "\n";
    return s;
  }
  
  
}
