/**
 * 
 */
package com.github.mlaursen.projectm.profile.controller;


/**
 * @author mlaursen
 *
 */
public enum ControllerKeyName {
	BUMPER_LEFT(0,0),
	BUMPER_RIGHT(0,1),
	Z(1,1),
	Y(2,1),
	X(3,1),
	A(4,1),
	B(5,1),
	C_STICK(6,1),
	D_PAD_UP(4,0),
	D_PAD_DOWN(5,0),
	D_PAD_SIDES(6,0),
	NONE(-1,-1);
	
	private int x, y;
	private ControllerKeyName(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
