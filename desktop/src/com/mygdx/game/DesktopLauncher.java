package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	private  static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Atomic Bomber");
		config.setWindowedMode(WIDTH, HEIGHT);
		config.setResizable(false);
		new Lwjgl3Application(new AtomicBomber(), config);
	}
}
