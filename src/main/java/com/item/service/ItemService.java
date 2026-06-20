package com.item.service;

import java.util.List;

import com.item.model.Item;
import com.item.model.User;

public interface ItemService {
	
	List<Item> getAllItems(User user);
	
	Item selectItem(long userId,long id);
	
	boolean addItem(Item item);
	
	boolean updateItem(Item item);
	
	boolean deleteItem(long id);
	
	public boolean isItemExist(String name, long userId);
	
}
