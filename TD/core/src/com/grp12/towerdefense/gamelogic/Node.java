package com.grp12.towerdefense.gamelogic;

public class Node {
    public NodeType getType() {
        return type;
    }

    boolean waypoint;

    enum NodeType {TOWERNODE, PATHNODE}
    private NodeType type;



    public Node(NodeType _type, boolean _waypoint){
        type = _type;
        waypoint = _waypoint;
    }


}
