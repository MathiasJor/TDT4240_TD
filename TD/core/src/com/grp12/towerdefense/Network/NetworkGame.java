package com.grp12.towerdefense.Network;

import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

import jdk.nashorn.internal.parser.JSONParser;

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
}