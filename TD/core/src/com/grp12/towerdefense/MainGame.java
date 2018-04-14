package com.grp12.towerdefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.grp12.towerdefense.gamelogic.Map;
import com.grp12.towerdefense.states.GameStateManager;
import com.grp12.towerdefense.states.PlayState;
import com.grp12.towerdefense.views.MapView;

public class MainGame extends ApplicationAdapter {

	public static final int HEIGHT = 480;
	public static final int WIDTH = 720;
	public static final String TITLE = "Tower Defense";

	Map map;
	MapView mapView;

	SpriteBatch batch;
	private GameStateManager gsm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new PlayState(gsm));

		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		//gsm.render(batch);
		batch.begin();
		//mapView.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
