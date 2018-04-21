package com.grp12.towerdefense.Network;

import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.grp12.towerdefense.gamelogic.PlayerStats;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Kristian on 4/18/2018.
 */

public class NetworkCommunicator {
    public static int userId = 0;

    String hostName = "localhost";
    int port = 9999;

    public NetworkCommunicator(){
    }

    public static void getExternalUserId(){
        Thread executor = new Thread(){
            public void run(){
                try{

                    Socket s = new Socket("localhost", 9999);
                    PrintWriter out = new PrintWriter(s.getOutputStream());
                    System.out.println("This worked!");

                    out.write("{\"type\":\"connect\", \"userId\": \"null\"}");
                    out.flush();
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String line;
                    while((line = in.readLine()) != null){
                        System.out.println(line);
                    }
                    out.close();
                    in.close();
                    s.close();
                    NetworkCommunicator.getGames();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        };
        executor.start();
    }

    public static void getGames(){
        Thread executor = new Thread(){
            public void run(){
                try{

                    Socket s = new Socket("localhost", 9999);
                    PrintWriter out = new PrintWriter(s.getOutputStream());
                    System.out.println("This worked!");

                    out.write(java.lang.String.format("{\"type\":\"getGames\", \"userId\":%d}", NetworkCommunicator.userId));
                    out.flush();
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String line;
                    while((line = in.readLine()) != null){
                        System.out.println(line);
                    }
                    out.close();
                    in.close();
                    s.close();

                }catch(Exception e){
                    System.out.println(e);
                }
            }
        };
        executor.start();
    }

    public static void sendEndTurnMessage(int gameId, PlayerStats ps){
        Thread executor = new EndTurnThread(gameId, ps);
        executor.start();
    }
}

class EndTurnThread extends Thread{
    int gameId;
    PlayerStats ps;

    public EndTurnThread(int gameId, PlayerStats ps){
        this.gameId = gameId;
        this.ps = ps;
    }
    public void run(){
        try{

            Socket s = new Socket("localhost", 9999);
            PrintWriter out = new PrintWriter(s.getOutputStream());
            System.out.println("This worked!");

            //TODO: Add health to the string. (Not done due to health not being implemented on branch at the time)
            out.write(java.lang.String.format("{\"type\":\"endTurn\", \"userId\":%d,  \"gameId\":%d,  \"userHealth\":%d,  \"userGold\":%d}", NetworkCommunicator.userId, gameId, 30, ps.getBalance()));
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                System.out.println(line);
            }
            out.close();
            in.close();
            s.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }
}