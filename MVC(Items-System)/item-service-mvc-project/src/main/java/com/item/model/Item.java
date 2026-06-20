package com.item.model;

public class Item {
	private long id ;
	private String name;
	private double price;
	private int totalNumber;
	
	public Item() {
		
	}
	
	public Item(String name, double price, int totalNumber) {
		this.name = name;
		this.price = price;
		this.totalNumber = totalNumber;
	}
	
	public Item(long id, String name, double price, int totalNumber) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.totalNumber = totalNumber;
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
	
	
	

}
