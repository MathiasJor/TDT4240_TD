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
        //temporary code for a hard coded map
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if ((i+j)%2 == 0) {
                    grid[i][j] = new Node(Node.NodeType.TOWERNODE);
                } else {
                    grid[i][j] = new Node(Node.NodeType.PATHNODE);
                }
            }
        }

    }

    public Node[][] getGrid() {
        return grid;
    }
}
