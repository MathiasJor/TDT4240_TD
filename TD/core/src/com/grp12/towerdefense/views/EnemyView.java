package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;

import java.util.ArrayList;

public class EnemyView extends View {

    private ArrayList<AbstractEnemy> enemies;
    private Texture enemyImg;
    private Sprite enemySpr;

    public EnemyView() {
        enemies = new ArrayList<AbstractEnemy>();
        enemyImg = new Texture("towerDefense_tile245.png");
        enemySpr = new Sprite(enemyImg);

    }
    @Override
    public void draw(SpriteBatch spriteBatch) {
        for(AbstractEnemy e : enemies) {
            //Må kanskje endres for å ta høyde for hvordan koordinater skal oversettes
            spriteBatch.draw(enemyImg, e.getX()*getTileWidth(), e.getY()*getTileHeight());
        }
    }

    public void addEnemy(AbstractEnemy abstractEnemy) {
        if (enemies.indexOf(abstractEnemy) == -1)
            enemies.add(abstractEnemy);
    }

    public void removeEnemy(AbstractEnemy abstractEnemy) {
        enemies.remove(abstractEnemy);
    }

    @Override
    public void dispose() {

    }
}
