package com.grp12.towerdefense.views.PlayViews;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class View {

    private static int tileWidth;
    private static int tileHeight;
    private static int mapWidth;
    private static int mapHeight;

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

    public static int getMapWidth() {
        return mapWidth;
    }

    public static void setMapWidth(int mapWidth) {
        View.mapWidth = mapWidth;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    public static void setMapHeight(int mapHeight) {
        View.mapHeight = mapHeight;
    }

    public abstract void draw(SpriteBatch spriteBatch);

    public abstract void dispose();

}
