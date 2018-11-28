package cn.com.admin;

import cn.com.admin.user.entity.Person;
import cn.com.admin.user.service.PersonService;
import cn.com.admin.user.service.TransactionalService;
import cn.com.common.utils.Tools;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sky.song on 2018/11/25.
 */
public class App {

    public static void main(String[] args) {


        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

//        TransactionalService  transactionalService1 = (TransactionalService)ac.getBean("transactionalService");
//        TransactionalService  transactionalService2 = (TransactionalService)ac.getBean("transactionalService");
//        System.out.println(transactionalService1);
//        System.out.println(transactionalService2);

        Person person = (Person)ac.getBean("person");
        Person person1 = (Person)ac.getBean("person");

        System.out.println(person.getPersonService());
        System.out.println(person1.getPersonService());

        System.out.println(person.getPersonService().getToolInstance());
        System.out.println(person1.getPersonService().getToolInstance());

//        System.out.println(personService);
//        System.out.println(personService1);
//
//
//        System.out.println(tools);
//        System.out.println(tools1);

        //transactionalService.add();

    }
}
