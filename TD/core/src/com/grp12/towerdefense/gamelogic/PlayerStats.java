package com.grp12.towerdefense.gamelogic;

public class PlayerStats {

    private int money;

    public PlayerStats() {
        money = 0;
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

}
