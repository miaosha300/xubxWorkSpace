package xubx.binary;

/**
 * 二进制 binary
 * Created by asus on 2019/11/20.
 */
public class Binary {

    public static void main(String[] args) {
        // \u000d System.out.println("Hello World...");
    }

    /**
     * ip:55.254.253.252地址放到int对象中, string -> int
     * @param ip
     */
    public static void ipToInt(String ip){
        int num = 0;
        String[] strings = ip.split("\\.");
        for (int i = 0; i < strings.length; i++) {
            //每次位移8 * n 位
            int temp = Integer.parseInt(strings[i]) << 8 * (strings.length -1 -i);
            System.out.println(Integer.toBinaryString(temp));
            //num 或运算
            num = num | temp;
        }
        System.out.println(Integer.toBinaryString(num));
        System.out.println(num);
    }

    /**
     * 将 int 转换为 ip 字符串
     *
     * @param ipInt 用 int 表示的 ip 值
     * @return ip字符串，如 127.0.0.1
     */
    public static String int2Ip(int ipInt) {
        String[] ipString = new String[4];
        for (int i = 0; i < 4; i++) {
            // 每 8 位为一段，这里取当前要处理的最高位的位置
            int pos = i * 8;
            // 取当前处理的 ip 段的值
            int and = ipInt & (255 << pos);
            // 将当前 ip 段转换为 0 ~ 255 的数字，注意这里必须使用无符号右移
            ipString[3-i] = String.valueOf(and >>> pos);
        }
        return String.join(".", ipString);
    }
}
