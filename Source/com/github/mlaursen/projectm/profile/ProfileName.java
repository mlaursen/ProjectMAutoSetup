/**
 * 
 */
package com.github.mlaursen.projectm.profile;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.robot.RobotAction;

/**
 * @author mlaursen
 *
 */
public class ProfileName {
	
	private String name;
	public ProfileName(String name) {
		this.name = name;
	}
	
	public List<RobotAction> getCreateNameActions() {
		List<RobotAction> actions = new ArrayList<>();
		LetterPosition from = new LetterPosition(0, 0, 0);
		for(Character c : name.toCharArray()) {
			char upper = Character.toUpperCase(c);
			LetterPosition to = LetterPositionMap.getLetterPosition(upper);
			to.updateToCase(c);
			actions.addAll(from.getControlsTo(to));
			from = to;
		}
		return actions;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
