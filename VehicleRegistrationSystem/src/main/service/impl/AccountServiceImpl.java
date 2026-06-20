package main.service.impl;

import main.model.Account;
import main.service.AccountService;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the AccountService interface.
 * Provides CRUD operations and account management functionality using an in-memory list.
 * Handles account creation, authentication, password management, and validation.
 *
 * @author Your Name
 * @version 1.0
 */
public class AccountServiceImpl implements AccountService {

    /* In-memory storage for all account objects */
    private final List<Account> accounts = new ArrayList<>();

    /**
     * Creates a new account in the system.
     * Validates that the username and phone number are unique before creation.
     *
     * @param account the account object to be created
     * @return the created Account object if successful, or null if a duplicate exists
     */
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

    /**
     * Authenticates a user by username and password.
     * Used for login functionality to verify user credentials.
     *
     * @param account the account containing username and password to validate
     * @return the authenticated Account object if credentials match, or null if not found
     */
    @Override
    public Account getAccountByUserNamePassword(Account account) {
        return accounts.stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName())
                                && acc.getPassword().equals(account.getPassword()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves detailed information for a specific account.
     * Finds an account by its username.
     *
     * @param account the account containing the username to search for
     * @return the Account object if found, or null if the username doesn't exist
     */
    @Override
    public Account showAccountDetails(Account account) {
        return accounts.stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Validates a user's password without retrieving the full account.
     * Checks if the provided password matches the stored password for the given username.
     *
     * @param account  the account containing the username to validate
     * @param password the password to check against the stored password
     * @return true if the password matches, false otherwise
     */
    @Override
    public boolean validateAccountPassword(Account account, String password) {
        return accounts.stream()
                .anyMatch(acc ->
                        acc.getUserName().equals(account.getUserName())
                                && acc.getPassword().equals(password));
    }

    /**
     * Changes the password for an existing account.
     * Updates the password if the account exists in the system.
     *
     * @param account  the account containing the username to update
     * @param password the new password to set
     * @return true if the password was successfully changed, false if the account doesn't exist
     */
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

    /**
     * Validates a user's phone number for account verification.
     * Checks if the provided phone number matches the stored phone number for the given username.
     *
     * @param account     the account containing the username to validate
     * @param phoneNumber the phone number to check against the stored phone number
     * @return the Account object if the phone number matches, or null if not found
     */
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