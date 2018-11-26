package cn.com.demo;

import cn.com.demo.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sky.song on 2018/11/20.
 */
public class App {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        DemoService demoService = (DemoService) ctx.getBean("person");
//        demoService.create();

                System.out.println("容器初始化成功");
                //得到Preson，并使用
                 Person person = ctx.getBean("person",Person.class);
                 System.out.println(person);

                 System.out.println("现在开始关闭容器！");
                 ((ClassPathXmlApplicationContext)ctx).registerShutdownHook();

    }
}
