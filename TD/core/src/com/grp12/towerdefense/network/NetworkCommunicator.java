package com.grp12.towerdefense.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grp12.towerdefense.models.PlayerStats;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Kristian on 4/18/2018.
 */

public class NetworkCommunicator {
    static int userId = 0;
    static GsonBuilder builder = new GsonBuilder();

    static Timer networkTimer;

    private static boolean updatedGameList = false;

    public static NetworkGame getActiveGame() {
        return activeGame;
    }

    public static void setActiveGame(NetworkGame activeGame) {
        NetworkCommunicator.activeGame = activeGame;
    }

    static NetworkGame activeGame;

    static public int getUserId(){
        return userId;
    }


    public static ArrayList<NetworkGame> userGames = new ArrayList<NetworkGame>();

    public static String hostName = "localhost"; //"10.22.12.149";
    public static int port = 9999;

    public NetworkCommunicator(){
    }

    public static void startNetworkTimer(){
        networkTimer = new Timer();
        networkTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                NetworkCommunicator.fetchGames();
            }
        }, 4*1000, 4*1000);
    }


    public static void fetchExternalUserId(){
        Thread executor = new Thread(){
            public void run(){
                try{

                    Socket s = new Socket(hostName, port);
                    PrintWriter out = new PrintWriter(s.getOutputStream());

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
                    NetworkCommunicator.startNetworkTimer();
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

                    Socket s = new Socket(hostName, port);
                    PrintWriter out = new PrintWriter(s.getOutputStream());

                    out.write(java.lang.String.format("{\"type\":\"getGames\", \"userId\":%d}", NetworkCommunicator.userId));
                    out.flush();
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String line;
                    //Got to flush
                    userGames = new ArrayList<NetworkGame>();
                    while((line = in.readLine()) != null){


                        //System.out.println(line);
                        builder.setLenient();
                        Gson gs = builder.create();
                        try{
                            NetworkGame game = gs.fromJson(line, NetworkGame.class);
                            if(game != null)
                                userGames.add(game);
                        }catch (Exception e){
                            //System.out.println(e);
                        }
                    }
//
//                    System.out.print("Game list size: ");
//                    System.out.print(userGames.size());
//                    System.out.println();

                    setUpdatedGameList(true);

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

    public static void sendEndTurnMessage(int gameId, PlayerStats ps, int sentCreatures, int waveNumber){
        Thread executor = new EndTurnThread(gameId, ps, sentCreatures, waveNumber);
        executor.start();
    }

    public static boolean isUpdatedGameList() {
        return updatedGameList;
    }

    public static void setUpdatedGameList(boolean updatedGameList) {
        NetworkCommunicator.updatedGameList = updatedGameList;
    }
}

class EndTurnThread extends Thread{
    int gameId;
    int sentCreatures = 0;
    PlayerStats ps;
    int waveNumber;

    public EndTurnThread(int gameId, PlayerStats ps, int sentCreatures, int waveNumber){
        this.gameId = gameId;
        this.ps = ps;
        this.sentCreatures = sentCreatures;
        this.waveNumber = waveNumber;
    }
    public void run(){
        try{

            Socket s = new Socket(NetworkCommunicator.hostName, NetworkCommunicator.port);
            PrintWriter out = new PrintWriter(s.getOutputStream());

            //TODO: Add health to the string. (Not done due to health not being implemented on branch at the time)
            out.write(java.lang.String.format("{\"type\":\"endTurn\", \"userId\":%d,  \"gameId\":%d,  \"userHealth\":%d,  \"userGold\":%d, \"sentCreatures\":%d, \"waveNumber\":%d}", NetworkCommunicator.userId, gameId, ps.getHealth(), ps.getBalance(), sentCreatures, waveNumber));
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

            Socket s = new Socket(NetworkCommunicator.hostName, NetworkCommunicator.port);
            PrintWriter out = new PrintWriter(s.getOutputStream());

            //TODO: Add health to the string. (Not done due to health not being implemented on branch at the time)
            out.write(java.lang.String.format("{\"type\":\"newGame\", \"userId\":%d}", NetworkCommunicator.userId));
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                System.out.println(line);
            }

            //OK, so we got a response. It might have been the game, but it might not have.
            //So we just pull the server to get our game list. If we got a game, the list updates.
            //If not we'll have to wait until our pooling returns with a new game.
            NetworkCommunicator.fetchGames();

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