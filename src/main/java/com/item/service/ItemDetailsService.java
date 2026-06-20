package com.item.service;

import com.item.model.ItemDetails;

public interface ItemDetailsService {
	
	ItemDetails selectItemDetails(Long id);
	
	boolean addItemDetails(ItemDetails itemDetails);
	
	boolean hasDetails(long itemId);
	
	boolean updateItemDetails(ItemDetails itemDetails);
	

}
