package com.grp12.towerdefense.gamelogic.enemies;

import java.util.ArrayList;

/*

This file is supposed to control the current wave, rotate through the different wave types (different enemies etc).

 */

public class WaveGenerator {

    private int currentWaveNumber;
    private Wave currentWave;

    private ArrayList<AbstractEnemy> enemyTypes;
    private float currentEnemyHealthBoost;
    private int numberOfEnemies;
    private int enemiesReceived;

    private int numberOfEnemyTypes;
    private int currentEnemyIndex;

    private final double healthIncrease = 0.1;
    private final int intervalToIncreaseEnemies = 5;
    private final int increaseEnemiesBy = 1;

    public WaveGenerator(ArrayList<AbstractEnemy> enemyTypes) {
        currentWaveNumber = 0;
        currentEnemyHealthBoost = 1;
        numberOfEnemies = 10;
        enemiesReceived = 0;
        currentEnemyIndex = 0;
        this.enemyTypes = enemyTypes;
        numberOfEnemyTypes = enemyTypes.size();
        currentWave = new Wave(enemyTypes.get(currentEnemyIndex), numberOfEnemies);
    }

    public void setCurrentWaveNumber(int waveNumber){
        currentWaveNumber = waveNumber;
    }

    public void setNextWave(){
        currentEnemyHealthBoost += healthIncrease;
        //currentWaveNumber++;

        //Increase enemies by "increaseEnemiesBy" every "intervalToIncreaseEnemies"th round
        if (currentWaveNumber % intervalToIncreaseEnemies == 0) {
            numberOfEnemies += increaseEnemiesBy;
        }

        //Used to cycle through different enemy types, depending on how many are available.
        currentEnemyIndex = currentWaveNumber % numberOfEnemyTypes;

        /*
        if (currentEnemyIndex < numberOfEnemyTypes) {
            currentEnemyIndex++;
            if (currentEnemyIndex == numberOfEnemyTypes) {
                currentEnemyIndex = 0;
            }
        } */

        //Increase enemy health by "currentEnemyHealthBoost"
        AbstractEnemy enemy = enemyTypes.get(currentEnemyIndex);
        enemy.setHealth(Math.round(enemy.getHealth() * currentEnemyHealthBoost));

        //Check if we received additional enemies from opponent
        if (enemiesReceived > 0) {
            currentWave = new Wave(enemy, numberOfEnemies + enemiesReceived);
            enemiesReceived = 0;
        } else {
            currentWave = new Wave(enemy, numberOfEnemies);
        }
    }

    public void setReceivedEnemies(int enemiesReceived) {
        this.enemiesReceived = enemiesReceived;
    }

    public int getCurrentWaveNumber() {
        return currentWaveNumber;
    }

    public Wave getCurrentWave() {
        return currentWave;
    }

    public int getCurrentEnemyIndex() {
        return currentEnemyIndex;
    }
}
