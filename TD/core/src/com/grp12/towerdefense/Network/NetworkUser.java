package com.grp12.towerdefense.Network;

/**
 * Created by Kristian on 4/21/2018.
 */

public class NetworkUser {
    int health;
    int gold;

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public boolean isTurn() {
        return isTurn;
    }

    boolean isTurn;

    public int getId() {
        return id;
    }

    int id;
}
