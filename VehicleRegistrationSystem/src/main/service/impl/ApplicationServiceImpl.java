package main.service.impl;

import main.exception.InvalidInputException;
import main.exception.VehicleNotFoundException;
import main.model.*;
import main.service.ApplicationService;
import main.service.RegistrationService;
import main.util.InputValidator;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {
        // Service for account-related operations (create, read, update, delete)
        private final RegistrationService service = new RegistrationService();
        private final Scanner input = new Scanner(System.in);
        private final AccountServiceImpl accountService = new AccountServiceImpl();

        // Service for input validation (username, password, age, phone number)
        private final ValidationServiceImpl validationService = new ValidationServiceImpl();

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

    private void showProfile(Account account) {
        Scanner input = new Scanner(System.in);
        System.out.println("------------> Profile <----------");
        showAccountDetails(account);

        int numOfTrying = 0;    // Counts invalid menu choices
        int numOfScanner = 0;    // Counts scanner input errors

        while (true) {
            System.out.println("\n========================================");
            System.out.println("VEHICLE REGISTRATION SYSTEM v1.0");
            System.out.println("========================================");
            System.out.println("1. Register New Vehicle");
            System.out.println("2. Search Vehicle by Plate");
            System.out.println("3. Update Owner Name");
            System.out.println("4. Delete Vehicle");
            System.out.println("5. List All Vehicles");
            System.out.println("6. Filter by Vehicle Type");
            System.out.println("7. Show Owner History");
            System.out.println("8. Show Expired Registrations");
            System.out.println("9. Statistics Report");
            System.out.println("0. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

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
                    registerNewVehicle();
                    break;
                case 2:
                    searchVehicleByPlate();
                    break;
                case 3:
                    updateOwnerName();
                    break;
                case 4:
                    deleteVehicle();
                    break;
                case 5:
                    listAllVehicles();
                    break;
                case 6:
                    filterByVehicleType();
                    break;
                case 7:
                    showOwnerHistory();
                    break;
                case 8:
                    showExpiredRegistrations();
                    break;
                case 9:
                    statisticsReport();
                    break;
                case 0:
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



    private void registerNewVehicle() {

        String plate;
        while (true) {
            System.out.print("Enter plate number: ");
            plate = input.nextLine();

            try {
                InputValidator.validatePlateNumber(plate);
                break; // valid input
            } catch (InvalidInputException e) {
                System.out.println("Invalid input, please try again.");
            }
        }


        String owner;
        while (true){
        System.out.print("Enter owner name: ");
        owner = input.nextLine();
        try{
            InputValidator.validateOwnerName(owner);
            break;
        }catch (InvalidInputException e){
            System.out.println("Invalid input, please try again.");
        }}

        String type;
        while (true){
        System.out.print("Enter vehicle type (Car/Truck/Motorcycle): ");
        type = input.nextLine();
        try {
            InputValidator.validateVehicleType(type);
            break;
        }catch (InvalidInputException e){
            System.out.println("Invalid input, please try again.");
        }}

        int year;
        while (true){
        System.out.print("Enter registration year: ");
        year = input.nextInt();
        input.nextLine();
        try {
            InputValidator.validateRegistrationYear(year);
            break;
        }catch (InvalidInputException e){
            System.out.println("Invalid input, please try again.");
        }}
        Vehicle vehicle = null;

        if (type.equalsIgnoreCase("Car")) {

            int doors;
            while (true) {
                System.out.print("Enter number of doors: ");
                doors = input.nextInt();
                input.nextLine();
                try{
                    InputValidator.validateDoors(doors);
                    break;
                } catch (InvalidInputException e){
                    System.out.println("Invalid input, please try again.");
                }
            }
            vehicle = new Car(plate, owner, year, "ACTIVE", doors);

        } else if (type.equalsIgnoreCase("Truck")) {

            double cargo;
            while(true){
                System.out.print("Enter cargo capacity tons: ");
                cargo = input.nextDouble();
                input.nextLine();
                try{
                    InputValidator.validateCargoCapacity(cargo);
                    break;
                }catch (InvalidInputException e){
                    System.out.println("Invalid input, please try again.");
                }
            }

            vehicle = new Truck(plate, owner, year, "ACTIVE", cargo);

        } else if (type.equalsIgnoreCase("Motorcycle")) {

            String engine;
            while (true){
                System.out.print("Enter engine type: ");
                engine = input.nextLine();

                try {
                    InputValidator.validateEngineType(engine);
                    break;
                }catch (InvalidInputException e){
                    System.out.println("Invalid input, please try again.");
                }
            }

            vehicle = new Motorcycle(plate, owner, year, "ACTIVE", engine);
        }

        if (vehicle != null) {
            service.registerVehicle(vehicle);
            System.out.println("Vehicle registered successfully.");
        }
    }

    private void searchVehicleByPlate() {

        String plate;

        while (true) {
            System.out.print("Enter plate number: ");
            plate = input.nextLine();

            try {
                InputValidator.validatePlateNumber(plate);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        Vehicle vehicle = service.findByPlate(plate);
        System.out.println(vehicle);

    }

    private void updateOwnerName() {

        String plate;
        String owner;

        // plate validation
        while (true) {
            System.out.print("Enter plate number: ");
            plate = input.nextLine();

            try {
                InputValidator.validatePlateNumber(plate);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        // owner validation
        while (true) {
            System.out.print("Enter new owner name: ");
            owner = input.nextLine();

            try {
                InputValidator.validateOwnerName(owner);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        service.updateOwner(plate, owner);

        System.out.println("Owner updated successfully.");
    }

    private void deleteVehicle() {

        String plate;

        while (true) {
            System.out.print("Enter plate number: ");
            plate = input.nextLine();

            try {
                InputValidator.validatePlateNumber(plate);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        try{
        Vehicle vehicle  = service.findByPlate(plate);

        if (Objects.nonNull(vehicle)){
            service.deleteVehicle(plate);
            System.out.println("Vehicle deleted successfully.");
        }}catch (VehicleNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    private void listAllVehicles() {

        List<Vehicle> vehicles = service.getAllVehicles();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return;
        }

        vehicles.forEach(System.out::println);
    }

    private void filterByVehicleType() {

        String type;

        while (true) {
            System.out.print("Enter vehicle type: ");
            type = input.nextLine();

            try {
                InputValidator.validateVehicleType(type);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        service.filterByType(type)
                .forEach(System.out::println);
    }

    private void showOwnerHistory() {
        String owner;
        while (true) {
            System.out.print("Enter owner name: ");
            owner = input.nextLine();
            try{
                InputValidator.validateOwnerName(owner);
                break;
            }catch (InvalidInputException e){
                System.out.println("Invalid input, please try again.");
            }
        }
        service.getVehiclesByOwner(owner)
                .forEach(System.out::println);
    }
    private void showExpiredRegistrations() {

        int currentYear = java.time.Year.now().getValue();

        service.getExpiredRegistrations(currentYear)
                .forEach(System.out::println);
    }
    private void statisticsReport() {
        service.printStatistics();
    }

    private void showAccountDetails(Account account) {
        System.out.println("*User Details (worker) <----------");
        Account accountDetails = accountService.showAccountDetails(account);
        System.out.println("User Name: " + accountDetails.getUserName());
        System.out.println("Password: " + accountDetails.getPassword());
        System.out.println("Age: " + accountDetails.getAge());
        System.out.println("Phone Number: " + accountDetails.getPhoneNumber());
    }


}
