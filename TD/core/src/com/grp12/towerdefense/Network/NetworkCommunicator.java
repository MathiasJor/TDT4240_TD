package com.grp12.towerdefense.Network;

import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grp12.towerdefense.gamelogic.PlayerStats;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Kristian on 4/18/2018.
 */

public class NetworkCommunicator {
    static int userId = 0;
    static GsonBuilder builder = new GsonBuilder();

    static public int getUserId(){
        return userId;
    }


    public static ArrayList<NetworkGame> userGames = new ArrayList<NetworkGame>();

    String hostName = "localhost";
    int port = 9999;

    public NetworkCommunicator(){
    }

    public static void fetchExternalUserId(){
        Thread executor = new Thread(){
            public void run(){
                try{

                    Socket s = new Socket("localhost", 9999);
                    PrintWriter out = new PrintWriter(s.getOutputStream());
                    System.out.println("This worked!");

                    out.write("{\"type\":\"connect\", \"userId\": \"null\"}");
                    out.flush();
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                    Gson gs = builder.create();
                    String line;
                    while((line = in.readLine()) != null){
                        System.out.println(line);
                        UserIdResponse res = gs.fromJson(line, UserIdResponse.class);
                        userId = res.userId;
                    }
                    System.out.println(userId);
                    out.close();
                    in.close();
                    s.close();
                    NetworkCommunicator.fetchGames();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        };
        executor.start();
    }

    public static void fetchGames(){
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
                        builder.setLenient();
                        Gson gs = builder.create();
                        try{
                            NetworkGame game = gs.fromJson(line, NetworkGame.class);
                            if(game != null)
                                userGames.add(game);
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }

                    System.out.println(userGames.size());

                    System.out.println(userGames.get(0).getId());
                    System.out.println(userGames.get(0).getUser(0).gold);

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

    public static void newGameRequest(){
        Thread executor = new NewGameThread();
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
            out.write(java.lang.String.format("{\"type\":\"endTurn\", \"userId\":%d,  \"gameId\":%d,  \"userHealth\":%d,  \"userGold\":%d, \"sentCreatures\":\"\"}", NetworkCommunicator.userId, gameId, ps.getHealth(), ps.getBalance()));
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

class NewGameThread extends Thread{
    public void run(){
        try{

            Socket s = new Socket("localhost", 9999);
            PrintWriter out = new PrintWriter(s.getOutputStream());

            //TODO: Add health to the string. (Not done due to health not being implemented on branch at the time)
            out.write(java.lang.String.format("{\"type\":\"newGame\", \"userId\":%d}", NetworkCommunicator.userId));
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

class UserIdResponse{
    public int userId;
    String type;
}