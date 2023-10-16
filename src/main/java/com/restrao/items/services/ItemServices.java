package com.restrao.items.services;

import com.restrao.items.dto.ItemDTO;
import com.restrao.items.entities.Items;
import com.restrao.items.exceptions.ItemIdNotFound;

public interface ItemServices {
	
	public Items addItem(Items item);
	public Items updateItem(ItemDTO item)throws ItemIdNotFound;
	public Items getItemById(Long itemId) throws ItemIdNotFound;
	public Boolean deleteItem(Long itemId) throws ItemIdNotFound;
	
	

}
