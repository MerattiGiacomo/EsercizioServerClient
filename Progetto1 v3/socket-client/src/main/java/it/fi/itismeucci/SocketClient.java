package it.fi.itismeucci;
import java.io.*;
import java.net.*;
import java.util.*;

public class SocketClient
{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner keyboard = new Scanner(System.in);
    private String userString;
    private String serverString;

    public Socket connect() throws IOException
    {
        this.socket = new Socket(InetAddress.getLocalHost(), 34567);
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        return socket;
    }
    
    public void send() throws IOException
    {
        System.out.print("Inserisci la stringa da trasmettere al server" + '\n');
        this.userString = this.keyboard.next();
        out.writeBytes(userString + '\n');
        serverString = in.readLine();
        System.out.println("Risposta dal server: " + '\n' + serverString);
        socket.close();
    }
}
