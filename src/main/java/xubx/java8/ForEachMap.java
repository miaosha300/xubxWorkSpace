package xubx.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.java8
 * 类名称:     ForEachMap
 * 类描述:     java8，遍历map
 * 创建人:     xubx
 * 创建时间:   2020/1/9 10:49
 */
public class ForEachMap {
    private static Map<String, Object> map = new HashMap<>();
    static {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", 4);
        map.put("key5", 5);
        map.put("key5", 'h');
    }

    public static void main(String[] args) {
        //遍历k、v
//        map.forEach((k,v) -> System.out.println("k：" + k + "  v：" + v));
        //遍历k
        map.keySet().forEach(k -> System.out.println("k：" + k + "  v：" + map.get(k)));
        //遍历v
//        map.values().forEach(System.out::println);
        //遍历k、v，通过entrySet：大容量时推荐使用
//        map.entrySet().forEach(entry -> System.out.println("k：" + entry.getKey() + "  v：" + entry.getValue()));
    }


}
