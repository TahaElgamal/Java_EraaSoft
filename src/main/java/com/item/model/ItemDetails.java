package com.item.model;

import java.sql.Date;

public class ItemDetails {
	private long id;
    private String description;
    private String color;
    private double weight;
    private Date manufactureDate;
    private Date expiryDate;
    private String countryOfOrigin;
    private long itemId;

    public ItemDetails() {
    }

    public ItemDetails(String description, String color, double weight,Date manufactureDate, Date expiryDate,
                       String countryOfOrigin, long itemId) {

        this.description = description;
        this.color = color;
        this.weight = weight;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.countryOfOrigin = countryOfOrigin;
        this.itemId = itemId;
    }
    
    public ItemDetails(String description, String color, double weight,Date manufactureDate, Date expiryDate,
            String countryOfOrigin) {
		
		this.description = description;
		this.color = color;
		this.weight = weight;
		this.manufactureDate = manufactureDate;
		this.expiryDate = expiryDate;
		this.countryOfOrigin = countryOfOrigin;
		
	}

    public ItemDetails(long id, String description, String color,double weight, Date manufactureDate,Date expiryDate, String countryOfOrigin,
                       long itemId) {

        this.id = id;
        this.description = description;
        this.color = color;
        this.weight = weight;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.countryOfOrigin = countryOfOrigin;
        this.itemId = itemId;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
    
    

}
