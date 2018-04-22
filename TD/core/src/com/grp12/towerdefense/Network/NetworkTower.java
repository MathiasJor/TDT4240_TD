package com.grp12.towerdefense.network;

public class NetworkTower {
    int type;
    int level;
    float x;
    float y;

    public int getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public NetworkTower(int type, int level, float x, float y) {
        this.type = type;
        this.level = level;
        this.x = x;
        this.y = y;


    }
}
