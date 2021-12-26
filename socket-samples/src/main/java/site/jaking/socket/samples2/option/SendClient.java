package site.jaking.socket.samples2.option;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Create by chenjiacheng on 2021/12/27
 */
public class SendClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.setTcpNoDelay(true);
        socket.connect(new InetSocketAddress("localhost",8000));

        OutputStream out = socket.getOutputStream();
        out.write("hello\r\n".getBytes());
        out.write("every one\r\n".getBytes());
        out.flush();
        Thread.sleep(10*1000);
        socket.close();
    }
}
