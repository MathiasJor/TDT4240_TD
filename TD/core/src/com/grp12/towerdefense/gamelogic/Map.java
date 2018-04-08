package com.grp12.towerdefense.gamelogic;

import java.util.ArrayList;

public class Map {

    final int width = 16;
    final int height = 20;

    private Node[][] grid;

    ArrayList<Node> waypoints;


    public Map(){
        grid = new Node[height][width];
    }

    public void generateMap(){
        
    }

    public Node[][] getGrid() {
        return grid;
    }
}
