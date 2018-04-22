package com.grp12.towerdefense.views.PlayViews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class StartRoundButton extends View {

    private Texture button;
    private int middleHeight;
    private int middleWidth;

    public StartRoundButton(int height, int width) {

        button = new Texture("startButton.png");
        this.middleHeight = 0;
        this.middleWidth = width/2 - button.getWidth()/2;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(button, middleWidth, middleHeight);
    }

    public boolean clicked(Vector3 pointer) {

        if (pointer.x >= middleWidth && pointer.x <= middleWidth + button.getWidth()) {
            if (pointer.y >= middleHeight && pointer.y <= middleHeight + button.getHeight()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void dispose() {

    }
}
