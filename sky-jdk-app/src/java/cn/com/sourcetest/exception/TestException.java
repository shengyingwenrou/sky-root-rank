package cn.com.sourcetest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc: cn.com.SourceTest.thread.exception
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/9
 * Time: 10:50
 */
public class TestException {


    /**
     * Throwable:
     * Error(系统错误)、Exception
     *
     * Exception:
     * IOException、ClassNotFoundException、CloneNotSupportedException、
     * RuntimeException
     *
     * IOException:
     * EOFException(到了文件末尾、程序还在读取)、
     * FileNotFondException、
     * MalformedURLException(URL异常)、UnknownHostException(DNS解析异常)
     *
     * RuntimeException(运行时异常):
     * ArithmeticException(运算异常)、ClassCastException(类型转换异常)、
     * IllegalArgumentException(传参异常)、IllegalStateException(重复响应异常)、
     * IndexOutOfBoundsException、NoSuchElementException(元素迭代异常)、
     * NullPointerException
     *
     */

    private static Logger logger = LoggerFactory.getLogger(TestException.class);


    public static void main(String[] args) throws Exception {

    }


    /**
     * 编译异常
     * **/
    public static void Exception(){
        try {
            //throwException();
        }catch (Exception e){
        }
        logger.debug("{} {}","TestException","TestException");
    }


    /**
     * 空指针异常
     */
    public static void nullPointerException() {
        try{
            Integer a = null;
            int b = a;
        } catch (Exception e) {
            e.getStackTrace();
            logger.debug("{} {}", "Exception", e.getMessage());
        }
    }

}
