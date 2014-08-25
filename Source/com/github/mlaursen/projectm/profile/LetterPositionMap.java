/**
 * 
 */
package com.github.mlaursen.projectm.profile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mlaursen
 *
 */
public class LetterPositionMap {
  
  private static Map<Character, LetterPosition> map = new HashMap<>();
  static {
    initLetters();
    initPunctuation();
  }
  
  private static void initLetters() {
    int totalCount = 65;
    int count = 0;
    for(int row = 0; row < 4; row++) {
      int boxInit = row == 0 ? 1 : 0;
      for(int box = boxInit; box < 3; box++) {
        int max = 3;
        if(row == 2 && (box == 0 || box == 2)) {
          max = 4;
        }
        for(int aPresses = 1; aPresses <= max; aPresses++) {
          map.put((char) (totalCount + count), new LetterPosition(row, box, aPresses));
          count++;
        }
      }
    }
  }
  
  private static void initPunctuation() {
    int aPresses = 1;
    int row = 0;
    map.put('@', new LetterPosition(0, row, aPresses++));
    map.put('(', new LetterPosition(0, row, aPresses++));
    map.put(')', new LetterPosition(0, row, aPresses++));
    map.put('^', new LetterPosition(0, row, aPresses++));
    map.put(':', new LetterPosition(0, row, aPresses++));
    map.put(';', new LetterPosition(0, row, aPresses++));
    
    aPresses = 1;
    row = 3;
    map.put('!', new LetterPosition(0, row, aPresses++));
    map.put('?', new LetterPosition(0, row, aPresses++));
    map.put('&', new LetterPosition(0, row, aPresses++));
    map.put('%', new LetterPosition(0, row, aPresses++));
    
    aPresses = 1;
    map.put('·', new LetterPosition(1, row, aPresses++));
    map.put(',', new LetterPosition(1, row, aPresses++));
    map.put('.', new LetterPosition(1, row, aPresses++));
    map.put('/', new LetterPosition(1, row, aPresses++));
    map.put('~', new LetterPosition(1, row, aPresses++));
  }
  
  public static LetterPosition getLetterPosition(Character c) {
    return map.get(c);
  }
}
