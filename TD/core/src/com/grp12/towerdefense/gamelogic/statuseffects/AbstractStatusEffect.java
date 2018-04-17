package com.grp12.towerdefense.gamelogic.statuseffects;

import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;

import java.util.ArrayList;

public abstract class AbstractStatusEffect {

    double [] reduceBasedOnTowerLevel  = {0.1,0.2,0.3,0.4};
    public void applyStatusEffect(int effectType, AbstractEnemy enemy, int towerLevel){
        switch(effectType){
            //slow
            case 1:
                slowDown(enemy,towerLevel);
                break;
            //poison
            case 2:
                poison(enemy,towerLevel);
                break;

        }
    }

    public void slowDown(AbstractEnemy enemy,int towerLevel){
        float speed = enemy.getSpeed();
        enemy.setSpeed((float) (speed*1-reduceBasedOnTowerLevel[towerLevel]));
    }

    //posion, returns the arrau [how much health reduced per seconds, duration in sections]
    public int[] poison(AbstractEnemy enemy,int towerLevel){
       return new int[]{(int) (enemy.getHealth() * reduceBasedOnTowerLevel[towerLevel]),10};
    }

}
