package core.service.impl;

import core.service.AccountService;
import core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService {

    private UserService userService;

    @Autowired
    public AccountServiceImpl(@Qualifier("personService") UserService userService) {
        this.userService = userService;
    }

    @Override
    public void getSavePerson(String name) {
        System.out.println("Account Service");

        userService.save(name);
    }


}
