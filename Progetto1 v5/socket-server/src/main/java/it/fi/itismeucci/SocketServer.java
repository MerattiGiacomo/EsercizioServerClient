package it.fi.itismeucci;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class SocketServer  {

    public void avvia() throws IOException {
        ServerSocket server = new ServerSocket(34567);
        ArrayList<Socket> allsockets = new ArrayList<>();

        for (;;) {
            try
            {
                Socket client = server.accept();
                serverThread t = new serverThread(client, server, allsockets);
                t.start();
            }
            catch (Exception e)
            {
                if (server.isClosed())
                    break;
            }
        }
    }
}
