package git.fi.itismeucci.merattichat.serben;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        while(true){
            ServerStr servente = new ServerStr();
            servente.attendi();
            servente.comunica();
        }
    }
}
