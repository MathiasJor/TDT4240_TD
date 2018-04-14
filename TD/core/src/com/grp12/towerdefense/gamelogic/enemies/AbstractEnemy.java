package com.grp12.towerdefense.gamelogic.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grp12.towerdefense.gamelogic.statuseffects.AbstractStatusEffect;
import com.grp12.towerdefense.gamelogic.Node;

import java.util.ArrayList;

public abstract class AbstractEnemy extends Actor{

    private int health;
    private float speed;
    private Vector2 position = new Vector2();

    private Node currentWaypoint = null;
    private int waypointIndex;
    private ArrayList<Node> waypoints;

    ArrayList<AbstractStatusEffect> statusEffects;

    public AbstractEnemy(ArrayList<Node> waypoints, float speed, int health) {
        this.waypoints = waypoints;
        this.speed = speed;
        this.health = health;
        waypointIndex = 0;
        findNextWaypoint();
    }

    public void move(float dt){
        if (position.dst(currentWaypoint.getPosition()) <= 0.4f){
            //TODO: Implement a check to see if we have reached the end of the path
            findNextWaypoint();
        }
        position.x = (currentWaypoint.getX() - position.x) * dt * speed;
        position.y = (currentWaypoint.getY() - position.y) * dt * speed;
    }

    public void setNextWaypoint(Node waypoint){
        currentWaypoint = waypoint;
    }

    public void findNextWaypoint(){
        if (waypointIndex <= waypoints.size()) {
            currentWaypoint = waypoints.get(waypointIndex);
            waypointIndex++;
        } else {
            System.out.println("End reached by: " + this.toString());
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
}
