package core.service.impl;

import core.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class PersonService implements UserService {
    @Override
    public void save(String name) {
        System.out.println("Person Saved : " + name);

    }
}
