package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.grp12.towerdefense.gamelogic.Node;

/*
MapView is unique in that it contains stuff that doesn't change during the game, and for performance's sake.
It returns a SpriteCache to not have to fill up a SpriteBatch with the same stuff each time it draws.
It does not extend the View interface like other views should.
 */

public class MapView {

    private Node[][] grid;
    private Texture path;
    private Texture land;
    private SpriteCache sc;
    private int cacheId;

    public MapView(Node[][] grid) {
        this.grid = grid;
        land = new Texture("towerDefense_tile024.png");
        path = new Texture("towerDefense_tile050.png");
        sc = new SpriteCache();
        generateCache();
    }

    public SpriteCache getSpriteCache() {
        return sc;
    }

    public int getCacheId() {
        return cacheId;
    }

    public void setGrid(Node[][] grid) {
        this.grid = grid;
        generateCache();
    }

    private void generateCache() {
        sc.beginCache();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getType() == Node.NodeType.TOWERNODE) {
                    sc.add(land, i*land.getHeight(), j*land.getWidth());
                }
                if (grid[i][j].getType() == Node.NodeType.PATHNODE) {
                    sc.add(path, i*land.getHeight(), j*land.getWidth());
                }
            }
        }
        cacheId = sc.endCache();
    }

    public int getMapWidth() {
        return land.getWidth()*grid.length;
    }

    public int getMapHeight() {
        return land.getHeight()*grid[0].length;
    }


}
