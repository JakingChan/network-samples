package site.jaking.socket.samples2;

import site.jaking.socket.util.ArraysAsset;
import site.jaking.socket.util.GZIPUtils;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Create by chenjiacheng on 2021/12/26
 */
public class HttpClient {
    String host = "www.javathinker.net";
    int port = 80;
    Socket socket;
    //    private static final String CRLF = System.lineSeparator();
    private static final String CRLF = "\r\n";

    public void createSocket() throws IOException {
        socket = new Socket(host, port);
    }

    public void communicate() throws IOException {
        //1. 组织报文
        StringBuffer message = new StringBuffer();
        //1.1 start line
        message.append("GET /bbs_index.do HTTP/1.1").append(CRLF);
        //1.2 header lines
        message.append("Host: ").append(host).append(CRLF);
        message.append("Accept: ").append("*/*").append(CRLF);
        message.append("Accept-Language: ").append("zh-CN,zh;q=0.9").append(CRLF);
        message.append("Accept-Encoding: ").append("gzip, deflate").append(CRLF);
        message.append("User-Agent: ").append("HTTPClient").append(CRLF);
        message.append("Connection: ").append("Keep-Alive").append(CRLF);
        //1.3 空白行
        message.append(CRLF);
        //1.4 实体（可有可无，按需写入）

        //2. 写入报文
        OutputStream socketOut = socket.getOutputStream();
        socketOut.write(message.toString().getBytes(StandardCharsets.UTF_8));
        socketOut.flush();

        //3. 接收响应结果
        InputStream socketIn = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socketIn));
        StringBuffer response = new StringBuffer();
        String line;
        int i = 0;
        while ((line= reader.readLine())!=null){
            response.append(line);
            System.out.println("line->"+(++i)+" =>"+line);
            if(line.isEmpty()){
                System.out.println("空白行");
                //
            }
        }
        //3.1 获取响应头
        //3.2 获取响应体
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        byte[] buff = new byte[1024];
//        int len = -1;
//        while ((len = socketIn.read(buff)) != -1) {
//            buffer.write(buff, 0, len);
//        }
//        //获取
////        System.out.println(buffer);
//
//        int end = getEntityStartIndex(buffer);
//        System.out.println("end = " + end);
//        byte[] bytes = Arrays.copyOfRange(buffer.toByteArray(), end, buffer.size());
//        System.out.println(GZIPUtils.uncompressToString(bytes, "GBK"));
        socket.close();
    }

    private int getEntityStartIndex(ByteArrayOutputStream buffer) {
        byte[] crlf_arr = CRLF.getBytes();
        byte[] bytes = buffer.toByteArray();
        for (int i = 0; i < bytes.length - (crlf_arr.length * 2); i++) {
            if (bytes[i] != crlf_arr[0]) {
                continue;
            }
            if (ArraysAsset.equals(crlf_arr, 0, bytes, i, crlf_arr.length)) {
                if (ArraysAsset.equals(crlf_arr, 0, bytes, i + crlf_arr.length, crlf_arr.length)) {
                    return i + (crlf_arr.length * 2);
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        try {
            client.createSocket();
            client.communicate();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }
}
