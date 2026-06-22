package core.main;

import core.service.SpringConfig;
import core.service.impl.AccountServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountServiceImpl accountService= applicationContext.getBean("accountServiceImpl", AccountServiceImpl.class);
        accountService.getSavePerson("taha");

        System.out.println("###########################################################");

        AnnotationConfigApplicationContext applicationContext1=new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountServiceImpl accountService1=applicationContext1.getBean("accountServiceImpl",AccountServiceImpl.class);
        accountService1.getSavePerson("marko");
    }
}