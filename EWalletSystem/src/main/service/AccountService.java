package main.service;

import main.model.Account;

public interface AccountService {

    Account createAccount(Account account);
    Account getAccountByUserNamePassword(Account account);
    Account deposit(Account account, double amount);
    Account withdraw(Account account, double amount);
    Account showAccountDetails(Account account);
    boolean validateAccountPassword(Account account, String password);
    boolean changeAccountPassword(Account account, String password);
    Account validateAccountPhoneNumber(Account account, String phoneNumber);
    boolean transferMoney(Account fromAccount, Account toAccount, double amount);
}
