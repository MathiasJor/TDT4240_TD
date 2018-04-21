package com.grp12.towerdefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.grp12.towerdefense.Network.ServerConnection;
import com.grp12.towerdefense.gamelogic.Map;
import com.grp12.towerdefense.gamelogic.Node;
import com.grp12.towerdefense.gamelogic.enemies.BasicEnemy;
import com.grp12.towerdefense.states.GameStateManager;
import com.grp12.towerdefense.states.MenuState;
import com.grp12.towerdefense.states.PlayState;
import com.grp12.towerdefense.views.EnemyView;
import com.grp12.towerdefense.views.MapView;
import com.grp12.towerdefense.views.View;

import java.util.ArrayList;

public class MainGame extends ApplicationAdapter {

	public static final int HEIGHT = 480;
	public static final int WIDTH = 720;
	public static final String TITLE = "Tower Defense";

	SpriteBatch batch;
	private GameStateManager gsm;

    private OrthographicCamera camera;
    private Viewport viewport;

	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		viewport = new FillViewport(100, 100, camera);
		viewport.apply();
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		Gdx.gl.glClearColor(0, 0, 0, 1);

		batch = new SpriteBatch();
		gsm = new GameStateManager(this);
		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render () {
		camera.update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		if(Gdx.input.isTouched()) {
			handleInput();
		}

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        gsm.render(batch);
        batch.end();
	}

	public void handleInput() {
		Vector3 v3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		Vector3 pointer = camera.unproject(v3);
		gsm.handleInput(pointer);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void resizeViewport(int width, int height) {
		viewport = new FillViewport(width, height, camera);
		viewport.apply();
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}
}
