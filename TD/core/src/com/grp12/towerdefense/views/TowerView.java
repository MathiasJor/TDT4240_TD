package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.gamelogic.towers.AbstractTower;

import java.util.ArrayList;

public class TowerView extends View {

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
            //TODO: tegn tårn der de er på kartet, mangler coords på tårn
            //spriteBatch.draw(towerSpr, t.getX()*getTileWidth(), t.getY()*getTileHeight());
        }
    }

}
