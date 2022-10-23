package it.fi.itismeucci;

import java.io.*;
import java.net.*;

public class ClientStr {
    
    String nomeServer = "localhost"; // indirizzo server locale
    int portaServer = 12121; // porta x servizio data e ora
    Socket miosocket;
    BufferedReader tastiera; // buffer per l'input da tastiera
    String StringaUtente; // stringa inserita da utente
    String StringaRicevutaDalServer; // stringa ricevuta dal server
    DataOutputStream outVersoServer; // stream output
    BufferedReader inDalServer; // stream input

    public void comunica() throws IOException {
        
        Comunica C1 = new Comunica();
        ThreadInput tin = new ThreadInput(C1);
        ThreadOutput to = new ThreadOutput(C1);

        to.start();
        tin.start();
    }
}

