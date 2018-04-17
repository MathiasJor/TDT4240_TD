package com.grp12.towerdefense.gamelogic.towers;

import com.badlogic.gdx.math.Vector2;
import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;

import java.util.ArrayList;

public abstract class AbstractTower {

    private int damage, cost, range, targetEnemy=1000, upgradeCost, towerLevel=0;
    private float reloadTime;
    private Vector2 position;
    private AbstractEnemy target;
    private static ArrayList<AbstractEnemy> enemies;
    private float frameTime = 0;
    private boolean canShoot = true;

    public AbstractTower(int damage, float reloadTime, int cost, int range, Vector2 position){
        this.damage = damage;
        this.reloadTime = reloadTime;
        this.range = range;
        this.position = position;
        this.upgradeCost= (int) (cost*.6); //upgrade cost is 60% of build price
        enemies = new ArrayList<AbstractEnemy>();
        target = null;
    }

    public static void setEnemyList(ArrayList<AbstractEnemy> enemies) {
        AbstractTower.enemies = enemies;
    }

    public void fire(float dt) {
        frameTime += dt;
        if (frameTime > reloadTime)
            canShoot = true;

        targetUpdate();
        if (target != null && canShoot) {
            target.takeDamage(damage);
            frameTime = 0;
            canShoot = false;
        }
    }

    private void targetUpdate() {
        if (target == null || target.getHealth() == 0 || enemyOutOfRange(target)) {
            target = findNextEnemy(enemies);
        }
    }

    private boolean enemyOutOfRange(AbstractEnemy abstractEnemy) {
        return position.dst2(abstractEnemy.getPosition()) > range;
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



    public AbstractEnemy findNextEnemy(ArrayList<AbstractEnemy> listOfEnemies){
        float shortest = Float.MAX_VALUE;
        float tempDistance;
        AbstractEnemy returnEnemy = null;
        for (AbstractEnemy enemy : listOfEnemies){
            tempDistance = position.dst2(enemy.getPosition());
            if(tempDistance < shortest){
                shortest = tempDistance;
                returnEnemy = enemy;
            }
        }
        return (shortest < range) ? returnEnemy : null;
    }
    

}
