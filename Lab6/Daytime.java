
import java.net.*;
import java.io.*;
import java.util.Date;

public class Daytime {
    InetAddress server;
    int port = 123;

    public static void main(String [] args) {
        try {
            Daytime d = new Daytime("<NTPSERVERNAME>");
            System.out. println(d.getTime());
        }
        catch (IOException ex) {
            System.err.println(ex);
        }   
    }

    public Daytime() throws UnknownHostException {
        this(InetAddress.getLocalHost());
    }

    public Daytime(String name) throws UnknownHostException {
        this(InetAddress.getByName(name));
    }

    public Daytime(InetAddress server) {
        this.server = server;
    }

    public String getTime() {
        if (server == null) return (new Date()).toString();
        try {
            Socket s = new Socket(server, port);
            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String theLine;
            while ((theLine = br.readLine()) != null) {
                sb.append(theLine + "\r\n");
            }
            s.close();
            return sb.toString();
        }
        catch (IOException ex) {
            return (new Date()).toString();
        }
    }
}
    
