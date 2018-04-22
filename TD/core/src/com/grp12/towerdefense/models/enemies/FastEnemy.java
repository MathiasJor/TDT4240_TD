package com.grp12.towerdefense.models.enemies;

import com.grp12.towerdefense.models.Node;

import java.util.ArrayList;

public class FastEnemy extends AbstractEnemy {

    private final float speedMultiplier = 1.3f;

    //TODO Make bounty and cost parameters?
    public FastEnemy(ArrayList<Node> waypoints, float speed, int health) {
        super(waypoints, speed, health, 50, 10);
        this.speed = speed * speedMultiplier;
        this.type = EnemyType.FAST;
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

    @Override
    public AbstractEnemy clone() {
        return new FastEnemy(getWaypoints(), getSpeed(), getHealth());
    }
}
