package com.grp12.towerdefense.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected GameStateManager gsm;

    protected State (GameStateManager gsm) {
        this.gsm = gsm;
    }

    protected abstract void handleInput(Vector3 pointer);
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
    public abstract int getViewportWidth();
    public abstract int getViewportHeight();

}
