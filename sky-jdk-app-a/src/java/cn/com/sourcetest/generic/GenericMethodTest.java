package cn.com.sourcetest.generic;


/**
 * desc: cn.com.sourcetest.generic
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/17
 * Time: 11:31
 */
public class GenericMethodTest {


    public static <E> void printArray(E[] array){

        if (null != array && array.length > 0) {
            for(E element : array){
               System.out.printf(" %s " , element);
            }
        }
    }


    public static void main(String[] args) {

        String[] strArray = {"1","2","3","4","5"};

        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        printArray(strArray);
        System.out.println();
        printArray(intArray);
        System.out.println();
        printArray(doubleArray);
        System.out.println();
        printArray(charArray);

    }

}
