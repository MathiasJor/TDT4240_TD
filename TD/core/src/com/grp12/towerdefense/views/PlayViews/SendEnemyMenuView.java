package com.grp12.towerdefense.views.PlayViews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class SendEnemyMenuView extends View {

    private Texture knapp;
    private Texture pluss;
    private int posX, posY;
    private boolean open;

    public SendEnemyMenuView() {
        knapp = new Texture("towerDefense_tile000.png");
        pluss = new Texture("towerDefense_tile001.png");
        posX = View.getMapWidth() - knapp.getWidth()*3;
        posY = 0;
        open = false;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(knapp, posX, posY, 200, 200);
        if (open) {
            spriteBatch.draw(pluss, posX, posY + knapp.getHeight()*3, 200, 200);
        }

    }

    public boolean clicked(Vector3 pointer) {
        if (pointer.x >= posX && pointer.x <= View.getMapWidth()) {
            if (pointer.y >= 0 && pointer.y <= knapp.getHeight()*3) {
                if (open) {
                    open = false;
                } else {
                    open = true;
                }
            }
            if (pointer.y >= knapp.getHeight()*3 && pointer.y <= knapp.getHeight()*6) {
                if (open) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    @Override
    public void dispose() {

    }
}
