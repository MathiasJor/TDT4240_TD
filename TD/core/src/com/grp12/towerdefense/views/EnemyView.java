package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;
import com.grp12.towerdefense.gamelogic.enemies.BasicEnemy;

import java.util.ArrayList;

public class EnemyView extends View {

    //TODO: #3: Add more textures and test for enemies type to draw different enemies
    private ArrayList<AbstractEnemy> enemies;

    //Basic enemy
    private Texture basicEnemyImg;
    private Sprite basicEnemySpr;

    //Fast enemy
    private Texture fastEnemyImg;
    private Sprite fastEnemySpr;

    public EnemyView() {
        enemies = new ArrayList<AbstractEnemy>();
        basicEnemyImg = new Texture("towerDefense_tile245.png");
        basicEnemySpr = new Sprite(basicEnemyImg);

        fastEnemyImg = new Texture("towerDefense_tile271.png");
        fastEnemySpr = new Sprite(fastEnemyImg);

    }
    @Override
    public void draw(SpriteBatch spriteBatch) {
        int counter = 0;
        for(AbstractEnemy e : enemies) {
            switch (e.getType()) {
                case BASIC:
                    basicEnemySpr.setRotation(e.getEnemyRotation());
                    basicEnemySpr.setPosition(e.getY()*getTileWidth(), e.getX()*getTileHeight());
                    basicEnemySpr.draw(spriteBatch);
                    break;
                case FAST:
                    fastEnemySpr.setRotation(e.getEnemyRotation());
                    fastEnemySpr.setPosition(e.getY()*getTileWidth(), e.getX()*getTileHeight());
                    fastEnemySpr.draw(spriteBatch);
                    break;
            }

            
            //spriteBatch.draw(enemySpr, e.getY()*getTileWidth(), e.getX()*getTileHeight());

            //System.out.print(e.getEnemyRotation()+"\n");

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
        //TODO: #4: Implement this
    }
}
