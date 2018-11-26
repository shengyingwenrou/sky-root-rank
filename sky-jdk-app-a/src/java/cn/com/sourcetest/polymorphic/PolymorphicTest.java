package cn.com.sourcetest.polymorphic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc: cn.com.sourcetest.polymorphic
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/17
 * Time: 10:17
 */
public class PolymorphicTest {

    private Logger logger = LoggerFactory.getLogger(PolymorphicTest.class);

    public static void main(String[] args) {

        Animal animal = new Cat();
        animal.eat();
        Cat c = (Cat) animal;
        c.eat();

        animal.eatTest();

        if (animal instanceof Cat) {
            Cat a = (Cat) animal;
            a.work();
        } else if (animal instanceof Dog) {
            Dog d = (Dog) animal;
            d.work();
        }

    }

}


abstract class Animal {

    abstract void eat();

    public void eatTest() {
      System.out.println("Animal eatTest");
    }
}

class Cat extends Animal {
    @Override
    void eat() {
        System.out.println("cat eat mouse");
    }

    void work() {System.out.println("cat catch mouse");}
}

class Dog extends Animal {
    @Override
    void eat() {
        System.out.println("dog eat bone");
    }

    void work() {System.out.println("dog catch bone");}
}
