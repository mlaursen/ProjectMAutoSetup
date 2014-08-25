/**
 * 
 */
package com.github.mlaursen.projectm.logging;

import com.github.mlaursen.robot.Debugable;

/**
 * @author mlaursen
 *
 */
public class Debug extends com.github.mlaursen.robot.Debug {
  
  public static void alywasDebug(Debugable debugable) {
    debug(debugable.getLogMessage());
  }
}
