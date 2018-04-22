package com.grp12.towerdefense.network2;

import java.util.ArrayList;

/**
 * Created by Kristian on 4/19/2018.
 */

public class NetworkGame {
    ArrayList<NetworkUser> users;
    public NetworkUser getUser(int uid){
        return users.get(uid);
    }

    public boolean isMyTurn(){
        for (NetworkUser user : users){
            if (user.getId() == NetworkCommunicator.getUserId()){
                return user.isTurn();
            }
        }
        return false;
    }

    public int getSentCreatures() {
        return sentCreatures;
    }

    int sentCreatures;

    public int getId() {
        return id;
    }

    int id;

    public int getWaveNumber() {
        return waveNumber;
    }
    int waveNumber;

    public NetworkUser getPhoneUser(){
        for (NetworkUser u : users){
            if (u.getId() == NetworkCommunicator.getUserId()){
                return u;
            }
        }
        return null;
    }

    public void setTurn(boolean bol){
        getPhoneUser().isTurn = bol;
    }

    public boolean isSecondPlayer(){
        return getUser(1).getId() == NetworkCommunicator.getUserId();
    }

    public NetworkUser getOpponent() {
        for (NetworkUser u : users){
            if (u.getId() != NetworkCommunicator.getUserId()){
                return u;
            }
        }
        return null;
    }
}