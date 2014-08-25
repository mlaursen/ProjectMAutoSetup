/**
 * 
 */
package com.github.mlaursen.projectm.utils;

/**
 * @author mlaursen
 *
 */
public class StringUtils {
  
  public static String getEnumPropertyName(Enum<?> e) {
    String lower = e.name().toLowerCase();
    String[] split = lower.split("_");
    String ret = split[0];
    if(split.length > 1) {
      for(int i = 1; i < split.length; i++) {
        String s = split[i];
        s = s.substring(0, 1).toUpperCase() + s.substring(1);
        ret += s;
      }
    }
    return ret;
  }
}
