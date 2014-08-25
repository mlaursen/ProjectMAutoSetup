/**
 * 
 */
package com.github.mlaursen.projectm.profile;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.projectm.Action;
import com.github.mlaursen.projectm.ScreenPosition;
import com.github.mlaursen.robot.Debug;
import com.github.mlaursen.robot.RobotAction;

/**
 * @author mlaursen
 *
 */
public class LetterPosition extends ScreenPosition {
  private int numberOfAPresses;
  public LetterPosition(int x, int y, int numberOfAPresses) {
    super(x, y);
    this.numberOfAPresses = numberOfAPresses;
  }
  
  public int getNumberOfAPresses() {
    return this.numberOfAPresses;
  }
  
  public void updateToCase(Character c) {
    Character lower = Character.toLowerCase(c);
    if(lower.equals(c)) {
      numberOfAPresses *= 2;
    }
  }
  
  
  @Override
  public List<RobotAction> getControlsTo(ScreenPosition position) {
    List<RobotAction> actions = new ArrayList<>();
    if(!(position instanceof LetterPosition)) {
      Debug.debug("Invalid screen position to mvoe from.");
      return actions;
    }
    actions.addAll(super.getControlsTo(position));
    for(int i = 1; i < ((LetterPosition) position).getNumberOfAPresses(); i++) {
      actions.add(Action.A.getRobotAction());
    }
    return actions;
  }
  
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "LetterPosition [x=" + x + ", y=" + y + ", numberOfAPresses=" + numberOfAPresses + "]";
  }
  
  
}
