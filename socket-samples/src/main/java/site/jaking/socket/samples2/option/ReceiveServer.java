package site.jaking.socket.samples2.option;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.stream.Collectors;

/**
 * Create by chenjiacheng on 2021/12/27
 */
public class ReceiveServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket socket = serverSocket.accept();
        socket.setSoTimeout(20*1000);
        InputStream in = socket.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        byte[]buff = new byte[8];
        int len = -1;
        do {
            try {
                len = in.read(buff);
                if(len!=-1){
                    buffer.write(buff,0,len);
                }
            }catch (SocketTimeoutException e){
                System.out.println("读取超时");
                len = 0;
            }
        }while (len!=-1);

        System.out.println(buffer);

    }
}
