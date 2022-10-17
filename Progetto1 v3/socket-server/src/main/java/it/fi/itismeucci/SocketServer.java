package it.fi.itismeucci;

import java.io.*;
import java.net.*;

public class SocketServer{


    public Socket avvia() throws IOException {
        ServerSocket server = new ServerSocket(34567);
        for (;;) {
            Socket client = server.accept();
            myThread newClient = new myThread(client);
            newClient.start();
        }
    }

    
}
