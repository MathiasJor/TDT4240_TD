package com.grp12.towerdefense.gamelogic.enemies;

import com.grp12.towerdefense.gamelogic.Node;

import java.util.ArrayList;

public class BasicEnemy extends AbstractEnemy {
    public BasicEnemy(ArrayList<Node> waypoints, float speed, int health) {
        super(waypoints, speed, health);
    }

    @Override
    public void move(float dt) {
        super.move(dt);
    }

    @Override
    public void setNextWaypoint(Node waypoint) {
        super.setNextWaypoint(waypoint);
    }

    @Override
    public void findNextWaypoint() {
        super.findNextWaypoint();
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
    }

    @Override
    public float getX() {
        return super.getX();
    }

    @Override
    public float getY() {
        return super.getY();
    }
}