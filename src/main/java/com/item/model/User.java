package com.item.model;

public class User {

	private long id ;
	private String userName ;
	private String password ;
	private String email;
	private int age ;
	private String phoneNumber ;
	
	public User() {
		
	}
	
	public User(String userName, String password) {
		this.userName=userName;
		this.password=password;
		
	}
	
	public User(String userName,String email, String password, int age,String phoneNumber) {
		this.userName=userName;
		this.email=email;
		this.password=password;
		this.age=age;
		this.phoneNumber=phoneNumber;
		
	}
	
	public User(long id ,String userName, String email,String password, int age,String phoneNumber) {
		this.id=id;
		this.userName=userName;
		this.email=email;
		this.password=password;
		this.age=age;
		this.phoneNumber=phoneNumber;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
