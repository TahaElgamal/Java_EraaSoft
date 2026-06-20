package main.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the central e-wallet system that holds all accounts.
 * This class acts as an in-memory data store for the application.
 * It contains a fixed system name and a mutable list of {@link Account} objects.
 * <p>
 * Note: The accounts list is exposed via a getter, allowing external classes
 * to directly modify the list (add/remove accounts). This is a design choice
 * for simplicity in this demo, but in a real application, encapsulation
 * would be stronger (e.g., providing add/remove methods instead of direct access).
 */
public class EWalletSystem {

    /** The name of the e-wallet system, declared as final and cannot be changed. */
    private final String name = "ErraaSoft EWalletSystem";

    /**
     * The list of all accounts registered in the system.
     * Initialized as an empty {@link ArrayList} when the system is created.
     */
    private List<Account> accounts = new ArrayList();

    /**
     * Returns the name of the e-wallet system.
     *
     * @return the system name (always "ErraaSoft EWalletSystem")
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of accounts.
     * <p>
     * <strong>Caution:</strong> This returns the direct reference to the internal list,
     * allowing external code to modify it (e.g., add or remove accounts).
     * This is intentional for this simple implementation but could be a
     * potential encapsulation issue.
     *
     * @return the list of {@link Account} objects
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Replaces the current list of accounts with a new one.
     * This allows resetting the entire account list.
     *
     * @param accounts the new list of accounts to set
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}