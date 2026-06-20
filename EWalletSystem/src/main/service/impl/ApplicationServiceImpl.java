package main.service.impl;

import main.model.Account;
import main.service.ApplicationService;

import java.util.Objects;
import java.util.Scanner;

/**
 * Implementation of the {@link ApplicationService} interface.
 * This class handles the console-based user interface for the banking application,
 * including authentication, account management, and transaction operations.
 * It delegates business logic to service classes like {@link AccountServiceImpl}
 * and {@link ValidationServiceImpl}.
 */
public class ApplicationServiceImpl implements ApplicationService {

    // Service for account-related operations (create, read, update, delete)
    private final AccountServiceImpl accountService = new AccountServiceImpl();

    // Service for input validation (username, password, age, phone number)
    private final ValidationServiceImpl validationService = new ValidationServiceImpl();

    /**
     * Starts the main application loop, presenting the user with login, signup, or exit options.
     * Handles invalid input attempts and limits repeated failures to prevent abuse.
     */
    @Override
    public void startApplication() {
        System.out.println("**************  Hello Sir :)  *************");

        int numOfTrying = 0;        // Counts invalid menu choices
        int numOfScanner = 0;        // Counts scanner input errors (e.g., non-integer input)

        while (true) {
            // Display main menu
            System.out.println("1.Login          2.SignUp         3.Exit");
            System.out.println("Enter your choice : ");

            Scanner input = new Scanner(System.in);
            int choice = 0;
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid choice : ");
                numOfScanner++;
                continue;   // Skip the rest of the loop and prompt again
            } finally {
                // If too many scanner errors occur, terminate the application
                if (numOfScanner == 4) {
                    System.out.println("Sorry, pls contact with admin......");
                    break;
                }
            }

            boolean isExit = false;   // Flag to break out of the main loop

            switch (choice) {
                case 1:
                    logIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    System.out.println("...... have a nice day :) ......");
                    isExit = true;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    numOfTrying++;    // Count invalid choices
            }

            if (isExit) break;

            // If user makes 4 invalid choices, terminate the application
            if (numOfTrying == 4) {
                System.out.println("many times invalid choice pls contact with the admin :( ......");
                break;
            }
        }
    }

    /**
     * Handles the login process.
     * Prompts the user for username and password, retrieves the corresponding account,
     * and if successful, navigates to the profile menu.
     */
    private void logIn() {
        Scanner input = new Scanner(System.in);

        System.out.println("------------> LogIn <----------");

        System.out.println("Enter your username : ");
        String username = null;
        try {
            username = input.nextLine();
        } catch (Exception e) {
            System.out.println("----------->Please enter a valid username : ");
        }

        System.out.println("Enter your password : ");
        String password = null;
        try {
            password = input.nextLine();
        } catch (Exception e) {
            System.out.println("----------->Please enter a valid password : ");
        }

        Account account = new Account(username, password);
        Account accountLogin = accountService.getAccountByUserNamePassword(account);

        if (Objects.nonNull(accountLogin)) {
            System.out.println("You have successfully logged in :) .....");
            showProfile(accountLogin);   // Enter user-specific menu
        } else {
            System.out.println("Invalid Username or Password :( ....");
        }
    }

    /**
     * Handles the signup process.
     * Collects username, password, age, and phone number from the user,
     * validates each field using {@link ValidationServiceImpl}, and creates a new account.
     * If creation is successful, shows the profile; otherwise, notifies of duplicate data.
     */
    private void signUp() {
        Scanner input = new Scanner(System.in);

        System.out.println("------------> SignUp <----------");

        // Username input with validation
        String username = null;
        while (true) {
            try {
                System.out.println("Enter your username : ");
                username = input.nextLine();

                if (validationService.validateUserName(username)) {
                    System.out.println("valid");
                    break;
                } else {
                    System.out.println("invalid");
                }
            } catch (Exception e) {
                System.out.println("-------------> Invalid input, please try again.");
            }
        }

        // Password input with validation
        String password = null;
        while (true) {
            try {
                System.out.println("Enter your password : ");
                password = input.nextLine();

                if (validationService.validatePassword(password)) {
                    System.out.println("valid");
                    break;
                } else {
                    System.out.println("invalid");
                }
            } catch (Exception e) {
                System.out.println("-------------> Invalid input, please try again.");
            }
        }

        // Age input with validation (expects a float)
        float age = 0;
        while (true) {
            try {
                System.out.println("Enter your age : ");
                age = input.nextFloat();

                if (validationService.validateAge(age)) {
                    System.out.println("valid");
                    break;
                } else {
                    System.out.println("invalid");
                }
            } catch (NumberFormatException e) {
                System.out.println("Age must be a number!");
            } catch (Exception e) {
                System.out.println("-------------> Invalid input, please try again.");
            }
        }

        // Phone number input with validation
        String phoneNumber = null;
        while (true) {
            try {
                System.out.println("Enter your phone number : ");
                phoneNumber = input.next();

                if (validationService.validatePhoneNumber(phoneNumber)) {
                    System.out.println("valid");
                    break;
                } else {
                    System.out.println("invalid");
                }
            } catch (Exception e) {
                System.out.println("-------------> Invalid input, please try again.");
            }
        }

        Account account = new Account(username, password, age, phoneNumber);
        Account accountCreated = accountService.createAccount(account);

        if (Objects.nonNull(accountCreated)) {
            System.out.println("Account Created Successfully :) ....");
            showProfile(accountCreated);
        } else {
            System.out.println("user name or phone-number already exist pls try again :( ....");
        }
    }

    /**
     * Displays the profile menu for a logged-in user.
     * Allows deposit, withdraw, transfer, view details, change password, and logout.
     * Handles invalid choices and input errors with attempt limits.
     *
     * @param account the currently logged-in account
     */
    private void showProfile(Account account) {
        Scanner input = new Scanner(System.in);
        System.out.println("------------> Profile <----------");
        showAccountDetails(account);

        int numOfTrying = 0;    // Counts invalid menu choices
        int numOfScanner = 0;    // Counts scanner input errors

        while (true) {
            System.out.println("1.deposit       2.withdraw       3.transfer      4.show account details       5.change password      6.logout");
            System.out.println("enter your choice : ");

            int choice = 0;
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid choice : ");
                numOfScanner++;
                continue;
            } finally {
                // If too many scanner errors, terminate the profile session
                if (numOfScanner == 3) {
                    System.out.println("Sorry, pls contact with admin......");
                    break;
                }
            }

            boolean isExit = false;
            boolean invalidChoice = false;

            switch (choice) {
                case 1:
                    deposit(account);
                    break;
                case 2:
                    withdraw(account);
                    break;
                case 3:
                    transfer(account);
                    break;
                case 4:
                    showAccountDetails(account);
                    break;
                case 5:
                    changePassword(account);
                    break;
                case 6:
                    System.out.println("logout success have a nice day :)....");
                    isExit = true;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    numOfTrying++;
                    invalidChoice = true;
            }

            if (isExit) break;

            // If too many invalid choices, terminate the profile session
            if (numOfTrying == 4) {
                System.out.println("many times invalid choice pls contact with the admin :( ......");
                break;
            }

            // If choice was invalid, skip asking for another service and loop again
            if (invalidChoice) {
                continue;
            }

            // Ask if the user wants to perform another operation
            System.out.println("Are you need another service .......:)");
            System.out.println("1.Yes       2.No");
            int serviceChoice = input.nextInt();
            if (serviceChoice == 2) {
                break;
            }
        }
    }

    /**
     * Performs a deposit operation for the given account.
     * Displays current account details, prompts for an amount, and updates the balance.
     *
     * @param account the account to deposit into
     */
    private void deposit(Account account) {
        System.out.println("------------> Your Profile <----------\n");
        showAccountDetails(account);

        System.out.println("------------> Deposit <----------");
        Scanner input = new Scanner(System.in);
        double amount = 0;
        while (true) {
            try {
                System.out.println("Enter an amount to be deposited : ");
                amount = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("-------------> Invalid input, please try again.");
                input.nextLine();   // Clear the invalid input
            }
        }

        Account accountDepositSuccess = accountService.deposit(account, amount);
        if (Objects.nonNull(accountDepositSuccess)) {
            System.out.println("Deposit Successfully :) ....");
            System.out.println("your current balance is : " + accountDepositSuccess.getBalance());
        } else {
            System.out.println("deposit failed");
        }
    }

    /**
     * Performs a withdrawal operation for the given account.
     * Displays current account details, prompts for an amount, and deducts if sufficient balance.
     *
     * @param account the account to withdraw from
     */
    private void withdraw(Account account) {
        System.out.println("------------> Your Profile <----------\n");
        showAccountDetails(account);

        System.out.println("------------> Withdrawal <----------");
        Scanner input = new Scanner(System.in);
        double amount;
        while (true) {
            try {
                System.out.println("Enter an amount to be withdrawn : ");
                amount = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("-------------> Invalid input, please try again.");
                input.nextLine();
            }
        }

        Account accountWithdrawSuccess = accountService.withdraw(account, amount);
        if (Objects.nonNull(accountWithdrawSuccess)) {
            System.out.println("Withdraw Successfully :) ....");
            System.out.println("your current balance is : " + accountWithdrawSuccess.getBalance());
        } else {
            System.out.println("Withdraw failed");
        }
    }

    /**
     * Displays the details of the given account (username, password, age, phone, balance).
     *
     * @param account the account whose details are to be shown
     */
    private void showAccountDetails(Account account) {
        System.out.println("*Your Details <----------");
        Account accountDetails = accountService.showAccountDetails(account);
        System.out.println("User Name: " + accountDetails.getUserName());
        System.out.println("Password: " + accountDetails.getPassword());
        System.out.println("Age: " + accountDetails.getAge());
        System.out.println("Phone Number: " + accountDetails.getPhoneNumber());
        System.out.println("Balance: " + accountDetails.getBalance());
    }

    /**
     * Allows the user to change their password.
     * Verifies the current password, then prompts for a new valid password.
     * Limits incorrect current password attempts to 4.
     *
     * @param account the account whose password is to be changed
     */
    private void changePassword(Account account) {
        Scanner input = new Scanner(System.in);
        System.out.println("------------> Change Password <----------");

        int validPasswordCounter = 0;
        while (true) {
            System.out.println("pls enter your current password : ");
            String oldPassword = null;
            try {
                oldPassword = input.nextLine();
            } catch (Exception e) {
                System.out.println("-------------> Invalid input, please try again.");
                input.nextLine();
                continue;
            }

            boolean validOldPassword = accountService.validateAccountPassword(account, oldPassword);
            if (validOldPassword) {
                System.out.println("valid password :) ");

                String newPassword = null;
                while (true) {
                    try {
                        System.out.println("enter your new password : ");
                        newPassword = input.nextLine();
                    } catch (Exception e) {
                        System.out.println("-------------> Invalid input, please try again.");
                        input.nextLine();
                        continue;
                    }

                    // Validate new password format
                    if (validationService.validatePassword(newPassword)) {
                        break;
                    } else {
                        System.out.println("invalid");
                    }
                }

                boolean validPassword = accountService.changeAccountPassword(account, newPassword);
                if (validPassword) {
                    System.out.println("your password is successfully changed :)......");
                } else {
                    System.out.println("try again.........");
                    continue;
                }

                break;  // Exit the outer loop on success
            } else {
                System.out.println("invalid password :) ");
                validPasswordCounter++;
            }

            // Too many incorrect current password attempts
            if (validPasswordCounter == 4) {
                System.out.println("Now more attempts pls contact with the admin :( ......");
                break;
            }
        }
    }

    /**
     * Transfers money from the logged-in account to another account identified by phone number.
     * Validates the target phone number exists, then prompts for amount and performs transfer.
     * Limits invalid phone number attempts to 3.
     *
     * @param account the source account
     */
    private void transfer(Account account) {
        Scanner input = new Scanner(System.in);
        System.out.println("------------> Transfer <----------");
        int counterTransfer = 0;
        while (true) {
            String phoneNumber = null;
            try {
                System.out.println("Enter an phone-number of account to transfer : ");
                phoneNumber = input.nextLine();
            } catch (Exception e) {
                System.out.println("-------------> Invalid input, please try again.");
                input.nextLine();
                continue;
            }

            Account accountPhoneNumberExist = accountService.validateAccountPhoneNumber(account, phoneNumber);
            if (accountPhoneNumberExist == null) {
                System.out.println("phone number does not exist :(");
                counterTransfer++;

                if (counterTransfer == 3) {
                    System.out.println("Too many failed attempts pls contact with the admin :( ......");
                    break;
                }
            } else {
                System.out.println("phone number is exist :)...");
                System.out.println("phone number : " + accountPhoneNumberExist.getUserName());

                double amount = 0;
                while (true) {
                    try {
                        System.out.println("Enter amount to send :");
                        amount = input.nextDouble();
                        break;
                    } catch (Exception e) {
                        System.out.println("-------------> Invalid input, please try again.");
                        input.nextLine();
                    }
                }

                boolean transferDone = accountService.transferMoney(account, accountPhoneNumberExist, amount);
                if (transferDone) {
                    System.out.println("Transfer successfully :) ....");
                } else {
                    System.out.println("Transfer failed :( insufficient balance");
                    continue;   // Allow user to try again with same phone number?
                }
                break;  // Exit after successful transfer
            }
        }
    }
}