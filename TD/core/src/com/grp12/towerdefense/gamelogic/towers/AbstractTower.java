package com.grp12.towerdefense.gamelogic.towers;

import com.badlogic.gdx.math.Vector2;
import com.grp12.towerdefense.gamelogic.Node;
import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;

import java.util.ArrayList;

public abstract class AbstractTower {

    private int damage, cost, range, upgradeCost, towerLevel=0;
    private float reloadTime;
    private AbstractEnemy target;
    private static ArrayList<AbstractEnemy> enemies;
    private float frameTime = 0;
    private boolean canShoot = true;
    private String towerName;
    public enum towerType {Basic,Rocket, Stunner};
    private towerType typeTower;


    private float rotation=0;

    private Node container;


    public AbstractTower(towerType typeTower, int damage, float reloadTime, int cost, int range){
        this.damage = damage;
        this.reloadTime = reloadTime;
        this.cost = cost;
        this.range = range;
        this.upgradeCost= (int) (cost*.6); //upgrade cost is 60% of build price
        this.typeTower = typeTower;
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
            rotation = findDegree(target);
            target.takeDamage(damage);
            frameTime = 0;
            canShoot = false;
        }
    }

    private void targetUpdate() {
        if (target == null || target.getHealth() == 0 || enemyOutOfRange(target)) {
            target = findNextEnemy(enemies);
            if(target!=null){
                rotation = findDegree(target);
            }
        }
    }

    private boolean enemyOutOfRange(AbstractEnemy abstractEnemy) {
        return container.getPosition().dst2(abstractEnemy.getPosition()) > range;
    }

    //TODO: Setting this sets the node.setTower, if it fails, fail here as well
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

    public void setCost(int cost) {this.cost = cost;}

    public int getCost(){return cost;}

    public void setUpgradeCost(){this.upgradeCost= (int) (upgradeCost*1.2);} //upgrade cost increase by 20% for each upgrade

    public int getUpgradeCost(){return upgradeCost;}

    public int getTowerLevel(){return towerLevel;}

    public float getRotation(){return rotation;}

    public towerType getType(){return typeTower;}

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
            if (enemy.getHealth() > 0) {
                tempDistance = container.getPosition().dst2(enemy.getPosition());
            } else {
                tempDistance = shortest + 1;
            }
            if(tempDistance < shortest){
                shortest = tempDistance;
                returnEnemy = enemy;
            }
        }
        return (shortest < range) ? returnEnemy : null;
    }

    public float findDegree(AbstractEnemy target){
        Vector2 targetCoords = target.getPosition();
        float degree = (float) Math.atan((targetCoords.x-container.getPosition().x)/(targetCoords.y-container.getPosition().y));
        degree= (float) Math.toDegrees(degree);
        if(container.getPosition().x==targetCoords.x){
            if(targetCoords.y<container.getPosition().y){
                degree = 90;
            }
            else{
                degree=270;
            }
        }
        else if(container.getPosition().y==targetCoords.y){
            if(targetCoords.x> container.getPosition().x){
                degree = 0;
            }
            else{
                degree=180;
            }
        }
        else{
            if(targetCoords.x>container.getPosition().x){
                if(targetCoords.y<container.getPosition().y){
                    degree=90+degree;
                }
                else{
                    degree = +degree-90; //done
                }
            }
            else{
                if(targetCoords.y>container.getPosition().y){
                    degree=270+degree; //done
                }
                else{
                    degree=degree+90;
                }
            }
        }
        return degree;
    }
    

}
