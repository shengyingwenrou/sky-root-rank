package cn.com.sourcetest.list;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * desc: cn.com.sourcetest.list
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/27
 * Time: 14:00
 */
public class ListTest {

    private static int index = 111;

    static {
        index++;
    }

    /**
     *
     Collection
     ├List
     │├LinkedList
     │├ArrayList
     │└Vector
     │　└Stack
     └Set

     Map
     ├Hashtable
     ├HashMap
     └WeakHashMap
     */


    public static void main(String[] args) {
        // int[] arr1 = {1, 2, 3, 4, 5};
        // System.out.println(FastJsonUtils.toJson(arr1));
        // System.out.println(FastJsonUtils.toJson(arrInserttTest(arr1)));

        List<Integer> intList = Arrays.asList(2, 3, 1);
        Collections.sort(intList);
        Collections.sort(intList, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 - o2 > 0) {
                    return -1;
                }else{
                    return 1;
                }
            }
        });
    }

    public static int[] arrInserttTest(int[] arr) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = new int[arr1.length + 1];
        arr2[4] = 6;
        System.arraycopy(arr1, 0, arr2, 0, 4);
        System.arraycopy(arr1, 4, arr2, 5, 1);
        arr = arr2;
        return arr;
    }


    public static void arrInsertTest1() {
        Integer a = 1;
        Integer b = 2;
        int value = 1;
        int[] arr1 = {1, 2, 3, 4, 5};
        //int[] arr2 = {1,2,3,4,5,6,7} ;   4----6
        int[] arr2 = new int[arr1.length + 1];
        arr2[4] = 6;
        System.arraycopy(arr1, 0, arr2, 0, 4);
        System.arraycopy(arr1, 4, arr2, 5, 1);
    }


    public static int[] insert(int a, int[] nums) {
        //进行插入操作
        int index = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (a >= nums[i]) {
                index++;
            }
        }
        int[] newArray = new int[nums.length + 1];
        newArray[index] = a;
        System.arraycopy(nums, 0, newArray, 0, index);
        System.arraycopy(nums, index, newArray, index + 1, nums.length - index);
        //返回新数组
        return newArray;
    }


    public static void listTest(){
        List<String> list= Lists.newArrayList();
        list.add("1111111111");
        list.add("2222222222");
        list.add("3333333333");
        list.get(2);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Object string = iterator.next();
        }
        LinkedList<Integer> linkList=Lists.newLinkedList();
        linkList.add(1);
        linkList.add(2);
        linkList.add(3);
        HashMap<String,String> map = Maps.newHashMap();
        map.put("1","1");
        map.put("2","1");
        map.get("1");
    }


}
