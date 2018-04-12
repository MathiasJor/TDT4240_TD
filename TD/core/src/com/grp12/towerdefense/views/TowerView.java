package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.gamelogic.towers.AbstractTower;

import java.util.ArrayList;

public class TowerView implements View {

    private ArrayList<AbstractTower> towers;
    private Texture towerImg;
    private Sprite towerSpr;

    public TowerView() {
        towers = new ArrayList<AbstractTower>();
    }

    public void draw(SpriteBatch sb) {
        for(AbstractTower t : towers) {
            //TODO: tegn tårn der de er på kartet
        }

        }

}
