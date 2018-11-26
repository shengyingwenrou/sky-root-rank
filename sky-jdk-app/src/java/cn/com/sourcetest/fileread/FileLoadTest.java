package cn.com.sourcetest.fileread;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sky.song on 2018/11/23.
 */
public class FileLoadTest {


    public FileLoadTest(){

    }
    static{

    }

    public static void main(String[] args) throws IOException {

//        FileLoadTest test= new FileLoadTest();
//
//        InputStream ins = test.getClass().getResourceAsStream("/setting.properties");
//
//        System.out.println(test.getClass().getResource(""));
//
//        System.out.println("4: "+System.getProperty("user.dir"));
//        System.out.println("5："+test.getClass().getResource("/"));
//        System.out.println("6："+test.getClass().getResource("/setting.properties"));

          FileInputStream inputStream1 = new FileInputStream("/setting.properties");
        // InputStream ins = getClass().getResourceAsStream("/main/setting.properties");
    }





}
