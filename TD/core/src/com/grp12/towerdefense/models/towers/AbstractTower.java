package com.grp12.towerdefense.models.towers;

import com.badlogic.gdx.math.Vector2;
import com.grp12.towerdefense.models.Node;
import com.grp12.towerdefense.models.enemies.AbstractEnemy;

import java.util.ArrayList;

public abstract class AbstractTower {

    private int damage, cost, range, upgradeCost, towerLevel=0, value=0, sellPrice=0;
    private float reloadTime;
    private AbstractEnemy target;
    private float frameTime = 0;
    private boolean canShoot = true;
    private String towerName;
    public enum towerType {Basic,Rocket, Stunner};
    private towerType typeTower;
    private char choice='X';
    private float rotation=0;
    private Node container;


    public AbstractTower(towerType typeTower, int damage, float reloadTime, int cost, int range){
        this.damage = damage;
        this.reloadTime = reloadTime;
        this.cost = cost;
        this.range = range;
        this.upgradeCost= (int) (cost*.4); //upgrade cost starts off as 40% of buy cost
        this.typeTower = typeTower;
        setValue(cost);
        setSellPrice();
        target = null;
    }

    //TODO: Setting this sets the node.setTower, if it fails, fail here as well
    public void setNode (Node node) {
        container = node;
    }
    public Node getNode () {
        return container;
    }

    public void setTarget(AbstractEnemy enemy) {
        target = enemy;
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
    public float getReloadTime(){
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
    public void upgradeTower() {
        towerLevel++;
    }

    public float getRotation(){return rotation;}
    public void setRotation(float rotation) {this.rotation = rotation;}

    public towerType getType(){return typeTower;}
    public void setValue(int increase){value+=increase;}

    public void setSellPrice(){sellPrice = (int) (value*.8);}
    public int getSellPrice(){return  sellPrice;}

    public void setChoice(char choice){this.choice=choice;}
    public char getChoice(){return choice;}

    public void addFrameTime(float ft) {
        frameTime += ft;
    }
    public void resetFrameTime() { frameTime = 0;}
    public float getFrameTime() {
        return frameTime;
    }

    public void setCanShoot(boolean b) {canShoot = b;}
    public boolean getCanShoot() {return canShoot;}

    //set new values for upgrade tower



    

}
