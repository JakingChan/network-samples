package site.jaking.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Enumeration;
import java.util.List;

public class SocketSamples {
    public static void main(String[] args) throws IOException {
        String str = "\r\n\r\n";
        byte[] bytes = str.getBytes();
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
    }
}
