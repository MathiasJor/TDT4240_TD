package com.grp12.towerdefense.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.grp12.towerdefense.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = MainGame.TITLE;
		config.height = MainGame.HEIGHT;
		config.width = MainGame.WIDTH;
		new LwjglApplication(new MainGame(), config);
	}
}
