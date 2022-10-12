package it.fi.itismeucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class myThread extends Thread{
    Socket client;
    public myThread(Socket client){
        this.client = client;
    }

    public void run() {
        try {
            communicate(client);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore di server");
        }
    }

    void communicate(Socket client) throws IOException {
        String recv = "";
        while(!recv.toUpperCase().equals("FINE")){
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            recv = in.readLine();
            System.out.print("Stringa ricevuta: " + recv);
            String modifiedRecv = recv.toUpperCase();
            out.writeBytes(modifiedRecv + '\n');
        }
        client.close();

    }
}
