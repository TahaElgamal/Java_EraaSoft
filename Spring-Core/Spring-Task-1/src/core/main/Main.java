package core.main;

import core.SpringConfig;
import core.service.impl.MangerService;
import core.service.impl.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext =new ClassPathXmlApplicationContext("applicationContext.xml");
        MangerService mangerService =applicationContext.getBean("mangerService", MangerService.class);
        PersonService personService =applicationContext.getBean("personService", PersonService.class);

        mangerService.save("taha");
        mangerService.update("mohsen");

        personService.save("medo");
        personService.update("mazen");
//        ----------------------------------------------------------------------------------------
        System.out.println("############################################################");

        AnnotationConfigApplicationContext configApplicationContext=new AnnotationConfigApplicationContext(SpringConfig.class);
        MangerService mangerService1= configApplicationContext.getBean("mangerService", MangerService.class);
        PersonService personService1=configApplicationContext.getBean("personService", PersonService.class);

        mangerService1.save("taha");
        mangerService1.update("ali");

        personService1.save("mohamed");
        personService1.update("ahmed");
    }
}