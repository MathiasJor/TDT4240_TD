package com.grp12.towerdefense.models.enemies;


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
    private boolean popable;
    private float frameTime;
    private float popDelay;

    public Wave(AbstractEnemy enemy, int amount) {
        this.enemy = enemy;
        this.amount = amount;
        enemies = new ArrayList<AbstractEnemy>();
        generateEnemiesArray();
        popable = true;
        frameTime = 0f;
        popDelay = 1f;
    }

    public void setReceivedEnemies(ArrayList<AbstractEnemy> receivedEnemies) {
        this.receivedEnemies = receivedEnemies;
    }

    private void generateEnemiesArray() {
        //TODO: Implement to include sent enemies
        /*for(AbstractEnemy e : receivedEnemies) {
            enemies.add(e);
        }*/
        for(int i = 0; i < amount; i++) {
            enemies.add(enemy.clone());
        }
    }

    //Returns an enemy if list not empty, and if enough time has passed since the last pop, else returns null
    public AbstractEnemy popAttempt(float dt) {
        frameTime += dt;
        if (frameTime > popDelay)
            popable = true;
        if (enemies.size() > 0 && popable) {
            popable = false;
            frameTime = 0f;
            return enemies.remove(enemies.size()-1);
        }
        return null;
    }

    public boolean empty() {
        return enemies.size() == 0;
    }
}
