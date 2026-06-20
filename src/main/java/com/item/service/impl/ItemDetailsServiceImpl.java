package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import javax.sql.DataSource;

import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;

public class ItemDetailsServiceImpl implements ItemDetailsService{
	
	
	private DataSource dataSource;
	
	public ItemDetailsServiceImpl(DataSource dataSource) {
		if(Objects.isNull(dataSource)) {
			throw new  IllegalArgumentException("DataSource must not be null!");
		}
		this.dataSource= dataSource;
	}

	
	@Override
	public ItemDetails selectItemDetails(Long id) {
		Connection connection =null;
		PreparedStatement statement=null;
		ItemDetails Details=null;
		try {
			connection = dataSource.getConnection();
			
			String query ="select * from item_details where item_id= ?";
			
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				 Details = new ItemDetails(
						resultSet.getLong("id"),
						resultSet.getString("description"),
						resultSet.getString("color"),
						resultSet.getDouble("weight"),
						resultSet.getDate("manufacture_date"),
						resultSet.getDate("expiry_date"),
						resultSet.getString("country_of_origin"),
						resultSet.getLong("item_id")
						);
				
			}
			
		} catch (SQLException e) {
			System.out.println("Exception"+e.getMessage());
		}finally {
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
				if (Objects.nonNull(statement)) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("Exception"+e.getMessage());
			}
		}
		return Details;
	}


	@Override
	public boolean addItemDetails(ItemDetails itemDetails) {
		Connection connection = null ;
		PreparedStatement statement = null ;
		try {
		    connection = dataSource.getConnection();
		    
		    String query = "INSERT INTO item_details "
		            + "(description, color, weight, manufacture_date, expiry_date, country_of_origin, item_id) "
		            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
		    
		    statement = connection.prepareStatement(query);
		    statement.setString(1, itemDetails.getDescription());
		    statement.setString(2, itemDetails.getColor());
		    statement.setDouble(3, itemDetails.getWeight());
		    statement.setDate(4, itemDetails.getManufactureDate());
		    statement.setDate(5, itemDetails.getExpiryDate());
		    statement.setString(6, itemDetails.getCountryOfOrigin());
		    statement.setLong(7, itemDetails.getItemId());
		    
		    statement.executeUpdate();    
	
		    return true;
		} catch (SQLException e) {
			System.out.println("Exception"+e.getMessage());
		}finally {
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
				if (Objects.nonNull(statement)) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("Exception"+e.getMessage());
			}
			
		}
		return false;
	}


	@Override
	public boolean hasDetails(long itemId) {
		Connection connection = null ;
		PreparedStatement statement = null ;
		try {
		    connection = dataSource.getConnection();  
		    
		    String query = "select * from item_details where item_id = ?";
		    statement =  connection.prepareStatement(query);
		    statement.setLong(1, itemId);
		    
		    ResultSet resultSet = statement.executeQuery();
		
		    return resultSet.next();
		} catch (SQLException e) {
			System.out.println("Exception"+e.getMessage());
		}finally {
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
				if (Objects.nonNull(statement)) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("Exception"+e.getMessage());
			}
			
		}
		return false;
	}


	@Override
	public boolean updateItemDetails(ItemDetails itemDetails) {
		Connection connection = null ;
		PreparedStatement statement = null ;
		try {
		    connection = dataSource.getConnection();
		    
		    String query =
		    	    "UPDATE item_details SET "
		    	    + "description = ?, "
		    	    + "color = ?, "
		    	    + "weight = ?, "
		    	    + "manufacture_date = ?, "
		    	    + "expiry_date = ?, "
		    	    + "country_of_origin = ? "
		    	    + "WHERE item_id = ?";
		    
		    statement =  connection.prepareStatement(query);
		    statement.setString(1, itemDetails.getDescription());
		    statement.setString(2, itemDetails.getColor());
		    statement.setDouble(3, itemDetails.getWeight());
		    statement.setDate(4, itemDetails.getManufactureDate());
		    statement.setDate(5, itemDetails.getExpiryDate());
		    statement.setString(6, itemDetails.getCountryOfOrigin());
		    statement.setLong(7, itemDetails.getItemId());
		    
		    statement.executeUpdate();	    
	
		    return true;
		} catch (SQLException e) {
			System.out.println("Exception"+e.getMessage());
		}finally {
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
				if (Objects.nonNull(statement)) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("Exception"+e.getMessage());
			}
			
		}
		return false;
	}

}
