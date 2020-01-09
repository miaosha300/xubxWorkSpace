package xubx.java8;

import org.assertj.core.util.Lists;
import xubx.entity.Apple;

import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Comparator.comparing;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.java8
 * 类名称:     List
 * 类描述:     List
 * 创建人:     xubx
 * 创建时间:   2020/1/9 15:25
 */
public class TestList {
    private static ArrayList<Apple> inventory ;

    public static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

    static {
        inventory = Lists.newArrayList(
                new Apple(10, "red"),
                new Apple(5, "red"),
                new Apple(1, "green"),
                new Apple(15, "green"),
                new Apple(2, "red"));
    }

    public static void main(String[] args) {
        //顺序排序
        //1、传递代码，函数式编程
//        inventory.sort(new AppleComparator());

        //2、匿名内部类
//        inventory.sort(new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getWeight() - o2.getWeight();
//            }
//        });

        // 3、使用Lambda表达式
//        inventory.sort((a, b) -> a.getWeight() - b.getWeight());

        // 4、使用Comparator的comparing
//        inventory.sort(comparing(Apple::getWeight));


        //逆序排序
        // 1、 根据重量逆序排序
//        inventory.sort(comparing(Apple::getWeight).reversed());

        //2、如果两个苹果的重量一样重，怎么办？那就再找一个条件进行排序呗
        inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        System.out.println(inventory);

    }

}
