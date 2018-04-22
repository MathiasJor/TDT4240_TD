package com.grp12.towerdefense.views.PlayViews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.states.GameStateManager;

public class GameOverView extends View{

    private Texture gameover;
    private Texture victory;
    private int xPosLoss, yPosLoss, xPosWin, yPosWin;
    private boolean win;

    public GameOverView(boolean win) {
        this.win = win;
        gameover = new Texture("gameover.png");
        victory = new Texture("victory.png");
        xPosLoss = View.getMapWidth()/2 - gameover.getWidth()/2;
        yPosLoss = View.getMapHeight()/2 - gameover.getHeight()/2;
        xPosWin = View.getMapWidth()/2 - victory.getWidth()/2;
        yPosWin = View.getMapHeight()/2 - victory.getHeight()/2;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (win) {
            spriteBatch.draw(victory, xPosWin, yPosWin);
        } else {
            spriteBatch.draw(gameover, xPosLoss, yPosLoss);
        }
    }

    @Override
    public void dispose() {

    }
}
