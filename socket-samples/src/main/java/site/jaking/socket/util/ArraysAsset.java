package site.jaking.socket.util;

/**
 * Create by chenjiacheng on 2021/12/27
 */
public class ArraysAsset {


    /***
     * 计算两个byte数组是否相等
     * @param b1 数组1
     * @param begin1 数组1的开始索引
     * @param b2 数组2
     * @param begin2 数组2的开始索引
     * @param len 要比较的长度
     * @return
     */
    public static boolean equals(byte[] b1, int begin1, byte[] b2, int begin2, int len) {
        if (b1 == b2) {
            return true;
        }
        if (b1 == null || b2 == null) {
            return false;
        }
        //翻转相等
        for (int i = 0; i < len; i++) {
            if (b1[begin1 + i] != b2[begin2 + i]) {
                return false;
            }
        }
        return true;
    }

    private static final String crlf = System.lineSeparator();

    public static void main(String[] args) {
        byte[] b1 = {'\n'};
        byte[] bytes = crlf.getBytes();
        boolean equals = ArraysAsset.equals(bytes, 0, b1, 0, 1);
        System.out.println("equals = " + equals);
    }

}
