package main.service.impl;

import main.model.Account;
import main.model.EWalletSystem;
import main.service.AccountService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link AccountService} interface.
 * This class provides concrete business logic for managing accounts,
 * including creation, retrieval, deposits, withdrawals, password changes,
 * and money transfers. It interacts with the central {@link EWalletSystem}
 * which holds the in-memory list of all accounts.
 */
public class AccountServiceImpl implements AccountService {

    // The central system that holds the list of all accounts.
    private final EWalletSystem eWalletSystem = new EWalletSystem();

    /**
     * Creates a new account after verifying that the username and phone number
     * are unique (not already used by any existing account).
     *
     * @param account the account object containing the new account's data
     * @return the created account if successful, or {@code null} if an account
     *         with the same username or phone number already exists
     */
    @Override
    public Account createAccount(Account account) {
        List<Account> accounts = eWalletSystem.getAccounts();

        // Check if any existing account has the same username OR phone number
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) ||
                        acc.getPhoneNumber().equals(account.getPhoneNumber()))
                .findAny();

        // If a duplicate is found, return null to indicate failure
        if (optionalAccount.isPresent()) {
            return null;
        }

        // Otherwise add the new account to the system and return it
        eWalletSystem.getAccounts().add(account);
        return account;
    }

    /**
     * Retrieves an account by matching both username and password.
     * Used for login authentication.
     *
     * @param account an account object containing the username and password to match
     * @return the matching account if found, otherwise {@code null}
     */
    @Override
    public Account getAccountByUserNamePassword(Account account) {
        List<Account> accounts = eWalletSystem.getAccounts();

        // Find an account where both username and password match exactly
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()))
                .findAny();

        // Return the found account or null if none matches
        return optionalAccount.orElse(null);
    }

    /**
     * Deposits a specified amount into an account.
     * Validates that the account exists and the amount is at least 100.
     *
     * @param account the account to deposit into (used for lookup)
     * @param amount  the amount to deposit
     * @return the updated account if successful, otherwise {@code null}
     */
    @Override
    public Account deposit(Account account, double amount) {
        List<Account> accounts = eWalletSystem.getAccounts();

        // Find the account by matching username and password
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()))
                .findAny();

        // If account not found, fail
        if (optionalAccount.isEmpty()) {
            System.out.println("Account not found");
            return null;
        }

        // Minimum deposit amount check (business rule)
        if (amount < 100) {
            System.out.println("minimum amount to deposit is 100 ...");
            return null;
        }

        // Update the balance
        Account accountDeposit = optionalAccount.get();
        accountDeposit.setBalance(accountDeposit.getBalance() + amount);
        return accountDeposit;
    }

    /**
     * Withdraws a specified amount from an account.
     * Validates account existence, minimum withdrawal amount, and sufficient balance.
     *
     * @param account the account to withdraw from
     * @param amount  the amount to withdraw
     * @return the updated account if successful, otherwise {@code null}
     */
    @Override
    public Account withdraw(Account account, double amount) {
        List<Account> accounts = eWalletSystem.getAccounts();

        // Find the account
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()))
                .findAny();

        if (optionalAccount.isEmpty()) {
            System.out.println("Account not found");
            return null;
        }

        // Minimum withdrawal amount check
        if (amount < 100) {
            System.out.println("minimum amount to withdraw 100 ..");
            return null;
        }

        Account accountWithdraw = optionalAccount.get();

        // Check if balance is sufficient
        if (accountWithdraw.getBalance() < amount) {
            System.out.println("your balance not contain money enough :[ .... ");
            return null;
        }

        // Deduct amount
        accountWithdraw.setBalance(accountWithdraw.getBalance() - amount);
        return accountWithdraw;
    }

    /**
     * Retrieves and returns the full details of an account.
     * Assumes the account exists (since it's called after login).
     *
     * @param account the account whose details are requested
     * @return the account object with all its data
     */
    @Override
    public Account showAccountDetails(Account account) {
        List<Account> accounts = eWalletSystem.getAccounts();

        // Find the account by username and password (should always exist here)
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()))
                .findAny();

        // Directly return the found account (throws NoSuchElementException if not found,
        // but that should not happen in normal flow)
        return optionalAccount.get();
    }

    /**
     * Validates whether the provided password matches the account's current password.
     * Used during password change to verify the old password.
     *
     * @param account  the account whose password is to be checked
     * @param password the password to compare against the stored one
     * @return {@code true} if the password matches, otherwise {@code false}
     */
    @Override
    public boolean validateAccountPassword(Account account, String password) {
        List<Account> accounts = eWalletSystem.getAccounts();

        // Find the account by its current password (note: this is risky if multiple accounts share password)
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getPassword().equals(account.getPassword()))
                .findAny();

        Account accountChange = optionalAccount.get();

        // Compare the provided password with the stored password
        return accountChange.getPassword().equals(password);
    }

    /**
     * Changes the account's password to a new one.
     * Prevents setting the same password as the current one.
     *
     * @param account  the account whose password is to be changed
     * @param password the new password
     * @return {@code true} if the password was successfully changed, {@code false} if the new password
     *         is the same as the current one
     */
    @Override
    public boolean changeAccountPassword(Account account, String password) {
        List<Account> accounts = eWalletSystem.getAccounts();

        // Find the account by its current password
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getPassword().equals(account.getPassword()))
                .findAny();

        Account accountChanged = optionalAccount.get();

        // Check if the new password is the same as the old one
        if (accountChanged.getPassword().equals(password)) {
            System.out.println("Failed, this password is the current password !");
            return false;
        }

        // Update to new password
        accountChanged.setPassword(password);
        return true;
    }

    /**
     * Validates whether a given phone number belongs to another account (different from the current user)
     * and returns that account if found.
     *
     * @param account     the current logged-in account
     * @param phoneNumber the phone number to look up
     * @return the account associated with the phone number if it exists and is not the current user,
     *         otherwise {@code null}
     */
    @Override
    public Account validateAccountPhoneNumber(Account account, String phoneNumber) {
        List<Account> accounts = eWalletSystem.getAccounts();

        // Find an account with the given phone number
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getPhoneNumber().equals(phoneNumber))
                .findAny();

        Account accountValidPhoneNumber = optionalAccount.get();

        // Prevent transferring to yourself
        if (phoneNumber.equals(account.getPhoneNumber())) {
            System.out.println("You cannot transfer to yourself");
            return null;
        }

        // If the phone number does not match (should not happen due to filter, but kept for safety)
        if (!(accountValidPhoneNumber.getPhoneNumber().equals(phoneNumber))) {
            return null;
        }

        return accountValidPhoneNumber;
    }

    /**
     * Transfers a specified amount from one account to another.
     * Validates positive amount, minimum transfer limit (50), and sufficient balance.
     *
     * @param fromAccount the source account
     * @param toAccount   the destination account
     * @param amount      the amount to transfer
     * @return {@code true} if the transfer succeeded, {@code false} otherwise
     */
    @Override
    public boolean transferMoney(Account fromAccount, Account toAccount, double amount) {
        List<Account> accounts = eWalletSystem.getAccounts();

        // Retrieve the actual source account from the system using phone number
        Optional<Account> optionalAccountFrom = accounts.stream()
                .filter(acc -> acc.getPhoneNumber().equals(fromAccount.getPhoneNumber()))
                .findAny();
        Account fromTransfer = optionalAccountFrom.get();

        // Retrieve the actual destination account
        Optional<Account> optionalAccountTo = accounts.stream()
                .filter(acc -> acc.getPhoneNumber().equals(toAccount.getPhoneNumber()))
                .findAny();
        Account toTransfer = optionalAccountTo.get();

        // Amount must be positive
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return false;
        }

        // Minimum transfer amount check (message says "limit for sending is 20" but condition is <=50)
        if (amount <= 50) {
            System.out.println("The limit for sending is 20");
            return false;
        }

        // Check sufficient balance
        if (fromTransfer.getBalance() >= amount) {
            // Deduct from source, add to destination
            fromTransfer.setBalance(fromAccount.getBalance() - amount);
            toTransfer.setBalance(toAccount.getBalance() + amount);
            return true;
        }

        return false;
    }
}