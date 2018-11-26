package cn.com.sourcetest.extend;

/**
 * Created by sky.song on 2018/10/30.
 */



class People{

   public static String name ="sky.song";

   static {

       // name;
       System.out.println(name+"aaaaaaaa");
   }


    public static void main(String[] args) {
        System.out.println(name);
       //  new People("a");
        //System.out.println(name);
    }

    {
        System.out.println("6");
    }

    public People(){
        System.out.println("1");
    }

    public People(String name){
        System.out.println("2");
    }

}


class Child1 extends People {

    {
        System.out.println("5");
    }

    public  Child1(String name) {
        System.out.println("3");
        this.name = name;
        People father;
        father = new People(name + ":F");
    }

    public Child1() {
        System.out.println("4");
    }
}


public class Test {

    public static void main1(String[] args) {
        //System.out.println(new Child1("SKY").name);
        new Child1("a");


        String a = "1";
        String b = "2";

    }

    public static void main(String[] args) {
        String a="1";
        String b="2";

    }
}




