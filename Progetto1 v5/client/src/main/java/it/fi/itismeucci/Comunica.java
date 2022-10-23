package it.fi.itismeucci;

import java.io.*;
import java.net.*;

public class Comunica {

    String nomeServer = "localhost"; // indirizzo server locale
    int portaServer = 12121; // porta x servizio data e ora
    Socket miosocket;
    BufferedReader tastiera; // buffer per l'input da tastiera
    String StringaUtente; // stringa inserita da utente
    String StringaRicevutaDalServer; // stringa ricevuta dal server
    DataOutputStream outVersoServer; // stream output
    BufferedReader inDalServer; // stream input

    public void Output() throws IOException {
        tastiera = new BufferedReader(new InputStreamReader(System.in));
        miosocket = new Socket(nomeServer, portaServer);
        outVersoServer = new DataOutputStream(miosocket.getOutputStream());
        inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        for (;;) {
            System.out.println("4 ... inserisci la stringa da trasmettere al server" + '\n');
            StringaUtente = tastiera.readLine();
            if (!StringaUtente.equals("fine") && !StringaUtente.equals("spegni")) {
                System.out.println("5 ... invio la stringa al server e attendo ... ");
                outVersoServer.writeBytes(StringaUtente + '\n');
                StringaRicevutaDalServer = inDalServer.readLine();
                System.out.println("8 ... risposta dal server " + '\n' + StringaRicevutaDalServer);
            } else {
                outVersoServer.writeBytes(StringaUtente + '\n');
                miosocket.close();
                System.out.println("FINE");
                break;
            }
        }
    }

    public void Input() throws IOException {

        StringaRicevutaDalServer = inDalServer.readLine();
        if (StringaRicevutaDalServer.equals("spegni")) {
            miosocket.close();
            System.out.println("FINE");
        }
    }
}
