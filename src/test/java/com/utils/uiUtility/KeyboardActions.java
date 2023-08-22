package com.utils.uiUtility;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KeyboardActions {

	private static void typeString(String text) {
		try {
			Robot robot = new Robot();
			char[] chars = text.toCharArray();
			for (char c : chars) {
				typeCharacter(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void typeCharacter(char c) {
		try {
			Robot robot = new Robot();
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
			if (KeyEvent.CHAR_UNDEFINED == keyCode) {
				System.out.println("Cannot type character: " + c);
				return;
			}
			robot.keyPress(keyCode);
			robot.keyRelease(keyCode);
			robot.delay(50);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void pressAndReleaseKey(int keyCode) {
		try {
			Robot robot = new Robot();
			robot.keyPress(keyCode);
			robot.keyRelease(keyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void holdAndReleaseKey(int keyCode) {
		try {
			Robot robot = new Robot();
			robot.keyPress(keyCode);
			robot.delay(1000);
			robot.keyRelease(keyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void simulateKeyCombination(int... keyCodes) {
		try {
			Robot robot = new Robot();
			for (int keyCode : keyCodes) {
				robot.keyPress(keyCode);
			}
			for (int keyCode : keyCodes) {
				robot.keyRelease(keyCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void pressAndReleaseKeysInSequence(int... keyCodes) {
		try {
			Robot robot = new Robot();
			for (int keyCode : keyCodes) {
				robot.keyPress(keyCode);
				robot.delay(100);
			}
			for (int keyCode : keyCodes) {
				robot.keyRelease(keyCode);
				robot.delay(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
