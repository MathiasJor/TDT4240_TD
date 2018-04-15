package com.grp12.towerdefense.gamelogic.towers;

import com.badlogic.gdx.math.Vector2;
import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractTower {

    private int damage, cost, range;
    private float reloadTime;
    private Vector2 position;
    private AbstractEnemy target;

    public AbstractTower(int damage, float reloadTime, int cost, int range, Vector2 position){
        this.damage = damage;
        this.reloadTime = reloadTime;
        this.range = range;
        this.position = position;
    }

    public void fire() {
        if (target != null) {
            target.takeDamage(damage);
        }
    }

    public void setTarget(AbstractEnemy abstractEnemy) {
        target = abstractEnemy;
    }

    public AbstractEnemy getTarget() {
        return target;
    }

    public void setDamage (int Dmg){
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }

    public void setRange (int range){
        this.range = range;
    }

    public int getRange(){
        return range;
    }

    public void setReloadTime (float reloadTime){
        this.reloadTime = reloadTime;
    }

    public float getreloatime(){
        return reloadTime;
    }

    public Vector2 getPosition(){
        return position;
    }


    public int findNextEnemy(ArrayList<AbstractEnemy> listOfEnemies){
        ArrayList<Float> distance = new ArrayList<Float>();
        float tempDistance=0;
        for (int i=0; i<(listOfEnemies.size());i++){
            tempDistance=position.dst2(listOfEnemies.get(i).getPosition());
            if(tempDistance>range){
                tempDistance=1000;
            }
            distance.add(tempDistance);
        }
        return distance.indexOf(Collections.min(distance));
    }
}
