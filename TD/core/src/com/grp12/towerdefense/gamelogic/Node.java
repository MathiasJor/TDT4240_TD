package com.grp12.towerdefense.gamelogic;

import com.badlogic.gdx.math.Vector2;

public class Node {

    public enum NodeType {TOWERNODE, PATHNODE}
    private NodeType type;

    private Vector2 position = new Vector2();

    int pixelSize;

    public Node(NodeType type, int x, int y){
        this.type = type;
        this.position.x = x;
        this.position.y = y;
    }

    public NodeType getType() {
        return type;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position){
        this.position = position;
    }
}
