package com.grp12.towerdefense.views.PlayViews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.states.GameStateManager;

public class GameOverView extends View{

    private Texture gameover;
    private int xPos;
    private int yPos;

    public GameOverView() {
        gameover = new Texture("gameover.png");
        xPos = View.getMapWidth()/2 - gameover.getWidth()/2;
        yPos = View.getMapHeight()/2 - gameover.getHeight()/2;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(gameover, xPos, yPos);
    }

    @Override
    public void dispose() {

    }
}
