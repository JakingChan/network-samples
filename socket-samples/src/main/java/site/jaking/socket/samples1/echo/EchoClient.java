package site.jaking.socket.samples1.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Create by chenjiacheng on 2021/12/26
 */
public class EchoClient {
    private String host = "localhost";
    private int port = 8000;
    private Socket socket;

    public EchoClient() throws IOException {
        socket = new Socket(host,port);
    }

    public static void main(String[] args) throws IOException {
        new EchoClient().talk();
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(),true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void talk() {
        try {
            PrintWriter writer = getWriter(socket);
            BufferedReader reader = getReader(socket);

            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg=localReader.readLine())!=null){
                writer.println(msg);
                System.out.println(reader.readLine());
                if(msg.equals("bye")){
                    break;
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
