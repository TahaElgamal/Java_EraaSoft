package main.service;

import main.model.Account;

public interface ValidationService {
    boolean validateUserName(String username);
    boolean validatePassword(String password);
//    boolean validateLoginPassword(Account account, String password);
    boolean validateAge(float age);
    boolean validatePhoneNumber(String phoneNumber);
}
