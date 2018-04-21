package com.grp12.towerdefense.gamelogic;

public class PlayerStats {

    private int money;
    private int health;


    public PlayerStats(int money, int health) {
        this.money = money;
        this.health = health;
    }

    public void addMoney(int money) {
        if (money > 0)
            this.money += money;
    }

    public boolean withdrawMoney(int money) {
        if (this.money >= money) {
            this.money -= money;
            return true;
        }
        return false;
    }

    public int getBalance() {
        return money;
    }

    public boolean removeHealth(int damage) {
        if (this.health - damage > 0) {
            this.health -= damage;
            return true;
        } else {
            this.health = 0;
            return false;
        }
    }

    public int getHealth() {
        return health;
    }

}
