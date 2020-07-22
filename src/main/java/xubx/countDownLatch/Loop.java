package xubx.countDownLatch;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.countDownLatch
 * 类名称:     Loop
 * 类描述:     用来标记下一次符合要求的循环从这个bai标签开始
 * 创建人:     xubx
 * 创建时间:   2020/7/3 11:19
 */
public class Loop {
    public static void main(String[] args) {
        Loop:
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < i; j++)
                if (i % j == 0) {
                continue Loop;
                }
            System.out.print(i + " ");
        }
    }
}
