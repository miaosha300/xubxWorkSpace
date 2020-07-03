package xubx.jdk;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.jdk
 * 类名称:     UnsafeTest
 * 类描述:     类功能描述
 * 创建人:     xubx
 * 创建时间:   2020/7/3 10:26
 */
public class UnsafeTest {
    static Unsafe unsafe;  //sun.misc.Unsafe
    static long stateOffset;
    private volatile long state = 0;

    static {
        try {
            //因为Unsafe的类加载和项目中的类加载器bootstrap不一样，Unsafe的getUnsafe()判断了类加载器不同会报错，
            // 所以需要使用反射获取unsafe实例
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            //获取state变量在test1中的内存偏移量
            stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        UnsafeTest unsafeTest = new UnsafeTest();
        System.out.println("first:"+stateOffset);
        System.out.println(unsafeTest.state);
        long state = unsafeTest.state;//此时state为0
        //非阻塞算法CAS操作，在系统层面把该操作锁住，禁止中途被其他线程修改。
        // 操作失败代表中途有人截胡，把state的值改了
        //这样就可以知道数据被改动，可以预防产生脏数据
        Boolean sucess = unsafe.compareAndSwapLong(unsafeTest, stateOffset, state,1);
        System.out.println(sucess);

        System.out.println("2rd:"+stateOffset);
        System.out.println(unsafeTest.state);



    }

}
