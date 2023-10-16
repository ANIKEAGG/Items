package com.restrao.items.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restrao.items.dto.ItemDTO;
import com.restrao.items.entities.Category;
import com.restrao.items.entities.Items;
import com.restrao.items.exceptions.ItemIdNotFound;
import com.restrao.items.repos.ItemsRepo;

@Component
public class ItemServiceImpl implements ItemServices{

	@Autowired
	private ItemsRepo itemsRepo;
	
	@Override
	public Items addItem(Items item) {
		return itemsRepo.save(item);
	}

	@Override
	public Items updateItem(ItemDTO item) throws ItemIdNotFound {
		itemsRepo.findById(item.getItemId()).orElseThrow(()-> new ItemIdNotFound("Item not Found For Item Id:-"+item.getItemId()));
		Items items=Items.builder()
				.itemId(item.getItemId())
				.name(item.getName())
				.category(Category.valueOf(Category.class, item.getCategory()))
				.description(item.getDescription())
				.price(item.getPrice())
				.build();
//		if()
		return itemsRepo.save(items);
	}

	@Override
	public Items getItemById(Long itemId) throws ItemIdNotFound {
		return itemsRepo.findById(itemId).orElseThrow(()-> new ItemIdNotFound("Item not Found For Item Id:-"+itemId));
	}

	@Override
	public Boolean deleteItem(Long itemId) throws ItemIdNotFound {
		Items item=itemsRepo.findById(itemId).orElseThrow(()-> new ItemIdNotFound("Item not Found For Item Id:-"+itemId));
		if(item!=null) {
			itemsRepo.deleteById(itemId);
			return true;
		}
		return false;
	}

	
}
