package main.model;

/**
 * Represents a user account in the system.
 * This class stores user credentials and personal information.
 *
 * @author Your Name
 * @version 1.0
 */
public class Account {
    /* The unique username of the account */
    private String userName;

    /* The password for authentication purposes */
    private String password;

    /* The age of the user (can be a decimal value) */
    private float age;

    /* The contact phone number of the user */
    private String phoneNumber;

    /**
     * Default constructor.
     * Creates an empty Account object.
     */
    public Account() {
    }

    /**
     * Parameterized constructor.
     * Creates an Account with username and password only.
     *
     * @param userName the username for the account
     * @param password the password for the account
     */
    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Fully parameterized constructor.
     * Creates an Account with all fields provided.
     *
     * @param userName    the username for the account
     * @param password    the password for the account
     * @param age         the age of the user
     * @param phoneNumber the phone number of the user
     */
    public Account(String userName, String password, float age, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // --- Getters and Setters ---

    /**
     * Gets the username of the account.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the account.
     *
     * @param userName the new username to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password of the account.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the account.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the age of the user.
     *
     * @return the age as a float value
     */
    public float getAge() {
        return age;
    }

    /**
     * Sets the age of the user.
     *
     * @param age the new age to set (float value)
     */
    public void setAge(float age) {
        this.age = age;
    }

    /**
     * Gets the phone number of the user.
     *
     * @return the phone number as a String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phoneNumber the new phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns a string representation of the Account object.
     * Includes all fields for debugging and logging purposes.
     *
     * @return a formatted string containing all account details
     */
    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age + '\'' +
                ", phoneNumber='" + phoneNumber +
                '}';
    }
}