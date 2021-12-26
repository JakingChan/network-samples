package site.jaking.socket.samples2;

import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Create by chenjiacheng on 2021/12/26
 */
public class SpamCheck {

    public static final String BLACK_HOLE = "sb1.spamhaus.org";


    public static void main(String[] args) {
        String host = "xqq.com";
        boolean isActive = isSpammer(host);
        System.out.println("host -> " + host + "\tactive -> " + isActive);
    }

    private static boolean isSpammer(String host) {
        try {
            InetAddress address = InetAddress.getByName(host);
            byte[] quad = address.getAddress();
            String query = BLACK_HOLE;
            for (byte octet : quad) {
                int unsigned = octet < 0 ? octet + 256 : octet;
                query = unsigned+"."+query;
            }
            System.out.println("query = " + query);
            InetAddress.getByName(query);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }


}
