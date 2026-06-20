package main.model;

/**
 * Represents a user account in the e-wallet system.
 * Contains personal information, authentication credentials, and the current balance.
 * This class is a simple POJO (Plain Old Java Object) with fields, constructors,
 * getters, setters, and a toString method.
 */
public class Account {

    /** The unique username chosen by the user (used for login). */
    private String userName;

    /** The account password (stored in plain text for simplicity in this demo). */
    private String password;

    /** The age of the account holder. */
    private float age;

    /** The phone number of the account holder, used for transfers and uniqueness. */
    private String phoneNumber;

    /** The current balance in the e-wallet. Defaults to 0.0. */
    private double balance;

    /**
     * Default no-argument constructor.
     * Required for frameworks that use reflection (e.g., serialization).
     */
    public Account() {
    }

    /**
     * Constructs an Account with minimal information – typically used for login attempts.
     *
     * @param userName the username
     * @param password the password
     */
    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Constructs a complete Account with personal details.
     * The balance is initialized to 0.0 by default.
     *
     * @param userName    the username
     * @param password    the password
     * @param age         the age of the user
     * @param phoneNumber the phone number of the user
     */
    public Account(String userName, String password, float age, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = 0; // New accounts start with zero balance
    }

    // --- Getters and Setters ---

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username.
     *
     * @param userName the new username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the age.
     *
     * @return the age
     */
    public float getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age the new age
     */
    public void setAge(float age) {
        this.age = age;
    }

    /**
     * Returns the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the current balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance.
     *
     * @param balance the new balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns a string representation of the account, including all fields.
     * Useful for debugging and logging.
     *
     * @return a string containing the account details
     */
    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}