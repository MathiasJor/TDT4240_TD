package com.grp12.towerdefense;

public class Map {

    final int width = 16;
    final int height = 20;

    Node[][] grid;


    public Map(){
        grid = new Node[width][height];
    }
}
