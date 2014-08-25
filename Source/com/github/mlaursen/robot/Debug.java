/**
 * 
 */
package com.github.mlaursen.robot;

/**
 * @author mlaursen
 *
 */
public class Debug {
	
	public static void debug(Debugable debugable, RobotSettings settings) {
		if(settings.isDebug()) {
			debug(debugable.getLogMessage());
		}
	}
	
	public static void debug(String message) {
		System.out.println(message);
	}
}
