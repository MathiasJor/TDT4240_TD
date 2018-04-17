package com.grp12.towerdefense.gamelogic.towers;

import com.badlogic.gdx.math.Vector2;
import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractTower {

    private int damage, cost, range, targetEnemy=1000, upgradeCost, towerLevel=0;
    private float reloadTime;
    private Vector2 position;

    public AbstractTower(int damage, float reloadTime, int cost, int range, Vector2 position){
        this.damage = damage;
        this.reloadTime = reloadTime;
        this.range = range;
        this.position = position;
        this.upgradeCost= (int) (cost*.6); //upgrade cost is 60% of build price
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

    public void setCost(int cost) {this.cost = cost;}

    public int getCost(){return cost;}

    public void setUpgradeCost(){this.upgradeCost= (int) (upgradeCost*1.2);} //upgrade cost increase by 20% for each upgrade

    public int getUpgradeCost(){return upgradeCost;}

    public int getTowerLevel(){return towerLevel;}

    //set new values for upgrade tower
    public void upgradeTower(int dmg, float reloadTime, int range){
        setDamage(dmg);
        setRange(range);
        setReloadTime(reloadTime);
        setUpgradeCost();
        towerLevel++;
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

   //check if target is still in range
    public int targetEnemy(ArrayList<AbstractEnemy> listOfEnemies){
        if(targetEnemy==1000){
            targetEnemy=findNextEnemy(listOfEnemies);
        }
        else if(position.dst(listOfEnemies.get(targetEnemy).getPosition())>range){
            targetEnemy=findNextEnemy(listOfEnemies);
        }
        return targetEnemy;
    }



}
