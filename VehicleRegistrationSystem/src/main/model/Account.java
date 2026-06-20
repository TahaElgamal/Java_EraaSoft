package main.model;

public class Account {
    private String userName;
    private String password;
    private float age;
    private String phoneNumber;

    public Account() {
    }


    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public Account(String userName, String password, float age, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // --- Getters and Setters ---


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public float getAge() {
        return age;
    }


    public void setAge(float age) {
        this.age = age;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +'\'' +
                ", phoneNumber='" + phoneNumber +
                '}';
    }
}
