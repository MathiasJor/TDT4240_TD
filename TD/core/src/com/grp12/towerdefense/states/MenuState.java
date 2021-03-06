package com.grp12.towerdefense.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.grp12.towerdefense.views.MenyViews.StartMenuView;

public class MenuState extends State {

    private StartMenuView startMenuView;
    private int width = 2500;
    private int height = 2000;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        startMenuView = new StartMenuView(width, height);
    }

    @Override
    protected void handleInput(Vector3 pointer) {
        if (startMenuView.clicked(pointer) != null) {
            if (startMenuView.clicked(pointer) == StartMenuView.StartMenuOption.NEWGAME) {
                gsm.push(new PlayState(gsm));
            }
            if (startMenuView.clicked(pointer) == StartMenuView.StartMenuOption.ACTIVE_GAMES) {
                gsm.push(new PlayState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        startMenuView.draw(sb);
    }

    @Override
    public void dispose() {
        startMenuView.dispose();
    }

    @Override
    public int getViewportWidth() {
        return width;
    }

    @Override
    public int getViewportHeight() {
        return height;
    }
}
