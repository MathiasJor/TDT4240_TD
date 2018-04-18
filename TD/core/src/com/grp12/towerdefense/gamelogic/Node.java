package com.grp12.towerdefense.gamelogic;

import com.badlogic.gdx.math.Vector2;
import com.grp12.towerdefense.gamelogic.towers.AbstractTower;

public class Node {

    public enum NodeType {TOWERNODE, PATHNODE}
    private NodeType type;

    private Vector2 position = new Vector2();

    private AbstractTower tower;

    int pixelSize;

    public Node(NodeType type, int x, int y){
        this.type = type;
        this.position.x = x;
        this.position.y = y;
        tower = null;
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

    //TODO: Successfully setting this sets the tower.setNode as well
    public boolean setTower(AbstractTower abstractTower) {
        if (type == NodeType.TOWERNODE && tower == null) {
            this.tower = abstractTower;
            return true;
        } else {
            return false;
        }
    }

    public void removeTower() {
        this.tower = null;
    }

    public AbstractTower getTower() {
        return this.tower;
    }
}
