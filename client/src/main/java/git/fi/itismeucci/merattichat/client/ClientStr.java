package git.fi.itismeucci.merattichat.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ClientStr {
    String nomeServer = "localhost";    //indirizzo server locale
    int portaServer = 6789; //porta per servizio
    Socket miosocket;
    BufferedReader tastiera;    //buffer per l'input da tastiera
    String stringaUtente;   //stringa inserita da utente
    String stringaRicevutaDalServer;    //stringa ricevuta dal server
    DataOutputStream outVersoServer;    //stream di output
    BufferedReader inDalServer;   //stream di input

    public Socket connect(){
        System.out.println("2 client partito in esecuzione . . .");
        try{
            //per l'input da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            //creao un socket
            miosocket = new Socket(nomeServer, portaServer);
            //miosocket = new Socket(InetAddress.getLocalhost(), 6789);
            //assioco due oggetti al socket per effettuare la scrittura e la lettura
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader (miosocket.getInputStream()));
        }
        catch(UnknownHostException e){
            System.err.println("host sconosciuto");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connesione!");
            System.exit(1);
        }
        return miosocket;
    }
    
    public void comunica(){
        try{
            System.out.println("4 ... inserisci la stringa da trasmettere al server"+'\n');
            stringaUtente = tastiera.readLine();
            //la spedisco al server
            System.out.println("5 ... invio la stringa la server e attendo ...");
            //leggo la risposta dal server
            outVersoServer.writeBytes(stringaUtente + '\n');
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("8 ... risposta dal server" +'\n' +stringaRicevutaDalServer);
            //chiudo la connessione
            System.out.println("9 Client: termina elaborazione e chiude la connessione");
            miosocket.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore dutante la connessione col server");
            System.exit(1);
        }
    }
}
