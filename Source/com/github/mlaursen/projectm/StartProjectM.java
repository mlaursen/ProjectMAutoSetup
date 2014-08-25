/**
 * 
 */
package com.github.mlaursen.projectm;

import java.awt.AWTException;

import com.github.mlaursen.projectm.logging.Debug;
import com.github.mlaursen.projectm.profile.Profile;
import com.github.mlaursen.projectm.stages.Stage;
import com.github.mlaursen.robot.Robot;

/**
 * @author mlaursen
 *
 */
public class StartProjectM {
	
	private static Robot r;
	/**
	 * @param args
	 * @throws AWTException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws AWTException, InterruptedException {
		for (int i = 5; i > 0; i--) {
			System.out.println(i + "... Click on the Project M window!");
			Thread.sleep(1000);
		}
		long startTime = System.currentTimeMillis();

		UserSettings settings = new UserSettings();
		r = new Robot(settings);
		navigateToStartScreen();
		initProfiles();
		navigateToMainMenu();
		initRules();
		initFight();
		r.automate();
		long endTime = System.currentTimeMillis();
		Debug.debug(String.format("[INFO]: Finished the setup in %d seconds!", (endTime - startTime) / 1000));
		Debug.debug("[INFO]: This window will now close in 8 seconds.");
		Thread.sleep(8000);
	}
	
	private static void navigateToStartScreen() {
		r.addAction(Action.A.getRobotAction(2000));
		r.addAction(Action.A.getRobotAction(1800));
		r.addAction(Action.B.getRobotAction(200));
		r.addAction(Action.A.getRobotAction(1050));
		r.addAction(Action.B.getRobotAction(450));
		r.addAction(Action.A.getRobotAction(1425));
	}
	
	private static void initProfiles() {
		ScreenPosition options = new ScreenPosition(1, 2);
		r.addActions(options.getControlsFrom());
		
		ScreenPosition controls = new ScreenPosition(0, 4);
		r.addActions(controls.getControlsFrom(400));
		
		for(Profile p : UserSettings.getUserProfiles()) {
			r.addAction(Action.A.getRobotAction(400));
			r.addActions(p.getProfileActions());
		}
	}
	
	private static void navigateToMainMenu() {
		r.addAction(Action.B.getRobotAction());
		r.addAction(Action.B.getRobotAction());
		r.addAction(Action.UP.getRobotAction());
		r.addAction(Action.LEFT.getRobotAction());
	}
	
	private static void initRules() {
		turnOffItems();
		initStages();
	}
	
	private static void turnOffItems() {
		r.addAction(Action.A.getRobotAction());
		r.addAction(Action.RIGHT.getRobotAction());
		r.addAction(Action.A.getRobotAction(200));
		
		// Now on the rules screen
		r.addAction(Action.UP.getRobotAction());
		r.addAction(Action.A.getRobotAction(200));
		
		// Disabling Items..
		r.addRepeatingAction(Action.LEFT.getRobotAction(), 2);
		r.addAction(Action.B.getRobotAction(200));
	}
	
	private static void initStages() {
		ScreenPosition moreRules = new ScreenPosition(0, 1);
		r.addActions(moreRules.getControlsFrom(200));
		
		r.addAction(Action.UP.getRobotAction());
		r.addAction(Action.A.getRobotAction(200));
		
		// Now on Random Stage switch screen
		r.addAction(Action.A.getRobotAction());
		r.addAction(Action.RIGHT.getRobotAction());
		r.addAction(Action.A.getRobotAction());
		r.addAction(Action.LEFT.getRobotAction());
		r.addAction(Action.DOWN.getRobotAction());
		
		ScreenPosition current = new ScreenPosition(0, 0);
		for(Stage s : UserSettings.getEnabledStages()) {
			ScreenPosition to = s.getPosition();
			r.addActions(current.getControlsTo(to));
			current = to;
		}
	}
	
	private static void initFight() {
		r.addRepeatingAction(Action.B.getRobotAction(), 3);
		r.addAction(Action.LEFT.getRobotAction());
		r.addAction(Action.A.getRobotAction());
	}
}
