package git.fi.itismeucci.merattichat.client;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClientStr cliente = new ClientStr();
        cliente.connect(); 
        cliente.comunica();
    }
}
