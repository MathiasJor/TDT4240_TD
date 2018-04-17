package com.grp12.towerdefense.gamelogic;

import java.util.ArrayList;

public class Map {

    final int width = 16;
    final int height = 20;

    private Node[][] grid;

    ArrayList<Node> waypoints;


    public Map() {
        grid = new Node[height][width];
        waypoints = new ArrayList<Node>();

        char mapArray[][] = {
                { '0', '0', 'S', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', 'R', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 'D', '0', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0' },
                { '0', '0', 'D', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 'L', '0', '0' },
                { '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', 'R', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 'D', '0', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0' },
                { '0', '0', '0', 'D', '1', '1', '1', '1', 'L', '0', '0', '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '0', '0', '0', '0', 'U', '1', '1', '1', '1', 'L', '0', '0' },
                { '0', '0', '0', 'E', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
        };

        generateMap(mapArray);

        /*
                    Empty 16h*20w map template
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',

         */

    }

    public void generateMap(char[][] mapArray) {
        //Determines current row (y coordinate)
        int realY = height;
        for (int y = 0; y < height; y++) {
            realY--;
            //Determines current column (x coordinate)
            for (int x = 0; x < width; x++) {
                Node tempNode;
                switch (mapArray[y][x]) {
                    case '0':
                        tempNode = new Node(Node.NodeType.TOWERNODE, x, realY);
                        grid[realY][x] = tempNode;
                        break;
                    case '1':
                        tempNode = new Node(Node.NodeType.PATHNODE, x, realY);
                        grid[realY][x] = tempNode;
                        break;
                    case 'S':
                        tempNode = new Node(Node.NodeType.PATHNODE, x, realY);
                        grid[realY][x] = tempNode;
                        //waypoints.add(tempNode);
                        break;
                    case 'E':
                        tempNode = new Node(Node.NodeType.PATHNODE, x, realY);
                        grid[realY][x] = tempNode;
                        //waypoints.add(tempNode);
                        break;
                    case 'R':
                        tempNode = new Node(Node.NodeType.PATHNODE, x, realY);
                        grid[realY][x] = tempNode;
                        //waypoints.add(tempNode);
                        break;
                    case 'L':
                        tempNode = new Node(Node.NodeType.PATHNODE, x, realY);
                        grid[realY][x] = tempNode;
                        //waypoints.add(tempNode);
                        break;
                    case 'U':
                        tempNode = new Node(Node.NodeType.PATHNODE, x, realY);
                        grid[realY][x] = tempNode;
                        //waypoints.add(tempNode);
                        break;
                    case 'D':
                        tempNode = new Node(Node.NodeType.PATHNODE, x, realY);
                        grid[realY][x] = tempNode;
                        //waypoints.add(tempNode);
                        break;
                }
            }
        }

        //TODO possibly add functionality for start node to be on on another height?
        //TODO Coordinate system has weird axes (0, 0 is top left). FIXED, but could be better implemented
        // Set up waypoints
        int currentX = 0;
        int currentY = 0;
        realY = height - 1;
        char currentDir = 'R';
        boolean startNodeFound = false;
        boolean endNodeFound = false;

        //Search top row for starting node
        while (!endNodeFound) {
            //System.out.println(currentX);
            switch (currentDir) {
                case 'R':
                    currentX++;
                    break;
                case 'L':
                    currentX--;
                    break;
                case 'U':
                    currentY--;
                    realY++;
                    break;
                case 'D':
                    currentY++;
                    realY--;
                    break;
            }

            if (mapArray[currentY][currentX] == 'S') {
                //Add starting node as first waypoint
                waypoints.add(grid[realY][currentX]);
                startNodeFound = true;
                currentDir = 'D';
            }
            if (mapArray[currentY][currentX] == 'E') {
                //Add starting node as first waypoint
                waypoints.add(grid[realY][currentX]);
                endNodeFound = true;
            }
            if (startNodeFound) {
                switch (mapArray[currentY][currentX]) {
                    case 'R':
                        currentDir = 'R';
                        waypoints.add(grid[realY][currentX]);
                        break;
                    case 'L':
                        currentDir = 'L';
                        waypoints.add(grid[realY][currentX]);
                        break;
                    case 'U':
                        currentDir = 'U';
                        waypoints.add(grid[realY][currentX]);
                        break;
                    case 'D':
                        currentDir = 'D';
                        waypoints.add(grid[realY][currentX]);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public Node[][] getGrid() {
        return grid;
    }

    public ArrayList<Node> getWaypoints() {
        return waypoints;
    }
}
