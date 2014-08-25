/**
 * 
 */
package com.github.mlaursen.projectm;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.robot.RobotAction;

/**
 * @author mlaursen
 *
 */
public class ScreenPosition {
	
	protected int x;
	protected int y;
	public ScreenPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
   * @return the x
   */
  public int getX() {
	  return x;
  }
  
  /**
   * @return the y
   */
  public int getY() {
	  return y;
  }
  
  protected int getNumberOfColumns(int row) {
  	return 3;
  }
  
  public List<RobotAction> getControlsFrom(int aWait) {
  	List<RobotAction> actions = new ScreenPosition(0, 0).getControlsTo(this);
  	if(aWait != 0) {
  		actions.get(actions.size() - 1).setWait(aWait);
  	}
  	return actions;
  }
  public List<RobotAction> getControlsFrom() {
  	return getControlsFrom(0);
  }
  
	public List<RobotAction> getControlsTo(ScreenPosition position) {
		List<RobotAction> actions = new ArrayList<>();
		if(x == position.x && y == position.y) {
  		actions.add(Action.DOWN.getRobotAction());
  		actions.add(Action.UP.getRobotAction());
  	}
  	
  	if(y != position.y) {
  		actions.addAll(getQuickestColumnRoute(position));
  	}
  	
  	if(x != position.x) {
			int boxDiff = x - position.x;
  		Action dir = boxDiff > 0 ? Action.UP : Action.DOWN;
  		boxDiff = Math.abs(boxDiff);
  		for(int i = 0; i < boxDiff; i++) {
  			actions.add(dir.getRobotAction());
  		}
		}
  	
  	actions.add(Action.A.getRobotAction());
  	return actions;
	}
	
	private int costLeft(ScreenPosition to) {
		int cost = 0;
		if(y > to.y) {
			cost = to.y - y;
		}
		else {
			cost = y + getNumberOfColumns(x) - to.y;
		}
		return Math.abs(cost);
	}
	
	private int costRight(ScreenPosition to) {
		int cost = 0;
		if(y < to.y) {
			cost = to.y - y;
		}
		else {
			cost = Math.abs(y - getNumberOfColumns(x)) + to.y;
		}
		return Math.abs(cost);
	}
	
  private List<RobotAction> getQuickestColumnRoute(ScreenPosition to) {
  	List<RobotAction> actions = new ArrayList<>();
  	int costLeft = costLeft(to);
  	int costRight = costRight(to);
  	Action dir = Action.RIGHT;
  	int cost = costRight;
  	if(costLeft  < costRight) {
  		dir = Action.LEFT;
  		cost = costLeft;
  	}
  	for(int i = 0; i < cost; i++) {
  		actions.add(dir.getRobotAction());
  	}
  	return actions;
  }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
  @Override
  public String toString() {
	  return "ScreenPosition [x=" + x + ", y=" + y + "]";
  }
  
}
