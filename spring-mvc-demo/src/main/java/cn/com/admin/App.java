package cn.com.admin;

import cn.com.admin.user.service.TransactionalService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sky.song on 2018/11/25.
 */
public class App {

    public static void main(String[] args) {


        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        TransactionalService  transactionalService1 = (TransactionalService)ac.getBean("transactionalService");
        TransactionalService  transactionalService2 = (TransactionalService)ac.getBean("transactionalService");
        System.out.println(transactionalService1);
        System.out.println(transactionalService2);
        //transactionalService.add();

    }
}
