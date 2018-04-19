package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartRoundButton extends View {

    private Texture button;
    private int middleHeight;
    private int middleWidth;

    public StartRoundButton(int height, int width) {

        button = new Texture("startButton.png");
        this.middleHeight = height/2 - button.getHeight()/2;
        this.middleWidth = width/2 - button.getWidth()/2;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(button, middleWidth, middleHeight);
    }

    @Override
    public void dispose() {

    }
}
