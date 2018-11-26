package cn.com.sourcetest.sort;


import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by sky.song on 2018/10/14.
 */
public class SortTest {


    public static void main(String[] args) {

        Integer[] arr = {11,3,5,6,7};
        Arrays.sort(arr);

        System.out.println(JSON.toJSONString(arr));
        Comparator cmp = new MyCompare();

        Arrays.sort(arr,cmp);
        System.out.println(JSON.toJSONString(arr));

    }
}


/** 内部实现快速算法实现的 **/
class MyCompare implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        if(o1>=o2){
            return -1;
        }else{
            return 0;
        }
    }
}
