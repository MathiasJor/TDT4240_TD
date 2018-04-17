package com.grp12.towerdefense.Network;

import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class ServerConnection {

    private Socket socket;

    public void ping() {
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


    }
}
