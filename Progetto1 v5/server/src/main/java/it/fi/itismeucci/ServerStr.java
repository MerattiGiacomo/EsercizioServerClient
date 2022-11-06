package it.fi.itismeucci;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerStr {

    ArrayList<Socket>SS = new ArrayList<>();

    public void avvia() throws IOException {
        System.out.println("1 Server partito in esecuzione ... ");
        ServerSocket server = new ServerSocket(12121);
        for (;;) {
            Socket client;
            try {
                client = server.accept();
                SS.add(client);
                Thread t = new Thread(() -> comunica(client, server));
                t.start();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    public void comunica(Socket client, ServerSocket server) {
        try {
            for (;;) {
                BufferedReader inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                DataOutput outVersoClient = new DataOutputStream(client.getOutputStream());
                System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiscuolo. Attendo ...");
                String StringaRicevuta = inDalClient.readLine();
                if (!StringaRicevuta.equals("fine") && !StringaRicevuta.equals("spegni")) {
                    System.out.println("6 ricevuta la stringa dal cliente: " + StringaRicevuta);
                    String StringaModificata = StringaRicevuta.toUpperCase();
                    System.out.println("7 invio la stringa modificata al client ...");
                    outVersoClient.writeBytes(StringaModificata + '\n');
                } else if (StringaRicevuta.equals("spegni")) {
                    for (Socket i : SS) {
                        DataOutputStream nb = new DataOutputStream(i.getOutputStream());
                        nb.writeBytes("spegni" + '\n');
                        i.close();
                    }
                    System.out.println("Server spento");
                    server.close();
                    break;
                } 
                else {
                    client.close();
                    break;
                }
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("E22");
            System.exit(1);
        }
        // server.close();
    }
}
