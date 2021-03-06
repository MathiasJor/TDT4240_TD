package com.grp12.towerdefense.views.MenyViews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class StartMenuView {

    public enum StartMenuOption {NEWGAME, ACTIVE_GAMES}
    private int widthPos;
    private int heightPos1, heightPos2;
    private Texture start;
    private Texture active;

    public StartMenuView(int width, int height) {
        start = new Texture("startgame.png");
        active = new Texture("activegames.png");
        this.widthPos = width/2 - start.getWidth()/2;
        this.heightPos1 = height*2/3;
        this.heightPos2 = height*1/3;
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(start, widthPos,heightPos1);
        spriteBatch.draw(active, widthPos,heightPos2);
    }

    public StartMenuOption clicked(Vector3 pointer) {
        if (pointer.x >= widthPos && pointer.x <= widthPos + start.getWidth()) {
            if (pointer.y >= heightPos1 && pointer.y <= heightPos1 + start.getHeight()) {
                return StartMenuOption.NEWGAME;
            }
            if (pointer.y >= heightPos2 && pointer.y <= heightPos2 + start.getHeight()) {
                return  StartMenuOption.ACTIVE_GAMES;
            }
        }
        return null;
    }

    public void dispose() {
        start.dispose();
        active.dispose();
    }


}
