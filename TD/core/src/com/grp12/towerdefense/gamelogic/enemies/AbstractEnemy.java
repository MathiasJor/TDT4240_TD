package com.grp12.towerdefense.gamelogic.enemies;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grp12.towerdefense.gamelogic.statuseffects.AbstractStatusEffect;
import com.grp12.towerdefense.gamelogic.Node;

import java.util.ArrayList;

public abstract class AbstractEnemy extends Actor{
    private int health;
    float x;
    float y;
    public int speed;

    ArrayList<AbstractStatusEffect> statusEffects;

    Node nextWaypoint;

    public void move(float dt){
        if(x == nextWaypoint.getX() && y == nextWaypoint.getY()){
            //TODO: Implement a check to see if we have reached the end of the path
            findNextWaypoint();
        }

        x = (nextWaypoint.getX() - x) * dt * speed;
        y = (nextWaypoint.getY() - y) * dt * speed;
    }

    public void setNextWaypoint(Node waypoint){
        nextWaypoint = waypoint;
    }

    public void findNextWaypoint(){

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
