package xubx.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.countDownLatch
 * 类名称:     CountDownlacthUseTwo
 * 类描述:     CountDownLatch典型用法2
 * 创建人:     xubx
 * 创建时间:   2020/7/3 11:00
 */
public class CountDownlacthUseTwo {
    /**
     * 实现多个线程开始执行任务的最大并行性。
     * 注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。
     * 类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。
     * 做法是初始化一个共享的CountDownLatch(1)，将其计数器初始化为1，
     * 多个线程在开始执行任务前首先 coundownlatch.await()，
     * 当主线程调用 countDown() 时，计数器变为0，多个线程同时被唤醒。
     */


    public static void main(String[] args) throws InterruptedException {
        /**
         *  子线程等待主线程处理完毕开始处理，子线程处理完毕后，主线程输出
         */
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch await = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(new MyRunnable(countDownLatch, await)).start();
        }

        System.out.println("主线程处理自己事情");
        Thread.sleep(3000);
        countDownLatch.countDown();//  主线程调用countDown() 时，计数器变为0，所有线程去做要做的事
        System.out.println("主线程处理结束");
        await.await();// 唤醒所有线程
        System.out.println("子线程处理完毕啦");
    }
}


class MyRunnable implements Runnable {

    private CountDownLatch countDownLatch;

    private CountDownLatch await;

    public MyRunnable(CountDownLatch countDownLatch, CountDownLatch await) {
        this.countDownLatch = countDownLatch;
        this.await = await;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();// 所有线程在此进入等待状态
            System.out.println("子线程" + Thread.currentThread().getName() + "处理自己事情");
            Thread.sleep(1000);
            await.countDown();// 所有线程在此进入等待状态
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}