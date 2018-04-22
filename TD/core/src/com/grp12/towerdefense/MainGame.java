package com.grp12.towerdefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.grp12.towerdefense.network2.NetworkCommunicator;
import com.grp12.towerdefense.states.GameStateManager;
import com.grp12.towerdefense.views.MenyViews.ActiveGameMenuView;

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
		viewport = new FillViewport(2560, 2048, camera);
		viewport.apply();
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gsm = new GameStateManager(this);

		// CONNECTING to server and STARTING ping loop
		NetworkCommunicator.fetchExternalUserId();

		gsm.push(new ActiveGameMenuView(gsm));
	}

	@Override
	public void render () {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        gsm.render(batch);
        batch.end();

		if(Gdx.input.justTouched()) {
			handleInput();
		}
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
}
