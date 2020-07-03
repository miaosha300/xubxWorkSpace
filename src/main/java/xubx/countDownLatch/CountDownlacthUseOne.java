package xubx.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.countDownLatch
 * 类名称:     CountDownlacthUseOne
 * 类描述:     CountDownlacth典型用法1：
 * 创建人:     xubx
 * 创建时间:   2020/7/3 10:53
 */
public class CountDownlacthUseOne {
    /**
     * 某一个线程开始运行前等待n个线程执行完毕。
     * 将 CountDownLatch 的计数器初始化为n （new CountDownLatch(n)）,
     * 每当一个任务线程执行完毕，就将计数器减1  （countdownlatch.countDown() ），
     * 当计数器的值变为0时，在CountDownLatch上 await() 的线程就会被唤醒。
     * 一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable(){
                @Override
                public void run(){
                    System.out.println(Thread.currentThread().getName()+"  正在运行");
                    try {
                        Thread.sleep(3000);
                    }catch( Exception e){
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            }).start();
        }
        System.out.println("等待子线程运行结束");
        latch.await(); //无限制时间等待
        //latch.await(10, TimeUnit.SECONDS); //有限制时间等待
        System.out.println("子线程运行结束");
    }
}
