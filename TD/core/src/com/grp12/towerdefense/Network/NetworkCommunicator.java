package com.grp12.towerdefense.Network;

import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Kristian on 4/18/2018.
 */

public class NetworkCommunicator {
    public static int userId;

    String hostName = "localhost";
    int port = 9999;

    public NetworkCommunicator(){
    }

    public static int getUserId(){
        try{

            Socket s = new Socket("localhost", 9999);
            PrintWriter out = new PrintWriter(s.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("This worked!");

            out.write("{\"type\":\"connect\", \"userId\": \"null\"}");
            String line;
            while((line = in.readLine()) != null){
                System.out.println("whelp?");
                System.out.println(line);
            }
            out.close();
            in.close();
            s.close();

        }catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public String getGames(){
        AsyncExecutor exec = new AsyncExecutor(2);
        exec.submit(new GetGameTask());
        return "";
    }
}


class GetGameTask<String> implements AsyncTask<String> {

    @Override
    public String call() throws Exception {
        try{

            Socket s = new Socket("localhost", 9999);
            PrintWriter out = new PrintWriter(s.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            out.write(java.lang.String.format("{\"type\":\"getGames\", \"userId\":%d}", NetworkCommunicator.userId));

            System.out.println();
        }catch(Exception e){
        }
        return (String) "";
    }
}

class GetUserIdTask implements AsyncTask<Integer> {

    @Override
    public Integer call() throws Exception {
        try{

            Socket s = new Socket("localhost", 9999);
            PrintWriter out = new PrintWriter(s.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("This worked!");

            out.write("{\"type\":\"connect\", \"userId\": \"null\"}");
            String line;
            while((line = in.readLine()) != null){
                System.out.println("whelp?");
                System.out.println(line);
            }
            out.close();
            in.close();
            s.close();

        }catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }
}