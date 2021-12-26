package site.jaking.socket.samples2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Create by chenjiacheng on 2021/12/26
 */
public class PortScanner {
    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {
            host = args[0];
        }
        new PortScanner().scan(host);
    }

    private void scan(String host) {
        Socket socket = null;
        for (int port = 1; port < 1024; port++) {
            try {
                socket = new Socket(host, port);
                System.out.println("There is a server on port "+port);
            } catch (IOException e) {
//                e.printStackTrace();
                System.out.println("Can't connect to port "+port);
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void scan2(String host) {
        Socket socket = null;
        for (int port = 1; port < 1024; port++) {
            try {
                socket = new Socket();
                InetSocketAddress remoteAddr = new InetSocketAddress(host,port);
                // 连接并设置超时时间
                socket.connect(remoteAddr,6000);
                System.out.println("There is a server on port "+port);
            } catch (IOException e) {
//                e.printStackTrace();
                System.out.println("Can't connect to port "+port);
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
