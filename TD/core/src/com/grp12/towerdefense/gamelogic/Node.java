package com.grp12.towerdefense.gamelogic;

public class Node {
    public NodeType getType() {
        return type;
    }

    boolean waypoint;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    enum NodeType {TOWERNODE, PATHNODE}
    private NodeType type;

    private int x;
    private int y;



    public Node(NodeType _type, boolean _waypoint, int _x, int _y){
        type = _type;
        waypoint = _waypoint;
        x = _x;
        y = _y;
    }


}
