package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class View {

    private static int tileWidth;
    private static int tileHeight;

    public static void setTileHeight(int tileHeight) {
        View.tileHeight = tileHeight;
    }

    public static void setTileWidth(int tileWidth) {
        View.tileWidth = tileWidth;
    }

    public static int getTileHeight() {
        return tileHeight;
    }

    public static int getTileWidth() {
        return tileWidth;
    }

    public abstract void draw(SpriteBatch spriteBatch);

    public abstract void dispose();

}
