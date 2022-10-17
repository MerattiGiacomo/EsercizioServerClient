package it.fi.itismeucci;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class serverThread extends Thread{
    Socket client;
    ServerSocket server;
    ArrayList<Socket> allsockets;

    serverThread (Socket client, ServerSocket server, ArrayList<Socket> allsockets)
    {
        this.client = client;
        this.server = server;
        this.allsockets = allsockets;
    }
    @Override
    public void run() {
        try {
            allsockets.add(client);
            communicate(client);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore");
        }
    }
    public void communicate(Socket client) throws IOException {
        try{
            String recv = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            while(!recv.toUpperCase().equals("FINE")){
                if(recv.toUpperCase().equals("SPEGNI")){
                    out.writeBytes("\n");
                    for (Socket c: allsockets) {
                        c.close();
                    }
                    server.close();
                    return;
                }
                recv = in.readLine();
                System.out.println(recv);
                System.out.print("Stringa ricevuta: " + recv);
                String modifiedRecv = recv.toUpperCase();
                out.writeBytes(modifiedRecv + '\n');
            }
            client.close();
        }
        catch(Exception e){
            if(e instanceof SocketException)
                System.out.println("Server chiuso");
        }
    }
}
