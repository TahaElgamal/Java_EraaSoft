package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javax.annotation.Resource;
import javax.sql.DataSource;

import com.item.model.User;
import com.item.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Resource(name= "jdbc/item")
	private DataSource dataSource;
	
	

	public UserServiceImpl(DataSource dataSource) {
		if(Objects.isNull(dataSource)) {
			throw new  IllegalArgumentException("DataSource must not be null!");
		}
		this.dataSource = dataSource;
	}

	
	@Override
	public boolean createUser(User user) {
		Connection connection = null ;
		PreparedStatement statement = null ;
		try {
		    connection = dataSource.getConnection();
		    
		    String query ="INSERT INTO users (username, email, password, age, phone_number) VALUES ( ?, ?, ?, ?, ?)";   
		    
		    statement =  connection.prepareStatement(query);
		    
		    statement.setString(1,user.getUserName());
		    statement.setString(2, user.getEmail());
		    statement.setString(3,user.getPassword());
		    statement.setInt(4,user.getAge());
		    statement.setString(5,user.getPhoneNumber());
		    
		    
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
	public User selectUser(User user) {
		Connection connection = null ;
		PreparedStatement statement = null ;
		User userLogged = null;
		try {
		    connection = dataSource.getConnection();
		   
		    String query ="select * from users WHERE username = ? AND password = ?";
		    
		    statement =  connection.prepareStatement(query);
		    statement.setString(1, user.getUserName());
		    statement.setString(2, user.getPassword());
		    
		    ResultSet resultSet= statement.executeQuery();	 
		    
		    if(resultSet.next()) {
		    	userLogged =new User(
			    	resultSet.getLong("id"),
			    	resultSet.getString("username"),
			    	resultSet.getString("email"),
			    	resultSet.getString("password"),
			    	resultSet.getInt("age"),
			    	resultSet.getString("phone_number")
		    	);
		    }
	
		    return userLogged;
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
	public boolean isUserNameExist(String username) {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {

	        connection = dataSource.getConnection();

	        String query ="SELECT * FROM users WHERE username = ? ";

	        statement = connection.prepareStatement(query);
	        statement.setString(1, username);
	        
	        resultSet =statement.executeQuery();

	        return resultSet.next();

	    } catch(SQLException e) {

	        System.out.println(
	            "Exception " + e.getMessage()
	        );

	    } finally {

	        try {

	            if(resultSet != null) {
	                resultSet.close();
	            }

	            if (Objects.nonNull(connection)) {
					connection.close();
				}
				if (Objects.nonNull(statement)) {
					statement.close();
				}

	        } catch(SQLException e) {

	            System.out.println(
	                "Exception " + e.getMessage()
	            );
	        }
	    }
	return false;
	}


	@Override
	public boolean deleteUser(long id) {
	    Connection connection = null;
	    PreparedStatement statement1 = null;
	    PreparedStatement statement2 = null;
	    PreparedStatement statement3 = null;
	    try {
	        connection = dataSource.getConnection();
	        

	        String query1 =
	        "delete from item_details "
	        + "where item_id in "
	        + "(select id from item "
	        + "where user_id = ? )";

	        String query2 =
	        "delete from item "
	        + "where user_id = ? " ;

	        String query3 =
	        "delete from users "
	        + "where id = ? ";
	        statement1 = connection.prepareStatement(query1);
	        statement2 = connection.prepareStatement(query2);
	        statement3 = connection.prepareStatement(query3);
	        
	        statement1.setLong(1, id);
	        statement2.setLong(1, id);
	        statement3.setLong(1, id);
	        
	        statement1.executeUpdate();
	        statement2.executeUpdate();
	        statement3.executeUpdate();

	        return true;

	    } catch(SQLException e){

	        System.out.println(
	            "Exception " + e.getMessage()
	        );

	    } finally {
	        try {
	            if(Objects.nonNull(connection)){
	                connection.close();
	            }
	            if(Objects.nonNull(statement1)){
	                statement1.close();
	            }
	            if(Objects.nonNull(statement2)){
	                statement2.close();
	            }
	            if(Objects.nonNull(statement3)){
	                statement3.close();
	            }
	        } catch(SQLException e){
	           System.out.println(
	                "Exception " + e.getMessage()
	            );
	        }
	    }

	    return false;
	}

}
