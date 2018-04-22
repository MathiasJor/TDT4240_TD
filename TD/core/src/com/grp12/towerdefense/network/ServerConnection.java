package com.grp12.towerdefense.network;

import com.grp12.towerdefense.models.enemies.AbstractEnemy;

import java.util.ArrayList;

public class ServerConnection {


    public void sendResult(int healthpoints, ArrayList<AbstractEnemy> sendEnemies, int waveNumber) {
        //TODO: code that sends this info to the server
    }















    /*public void ping() {
        new Thread(new ClientThread()).start();
    }

    class ClientThread implements Runnable {

        public void run() {
            try {
                URL url = new URL("http://tdt4240tdserver.azurewebsites.net/test");
                HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setChunkedStreamingMode(0);
                System.out.println(connection.getResponseCode());
                connection.disconnect();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }*/
}
