package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.gamelogic.towers.AbstractTower;

import java.util.ArrayList;

public class TowerView extends View {

    //TODO: #12: Add more textures in TowerView and a test for which texture to draw
    private ArrayList<AbstractTower> towers;
    private Texture towerImg;
    private Sprite towerSpr;

    public TowerView() {
        towers = new ArrayList<AbstractTower>();
        towerImg = new Texture("towerDefense_tile250.png");
        towerSpr = new Sprite(towerImg);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        for(AbstractTower t : towers) {
            spriteBatch.draw(towerSpr, t.getPosition().y*getTileWidth(), t.getPosition().x*getTileHeight());

        }
    }

    public void addTower(AbstractTower abstractTower) {
        if (towers.indexOf(abstractTower) == -1)
            towers.add(abstractTower);
    }

    public void removeTower(AbstractTower abstractTower) {
        towers.remove(abstractTower);
    }

    @Override
    public void dispose() {
        //TODO: #4: Implement this
    }

}
