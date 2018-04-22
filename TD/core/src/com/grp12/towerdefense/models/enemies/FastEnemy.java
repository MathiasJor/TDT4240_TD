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
    public AbstractEnemy clone() {
        return new FastEnemy(getWaypoints(), getSpeed(), getHealth());
    }
}
