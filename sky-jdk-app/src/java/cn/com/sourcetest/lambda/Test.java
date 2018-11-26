package cn.com.sourcetest.lambda;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.function.Function;

/**
 * Created by sky.song on 2018/11/13.
 */
public class Test {


    public static void lambeda() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (String a, String b) -> a.compareTo(b));
        System.out.println(JSON.toJSONString(names));
    }

    public static void filter(List<String> stringCollection) {
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
    }

    public static void andThen() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        backToString.apply("123");
    }

    public static void maps(List<String> stringCollection){
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        andThen();
    }
}
