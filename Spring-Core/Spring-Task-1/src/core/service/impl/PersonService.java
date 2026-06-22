package core.service.impl;

import org.springframework.stereotype.Component;

@Component
public class PersonService implements UserService {
    @Override
    public void save(String name) {
        System.out.println("person your name is saved: "+name);
    }

    @Override
    public void update(String name) {
        System.out.println("person your name is updated: "+name);
    }
}
