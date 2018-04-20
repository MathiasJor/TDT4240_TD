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

    private int numberOfEnemyTypes;
    private int currentEnemyIndex;

    private final double healthIncrease = 0.1;

    public WaveGenerator(ArrayList<AbstractEnemy> enemyTypes) {
        currentWaveNumber = 0;
        currentEnemyHealthBoost = 1;
        numberOfEnemies = 10;
        currentEnemyIndex = 0;
        this.enemyTypes = enemyTypes;
        numberOfEnemyTypes = enemyTypes.size();
        currentWave = new Wave(enemyTypes.get(currentEnemyIndex), numberOfEnemies);
    }

    public void setNextWave(){
        currentEnemyHealthBoost += healthIncrease;
        numberOfEnemies += 10;
        currentWaveNumber++;
        if (currentEnemyIndex < numberOfEnemyTypes) {
            currentEnemyIndex++;
            if (currentEnemyIndex == numberOfEnemyTypes) {
                currentEnemyIndex = 0;
            }
        }

        AbstractEnemy enemy = enemyTypes.get(currentEnemyIndex);
        enemy.setHealth(Math.round(enemy.getHealth()*currentEnemyHealthBoost));
        System.out.println(Math.round(enemy.getHealth()*currentEnemyHealthBoost));
        currentWave = new Wave(enemy, numberOfEnemies);
    }

    public int getCurrentWaveNumber() {
        return currentWaveNumber;
    }

    public Wave getCurrentWave() {
        return currentWave;
    }
}
