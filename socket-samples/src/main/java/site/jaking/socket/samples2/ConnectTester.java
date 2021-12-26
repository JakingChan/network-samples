package site.jaking.socket.samples2;

import java.io.*;
import java.net.*;
import java.util.stream.Collectors;

/**
 * Create by chenjiacheng on 2021/12/26
 */
public class ConnectTester {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 80;
        if(args.length>1){
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        new ConnectTester().connect(host,port);
    }

    private void connect(String host, int port) {
        SocketAddress remoteAddr = new InetSocketAddress(host,port);
        Socket socket = null;
        String result = "";

        try {
            long begin = System.currentTimeMillis();
            socket = new Socket();
            socket.connect(remoteAddr,6000);
            long end = System.currentTimeMillis();
            result = (end-begin)+" ms";
        } catch (BindException e) {
            result = "BindException: "+e.getMessage();
        } catch (UnknownHostException e) {
            result = "UnknownHostException: "+e.getMessage();
        } catch (ConnectException e) {
            result = "ConnectException: "+e.getMessage();
        } catch (SocketTimeoutException e) {
            result = "SocketTimeoutException: "+e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            result = "IOException: "+e.getMessage();
        } finally {
            try {
                if(socket!=null){
                    socket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println( remoteAddr+" : "+result);
    }

}
