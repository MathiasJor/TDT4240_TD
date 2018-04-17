package com.grp12.towerdefense.gamelogic.towers;

import com.badlogic.gdx.math.Vector2;
import com.grp12.towerdefense.gamelogic.Node;
import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;

import java.util.ArrayList;

public abstract class AbstractTower {

    private int damage, cost, range;
    private float reloadTime;
    private AbstractEnemy target;
    private static ArrayList<AbstractEnemy> enemies;
    private float frameTime = 0;
    private boolean canShoot = true;
    private Node container;

    public AbstractTower(int damage, float reloadTime, int cost, int range){
        this.damage = damage;
        this.reloadTime = reloadTime;
        this.range = range;
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
        return container.getPosition().dst2(abstractEnemy.getPosition()) > range;
    }

    public void setNode (Node node) {
        container = node;
    }

    public Node getNode () {
        return container;
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
        return container.getPosition();
    }

    public AbstractEnemy findNextEnemy(ArrayList<AbstractEnemy> listOfEnemies){
        float shortest = Float.MAX_VALUE;
        float tempDistance;
        AbstractEnemy returnEnemy = null;
        for (AbstractEnemy enemy : listOfEnemies){
            tempDistance = container.getPosition().dst2(enemy.getPosition());
            if(tempDistance < shortest){
                shortest = tempDistance;
                returnEnemy = enemy;
            }
        }
        return (shortest < range) ? returnEnemy : null;
    }
}
