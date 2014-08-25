/**
 * 
 */
package com.github.mlaursen.projectm.stages;

import com.github.mlaursen.projectm.ScreenPosition;

/**
 * @author mlaursen
 *
 */
public class StageScreenPosition extends ScreenPosition {

	/**
	 * @param x
	 * @param y
	 */
  public StageScreenPosition(int x, int y) {
	  super(x, y);
  }
	
  @Override
  protected int getNumberOfColumns(int row) {
  	if(row < 5) {
  		return 7;
  	}
  	else if(row == 5) {
  		return 6;
  	}
  	else return 1;
  }
  
}
