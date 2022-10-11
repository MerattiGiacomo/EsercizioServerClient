package git.fi.itismeucci.merattichat.serben;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String StringaRicevuta = null;
    String StringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi() {
        try {
            System.out.println("1 Server partito in esecuzione ... ");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'instanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica() {
        try {
            System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiscuolo. Attendo ...");
            StringaRicevuta = inDalClient.readLine();
            System.out.println("6 ricevuta la stringa dal cliente: " + StringaRicevuta);

            StringaModificata = StringaRicevuta.toUpperCase();
            System.out.println("7 invio la stringa modificata ...");
            outVersoClient.writeBytes(StringaModificata+'\n');

            System.out.println("9 SERVER: fine elaborazione ...");
            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'instanza del server");
            System.exit(1);
        }
    }
}

