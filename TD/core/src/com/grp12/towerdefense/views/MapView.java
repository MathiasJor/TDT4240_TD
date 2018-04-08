package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.gamelogic.Node;

public class MapView {

    private Node[][] grid;
    private Texture path;
    private Texture land;

    public MapView(Node[][] grid) {
        this.grid = grid;
        path = new Texture("towerDefense_tile024.png");
        land = new Texture("towerDefense_tile050.png");
    }

    public void draw(SpriteBatch sb) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //Finn ut hvilken type node det er
                if (grid[i][j].getType() == Node.NodeType.TOWERNODE) {
                    sb.draw(land, i*land.getHeight(), j*land.getWidth());
                }
                if (grid[i][j].getType() == Node.NodeType.PATHNODE) {
                    sb.draw(path, i*land.getHeight(), j*land.getWidth());
                }
                //Tegn riktig Texture i coords i*img.height j*img.width
                //sb.draw(path, x, y);
            }
        }

    }


}
