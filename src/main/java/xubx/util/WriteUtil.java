package xubx.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 写文件的6种方法
 *
 * 本文我们展示了 6 种写入文件的方法，这 6 种方法总共分为 3 类：
 *
 * 字符流写入、字节流写入和 Files 类写入。
 *
 * 其中操作最便利的是 Files 类，但它的性能不怎么好。
 *
 * 如果对性能有要求就推荐使用带有缓存区的流来完成操作，如 BufferedWriter 或 BufferedOutputStream。
 *
 * 如果写入的内容是字符串的话，那么推荐使用 BufferedWriter，
 *
 * 如果写入的内容是二进制文件的话就推荐使用 BufferedOutputStream
 *
 */
public class WriteUtil {
    public static void main(String[] args) throws IOException {
        // 构建写入内容
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            stringBuilder.append("ABCDEFGHIGKLMNOPQRSEUVWXYZ");
        }
        // 写入内容
        final String content = stringBuilder.toString();
        // 存放文件的目录
        final String filepath1 = "E:/io_test/write1.txt";
        final String filepath2 = "E:/io_test/write2.txt";
        final String filepath3 = "E:/io_test/write3.txt";
        final String filepath4 = "E:/io_test/write4.txt";
        final String filepath5 = "E:/io_test/write5.txt";
        final String filepath6 = "E:/io_test/write6.txt";

        // 方法一:使用 FileWriter 写文件
        long stime1 = System.currentTimeMillis();
        fileWriterTest(filepath1, content);
        long etime1 = System.currentTimeMillis();
        System.out.println("FileWriter 写入用时:" + (etime1 - stime1));

        // 方法二:使用 BufferedWriter 写文件
        long stime2 = System.currentTimeMillis();
        bufferedWriterTest(filepath2, content);
        long etime2 = System.currentTimeMillis();
        System.out.println("BufferedWriter 写入用时:" + (etime2 - stime2));

        // 方法三:使用 PrintWriter 写文件
        long stime3 = System.currentTimeMillis();
        printWriterTest(filepath3, content);
        long etime3 = System.currentTimeMillis();
        System.out.println("PrintWriterTest 写入用时:" + (etime3 - stime3));

        // 方法四:使用 FileOutputStream  写文件
        long stime4 = System.currentTimeMillis();
        fileOutputStreamTest(filepath4, content);
        long etime4 = System.currentTimeMillis();
        System.out.println("FileOutputStream 写入用时:" + (etime4 - stime4));

        // 方法五:使用 BufferedOutputStream 写文件
        long stime5 = System.currentTimeMillis();
        bufferedOutputStreamTest(filepath5, content);
        long etime5 = System.currentTimeMillis();
        System.out.println("BufferedOutputStream 写入用时:" + (etime5 - stime5));

        // 方法六:使用 Files 写文件
        long stime6 = System.currentTimeMillis();
        filesTest(filepath6, content);
        long etime6 = System.currentTimeMillis();
        System.out.println("Files 写入用时:" + (etime6 - stime6));

    }

    /**
     * 方法六:使用 Files 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void filesTest(String filepath, String content) throws IOException {
        Files.write(Paths.get(filepath), content.getBytes());
    }

    /**
     * 方法五:使用 BufferedOutputStream 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void bufferedOutputStreamTest(String filepath, String content) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(filepath))) {
            bufferedOutputStream.write(content.getBytes());
        }
    }

    /**
     * 方法四:使用 FileOutputStream  写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void fileOutputStreamTest(String filepath, String content) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filepath)) {
            byte[] bytes = content.getBytes();
            fileOutputStream.write(bytes);
        }
    }

    /**
     * 方法三:使用 PrintWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void printWriterTest(String filepath, String content) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filepath))) {
            printWriter.print(content);
        }
    }

    /**
     * 方法二:使用 BufferedWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void bufferedWriterTest(String filepath, String content) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
            bufferedWriter.write(content);
        }
    }

    /**
     * 方法一:使用 FileWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void fileWriterTest(String filepath, String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.append(content);
        }
    }
}
