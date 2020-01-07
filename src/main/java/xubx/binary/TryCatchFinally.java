package xubx.binary;

import java.util.HashMap;
import java.util.Map;

/**
 * try catch finally 执行顺序
 */
public class TryCatchFinally {

    public static void main(String[] args) {
        System.out.println("binary 1 ----------------------------------------");
        System.out.println(test1());

        System.out.println("binary 2 ----------------------------------------");
        System.out.println(test2());

        System.out.println("binary 3 ----------------------------------------");
        System.out.println(test3());

        System.out.println("binary 4 ----------------------------------------");
        System.out.println(test4());
    }


    private static int test1() {
        int i = 1;
        try {
            System.out.println("try...");
            return i += 10;
        } catch (Exception e) {
            System.out.println("catch...");
        } finally {
            i++;
            System.out.println("finally...");
            System.out.println("i=" + i);
        }
        return i;
    }

    private static int test2() {
        int i = 1;
        try {
            System.out.println("try...");
            return i += 10;
        } catch (Exception e) {
            System.out.println("catch...");
        } finally {
            i++;
            System.out.println("finally...");
            System.out.println("i=" + i);
            return i;
        }
    }

    private static Map<String, String> test3() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("KEY", "INIT");
        try {
            System.out.println("try...");
            map.put("KEY", "TRY");
            return map;
        } catch (Exception e) {
            System.out.println("catch...");
            map.put("KEY", "CATCH");
        } finally {
            System.out.println("finally...");
            map.put("KEY", "FINALLY");
            map = null;
        }
        return map;
    }

    private static int test4() {
        int i = 1;
        try {
            System.out.println("try...");
            i = i / 0;
            return i += 10;
        } catch (Exception e) {
            System.out.println("catch...");
            return i;
        } finally {
            i++;
            System.out.println("finally...");
            System.out.println("i=" + i);
        }
    }



}
