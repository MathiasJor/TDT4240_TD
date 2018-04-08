package com.grp12.towerdefense.gamelogic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map {

    final int width = 16;
    final int height = 20;

    private Node[][] grid;

    ArrayList<Node> waypoints;


    public Map(){
        grid = new Node[height][width];
        waypoints = new ArrayList<Node>();
        generateMap(
                "oosooooooooooooo\n" +
                "ooxooooooooooooo\n" +
                "ooxooooooooooooo\n" +
                "ooxxxxxxxxxxoooo\n" +
                "oooooooooooxoooo\n" +
                "oooooooooooxoooo\n" +
                "oooooooooooxoooo\n" +
                "ooxxxxxxxxxxoooo\n" +
                "ooxooooooooooooo\n" +
                "ooxxxxxxxxoooooo\n" +
                "oooooooooxoooooo\n" +
                "oooooooooxoooooo\n" +
                "oooooooooxxxxooo\n" +
                "ooooooooooooxooo\n" +
                "oooooooxxxxxxooo\n" +
                "oooooooxoooooooo\n" +
                "oooooooxoooooooo\n" +
                "oooooooxxxxxxooo\n" +
                "ooooooooooooxooo\n" +
                "ooooooooooooeooo");
    }

    public void generateMap(String mapString){
        System.out.println(mapString);
        String[] rows = mapString.split("\n");

        for(int y = 0; y < rows.length; y++){
            String row = rows[y];
            for(int x = 0; x < row.length(); x++){
                switch(row.charAt(x)){
                    case 'o':
                        grid[y][x] = new Node(Node.NodeType.TOWERNODE, false, x, y);
                        break;
                    case 'x':
                        grid[y][x] = new Node(Node.NodeType.PATHNODE, false, x, y);
                        break;
                    default:
                        Node waypoint = new Node(Node.NodeType.PATHNODE, true, x, y);
                        waypoints.add(waypoint);
                        break;
                }
            }
        }


    }

    public Node[][] getGrid() {
        return grid;
    }
}
