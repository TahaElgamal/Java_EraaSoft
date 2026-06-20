package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import com.item.model.Item;
import com.item.model.User;
import com.item.service.ItemService;

public class ItemServiceImpl implements ItemService {
	
	private DataSource dataSource;

	public ItemServiceImpl(DataSource dataSource) {
		if(Objects.isNull(dataSource)) {
			throw new  IllegalArgumentException("DataSource must not be null!");
		}
		this.dataSource = dataSource;
	}

	@Override
	public List<Item> getAllItems(User user) {
		Connection connection = null ;
		PreparedStatement statement = null ;
		try {
		    connection = dataSource.getConnection();
		    
		    String query ="select * from item where user_id = ?";
		    statement =  connection.prepareStatement(query);
		    
		    statement.setLong(1, user.getId());
		    ResultSet resultSet= statement.executeQuery();	
		    
		    List<Item> items =new ArrayList();
		    
		    while (resultSet.next()) {
		    	Item item =new Item(
			    	resultSet.getLong("id"),
			    	resultSet.getString("name"),
			    	resultSet.getDouble("price"),
			    	resultSet.getInt("total_number"),
			    	resultSet.getLong("user_id")
		    	);
		    	items.add(item);
		    }
		    return items;
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
		return null;
	}

	@Override
	public Item selectItem(long userId,long id) {
		Connection connection = null ;
		PreparedStatement statement = null ;
		Item item =null;
		try {
		    connection = dataSource.getConnection();
		    
		    String query ="select * from item where user_id= ? AND id = ?";
		    
		    statement =  connection.prepareStatement(query);
		    statement.setLong(1, userId);
		    statement.setLong(2, id);
		    
		    ResultSet resultSet= statement.executeQuery();	
		    
		   
		    if(resultSet.next()) {
		    	item =new Item(
			    	resultSet.getLong("id"),
			    	resultSet.getString("name"),
			    	resultSet.getDouble("price"),
			    	resultSet.getInt("total_number"),
			    	resultSet.getLong("user_id")
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
		return item;
	}

	@Override
	public boolean addItem(Item item) {
		Connection connection = null ;
		PreparedStatement statement = null ;
		try {
		    connection = dataSource.getConnection();
		    
		    String query ="INSERT INTO item (name, price, total_number, user_id) VALUES (?, ?, ?, ?)";
		    
		    statement =  connection.prepareStatement(query);
		    statement.setString(1, item.getName());
		    statement.setDouble(2, item.getPrice());
		    statement.setInt(3, item.getTotalNumber());
		    statement.setLong(4, item.getUserId());
		    
		    statement.executeUpdate();	    
	
		    return true;
		} catch (SQLException e) {
			System.out.println("Exception"+e.getMessage());
		}finally {
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}if (Objects.nonNull(statement)) {
					statement.close();
				}
				
			} catch (SQLException e) {
				System.out.println("Exception"+e.getMessage());
			}
			
		}
		return false;
	}

	@Override
	public boolean updateItem(Item item) {
		Connection connection = null ;
		PreparedStatement statement = null ;
		try {
		    connection = dataSource.getConnection();
		    
		    String query = "UPDATE item SET name = ?, price = ?, total_number = ? WHERE id = ?";

		    statement = connection.prepareStatement(query);
		    statement.setString(1, item.getName());
		    statement.setDouble(2, item.getPrice());
		    statement.setInt(3, item.getTotalNumber());
		    statement.setLong(4, item.getId());

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
	public boolean deleteItem(long id) {
		Connection connection = null ;
		PreparedStatement statement1 = null ;
		PreparedStatement statement2 = null ;
		try {
		    connection = dataSource.getConnection();
		    
		    String query1 = "DELETE FROM item_details WHERE item_id = ?";
		    String query2 = "DELETE FROM item WHERE id = ?";

		    statement1 = connection.prepareStatement(query1);
		    statement2 = connection.prepareStatement(query2);

		    statement1.setLong(1, id);
		    statement2.setLong(1, id);

		    statement1.executeUpdate();
		    statement2.executeUpdate();    
	
		    return true;
		} catch (SQLException e) {
			System.out.println("Exception"+e.getMessage());
		}finally {
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
				if (Objects.nonNull(statement1)) {
					statement1.close();
				}
				if (Objects.nonNull(statement2)) {
					statement2.close();
				}
			} catch (SQLException e) {
				System.out.println("Exception"+e.getMessage());
			}
			
		}
		return false;
	}

	@Override
	public boolean isItemExist(String name, long userId) {
		Connection connection = null ;
		PreparedStatement statement = null ;
		try {
		    connection = dataSource.getConnection();
		    String query = "SELECT * FROM item WHERE name = ? AND user_id = ? ";
		    
		    statement =  connection.prepareStatement(query);
		    statement.setString(1, name);
		    statement.setLong(2, userId);
		    
		    ResultSet resultset= statement.executeQuery();

		    return resultset.next();
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
