package com.grp12.towerdefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.grp12.towerdefense.Network.ServerConnection;
import com.grp12.towerdefense.gamelogic.Map;
import com.grp12.towerdefense.gamelogic.Node;
import com.grp12.towerdefense.gamelogic.enemies.BasicEnemy;
import com.grp12.towerdefense.states.GameStateManager;
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
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new PlayState(gsm));

        camera = new OrthographicCamera();
        viewport = new FillViewport(gsm.getViewportWidth(), gsm.getViewportHeight(), camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        gsm.render(batch);
        batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
