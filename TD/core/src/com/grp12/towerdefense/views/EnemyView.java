package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;

import java.util.ArrayList;

public class EnemyView implements View {

    private ArrayList<AbstractEnemy> enemies;
    private Texture enemyImg;
    private Sprite enemySpr;

    public EnemyView() {
        enemies = new ArrayList<AbstractEnemy>();
    }

    public void draw(SpriteBatch sb) {
        for(AbstractEnemy e : enemies) {
            //TODO: tegn fiender der de er på kartet
        }
    }
}
