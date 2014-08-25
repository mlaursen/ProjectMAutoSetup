/**
 * 
 */
package com.github.mlaursen.projectm.profile.controller;

import com.github.mlaursen.projectm.utils.StringUtils;

/**
 * @author mlaursen
 *
 */
public enum ControllerBinding {
	ATTACK,
	SPECIAL,
	JUMP,
	SHIELD,
	GRAB,
	SMASH(LimitedTo.C_STICK),
	UP_TAUNT(LimitedTo.NON_C_STICK),
	SIDE_TAUNT(LimitedTo.NON_C_STICK),
	DOWN_TAUNT(LimitedTo.NON_C_STICK);
	
	private LimitedTo limitedTo;
	private ControllerBinding() {
		this.limitedTo = LimitedTo.NONE;
	}
	
	private ControllerBinding(LimitedTo limitedTo) {
		this.limitedTo = limitedTo;
	}
	

	public boolean isLimitedToCStick() {
		return this.limitedTo.equals(LimitedTo.C_STICK);
	}
	
	public boolean isLimtiedToNonCStick() {
		return this.limitedTo.equals(LimitedTo.NON_C_STICK);
	}
	
	public static ControllerBinding fromString(String s) {
		for(ControllerBinding cb : values()) {
			if(StringUtils.getEnumPropertyName(cb).equalsIgnoreCase(s)) {
				return cb;
			}
		}
		return null;
	}
	
	/**
	 * The return value represents:
	 * <ul>
	 * <li>0 - the same location
	 * <li>A negative number - number of times to send the Left Input
	 * <li>A positive number - a number of times to send the Right Input
	 * </ul>
	 * @param binding the ControllerBinding to navigate to
	 * @return a number representing how many times to input Right in the Robot
	 */
	public int getNumberOfInputTo(ControllerBinding binding) {
		int diff = binding.ordinal() - this.ordinal();
		if(binding.ordinal() > SMASH.ordinal()) {
			diff--;
		}
		return diff;
	}
	
	public enum LimitedTo {
		NONE,
		C_STICK,
		NON_C_STICK;
	}
}
