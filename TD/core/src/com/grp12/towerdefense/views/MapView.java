package com.grp12.towerdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.grp12.towerdefense.gamelogic.Node;
import com.grp12.towerdefense.gamelogic.towers.AbstractTower;

import javax.swing.text.Position;

/*
MapView is unique in that it contains stuff that doesn't change during the game, and for performance's sake.
It returns a SpriteCache to not have to fill up a SpriteBatch with the same stuff each time it draws.
It does not extend the View interface like other views should.
 */
// Changed to View subclass for now, if stuff starts getting laggy, consider making it use spritecache again, and make it so that the main render loop fetches it.

public class MapView extends View {

    private Node[][] grid;
    private Texture path;
    private Texture land;
    private Texture tower;
    private Sprite tSprite;

    public MapView(Node[][] grid) {
        this.grid = grid;
        land = new Texture("towerDefense_tile024.png");
        path = new Texture("towerDefense_tile050.png");
        tower= new Texture("towerDefense_tile250.png");
        tSprite = new Sprite(tower);
      
        View.setTileHeight(path.getHeight());
        View.setTileWidth(path.getWidth());
        View.setMapHeight(land.getHeight()*grid[0].length);
        View.setMapWidth(land.getWidth()*grid.length);
    }

    public void setGrid(Node[][] grid) {
        this.grid = grid;
    }

    @Override
    public void draw(SpriteBatch sb) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getType() == Node.NodeType.TOWERNODE) {
                    sb.draw(land, i*land.getHeight(), j*land.getWidth());
                    AbstractTower t = grid[i][j].getTower();
                    if (t != null) {
                        tSprite.setRotation(t.getRotation());
                        tSprite.setPosition(i * land.getHeight(), j * land.getWidth());
                        tSprite.draw(sb);

                    }
                }
                if (grid[i][j].getType() == Node.NodeType.PATHNODE) {
                    sb.draw(path, i*land.getHeight(), j*land.getWidth());
                }
            }
        }
    }

    //returns the node from where finger is clicked
    public Node getNode(Vector3 pointer){
        //convert from screen coordinates to map coordinates
        int i = (int) (Math.ceil(pointer.x/getTileWidth()))-1;
        int j = (int) (Math.ceil(pointer.y/getTileHeight()))-1;
        return grid[i][j];
    }


    public void dispose() {
        //TODO: #4: Implement this
    }


}
