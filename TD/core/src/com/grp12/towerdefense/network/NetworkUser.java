package com.grp12.towerdefense.network;

import java.util.ArrayList;

/**
 * Created by Kristian on 4/21/2018.
 */

public class NetworkUser {
    int health;
    int gold;

    ArrayList<NetworkTower> towers;

    boolean isTurn;

    int id;


    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public int getId() {
        return id;
    }

    public ArrayList<NetworkTower> getTowers() {
        return towers;
    }
}
