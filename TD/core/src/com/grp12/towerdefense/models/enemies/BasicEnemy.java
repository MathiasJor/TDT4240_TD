package com.grp12.towerdefense.models.enemies;

import com.grp12.towerdefense.models.Node;

import java.util.ArrayList;

public class BasicEnemy extends AbstractEnemy {
    //TODO Make bounty and cost parameters?
    public BasicEnemy(ArrayList<Node> waypoints, float speed, int health) {
        super(waypoints, speed, health, 50, 10);
        this.type = EnemyType.BASIC;
    }

    @Override
    public AbstractEnemy clone() {
        return new BasicEnemy(getWaypoints(), getSpeed(), getHealth());
    }
}
