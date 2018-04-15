package com.grp12.towerdefense.gamelogic.enemies;


import java.util.ArrayList;

/*
Class that contains the info a wave of enemies to be sent down the lane
A wave consists of the enemies to be sent + the ones sent by the opponent
 */
public class Wave {

    private AbstractEnemy enemy;
    private int amount;
    private ArrayList<AbstractEnemy> receivedEnemies;
    private ArrayList<AbstractEnemy> enemies;

    public Wave(AbstractEnemy enemy, int amount) {
        this.enemy = enemy;
        this.amount = amount;
        generateEnemiesArray();
    }

    public void setReceivedEnemies(ArrayList<AbstractEnemy> receivedEnemies) {
        this.receivedEnemies = receivedEnemies;
    }

    private void generateEnemiesArray() {
        for(int i = 0; i < amount; i++) {
            enemies.add(enemy.clone());
        }
        for(AbstractEnemy e : receivedEnemies) {
            enemies.add(e);
        }
    }

    public ArrayList<AbstractEnemy> getEnemies() {
        return enemies;
    }
}
