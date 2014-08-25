/**
 * 
 */
package com.github.mlaursen.projectm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.github.mlaursen.projectm.logging.Debug;
import com.github.mlaursen.projectm.profile.Profile;
import com.github.mlaursen.projectm.profile.controller.ControllerBinding;
import com.github.mlaursen.projectm.profile.controller.ControllerKeyName;
import com.github.mlaursen.projectm.profile.controller.ControllerSetup;
import com.github.mlaursen.projectm.stages.Stage;
import com.github.mlaursen.projectm.stages.StageName;
import com.github.mlaursen.projectm.utils.StringUtils;
import com.github.mlaursen.robot.RobotSettings;

/**
 * @author mlaursen
 *
 */
public class UserSettings implements RobotSettings {
  private static final String propertiesFile = "config/config.properties";
  private static final String DEBUG = "[SETTINGS]: %s = %s";
  public static final String ENABLED = "enabled";
  
  
  private static List<Profile> userProfiles = new ArrayList<>();
  private static List<Stage> stages = new ArrayList<>();
  
  private static boolean isDebug = false;
  private static boolean isOnHomeScreen = false;
  private static int latency;
  private static Map<String, Integer> keyCodes = new HashMap<>();
  private static Map<Action, Integer> keys = new HashMap<>();
  static {
    init();
  }
  
  private static void init() {
    try {
      Debug.debug("[INFO]: Initializing Settings...");
      Properties p = new Properties();
      InputStream is = new FileInputStream(propertiesFile);
      p.load(is);
      latency = Integer.parseInt(p.getProperty("latency"));
      isDebug = p.getProperty("debug").equals("on");
      isOnHomeScreen = p.getProperty("isOnHomeScreen").equals("true");
      Debug.debug(String.format(DEBUG, "latency", latency));
      Debug.debug(String.format(DEBUG, "debug", isDebug));
      Debug.debug(String.format(DEBUG, "isOnHomeScreen", isOnHomeScreen));
      
      initKeys(p);
      initProfiles(p);
      initStages(p);
    }
    catch(IOException e) {
      Debug.debug("Can not find " + propertiesFile + " to read configuration properties.");
      Debug.debug("Starting Project M has been aborted.");
      System.exit(0);
    }
  }
  
  private static void initProfiles(Properties p) {
    int numProfiles = getNumberOfProfiles(p);
    for(int i = 1; i <= numProfiles; i++) {
      String currentProfile = "profile" + i + ".";
      String name = p.getProperty(currentProfile + "name");
      String bumperL = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.BUMPER_LEFT));
      String bumperR = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.BUMPER_RIGHT));
      String z = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.Z));
      String y = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.Y));
      String x = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.X));
      String a = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.A));
      String b = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.B));
      String cStick = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.C_STICK));
      String dPadUp = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.D_PAD_UP));
      String dPadSides = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.D_PAD_SIDES));
      String dPadDown = p.getProperty(currentProfile + StringUtils.getEnumPropertyName(ControllerKeyName.D_PAD_DOWN));
      String tapJump = p.getProperty(currentProfile + "tapJump");
      ControllerSetup cs = new ControllerSetup();
      cs.setBumperLeft(ControllerBinding.fromString(bumperL));
      cs.setBumperRight(ControllerBinding.fromString(bumperR));
      cs.setZ(ControllerBinding.fromString(z));
      cs.setY(ControllerBinding.fromString(y));
      cs.setX(ControllerBinding.fromString(x));
      cs.setA(ControllerBinding.fromString(a));
      cs.setB(ControllerBinding.fromString(b));
      cs.setCStick(ControllerBinding.fromString(cStick));
      cs.setDPadUp(ControllerBinding.fromString(dPadUp));
      cs.setDPadSides(ControllerBinding.fromString(dPadSides));
      cs.setDPadDown(ControllerBinding.fromString(dPadDown));
      cs.setTapJump(ENABLED.equals(tapJump));
      Profile profile = new Profile(name, cs);
      userProfiles.add(profile);
      Debug.alywasDebug(profile);
    }
  }
  
  private static int getNumberOfProfiles(Properties p) {
    int count = 0;
    Enumeration<?> e = p.propertyNames();
    while(e.hasMoreElements()) {
      String s = e.nextElement().toString();
      if(s.contains("profile") && s.contains("name")) {
        count++;
      }
    }
    return count;
  }
  
  private static void initStages(Properties p) {
    for(StageName sn : StageName.values()) {
      Stage s = new Stage(sn, ENABLED.equals(p.getProperty(sn.name())));
      stages.add(s);
    }
  }
  
  private static void initKeyCodes() {
    for(int i = 65; i < 65 + 26; i++) {
      keyCodes.put(String.valueOf((char) i), i);
    }
  }
  
  private static void initKeys(Properties p) {
    initKeyCodes();
    for(Action a : Action.values()) {
      keys.put(a, keyCodes.get(p.get("gc." + a.toString().toLowerCase())));
    }
  }
  
  /**
   * @return the userProfiles
   */
  public static List<Profile> getUserProfiles() {
    return userProfiles;
  }
  
  /**
   * @return the isDebug
   */
  public boolean isDebug() {
    return isDebug;
  }
  
  /**
   * @return the latency
   */
  public int getLatency() {
    return latency;
  }
  
  public static boolean isDebugEnabled() {
    return isDebug;
  }
  
  public static int getLatencyAmount() {
    return latency;
  }
  
  public static int getKey(Action a) {
    return keys.get(a);
  }
  
  /**
   * @return the isOnHomeScreen
   */
  public static boolean isOnHomeScreen() {
    return isOnHomeScreen;
  }
  
  public static List<Stage> getEnabledStages() {
    List<Stage> enabled = new ArrayList<>();
    for(Stage s : stages) {
      StageName n = s.getStageName();
      if(s.isEnabled() && (!n.equals(StageName.TEMPLE) || n.equals(StageName.CUSTOM_STAGES))) {
        enabled.add(s);
        Debug.alywasDebug(s);
      }
    }
    return enabled;
  }
}
