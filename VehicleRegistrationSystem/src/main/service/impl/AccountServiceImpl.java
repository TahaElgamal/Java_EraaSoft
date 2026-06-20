package main.service.impl;

import main.model.Account;
import main.service.AccountService;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final List<Account> accounts = new ArrayList<>();
    @Override
    public Account createAccount(Account account) {
        boolean exists = accounts.stream()
                .anyMatch(acc ->
                        acc.getUserName().equals(account.getUserName())
                                || acc.getPhoneNumber().equals(account.getPhoneNumber()));

        if (exists) {
            return null;
        }

        accounts.add(account);
        return account;
    }

    @Override
    public Account getAccountByUserNamePassword(Account account) {
        return accounts.stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName())
                                && acc.getPassword().equals(account.getPassword()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Account showAccountDetails(Account account) {
        return accounts.stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean validateAccountPassword(Account account, String password) {
        return accounts.stream()
                .anyMatch(acc ->
                        acc.getUserName().equals(account.getUserName())
                                && acc.getPassword().equals(password));
    }

    @Override
    public boolean changeAccountPassword(Account account, String password) {
        boolean exists = accounts.stream()
                .anyMatch(acc ->
                        acc.getUserName().equals(account.getUserName()));

        if (!exists) {
            return false;
        }

        accounts.stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName()))
                .forEach(acc -> acc.setPassword(password));

        return true;
    }

    @Override
    public Account validateAccountPhoneNumber(Account account, String phoneNumber) {
        return accounts.stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName())
                                && acc.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }
}
