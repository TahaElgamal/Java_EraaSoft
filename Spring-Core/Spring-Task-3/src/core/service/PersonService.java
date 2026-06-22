package core.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("prototype")
public class PersonService implements UserService{
    @Override
    public void save(String name) {
        System.out.println("the name: "+name);
    }

    @PostConstruct
    public void init() {
        System.out.println("Init Method Called");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy Method Called");
    }
}
