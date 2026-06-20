package com.item.model;

public class Item {
	private long id ;
	private String name;
	private double price;
	private int totalNumber;
	private long userId;
	
	private boolean hasDetails;
	
	
	public Item() {
		
	}
	
	public Item(String name, double price, int totalNumber) {
		this.name = name;
		this.price = price;
		this.totalNumber = totalNumber;
	}
	
	public Item(String name, double price, int totalNumber ,long userId) {
		this.name = name;
		this.price = price;
		this.totalNumber = totalNumber;
		this.userId=userId;
	}
	
	public Item(long id, String name, double price, int totalNumber,long userId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.totalNumber = totalNumber;
		this.userId=userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isHasDetails() {
		return hasDetails;
	}

	public void setHasDetails(boolean hasDetails) {
		this.hasDetails = hasDetails;
	}

	

}
