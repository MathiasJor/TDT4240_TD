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
        int counter = 0;
        for(AbstractEnemy e : enemies) {
            spriteBatch.draw(enemySpr, e.getY()*getTileWidth(), e.getX()*getTileHeight());
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
        //TODO: Implement this
    }
}
