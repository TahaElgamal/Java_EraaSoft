package core.main;

import core.service.PersonService;
import core.service.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonService personService=applicationContext.getBean("personService", PersonService.class);
        personService.save("taha");
        applicationContext.close();

        System.out.println("#####################################################");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PersonService person = context.getBean(PersonService.class);
        person.save("Taha");
        context.close();

    }
}