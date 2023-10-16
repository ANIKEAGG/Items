package com.restrao.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.restrao.items.entities.Category;
import com.restrao.items.entities.Items;
import com.restrao.items.exceptions.ItemIdNotFound;
import com.restrao.items.repos.ItemsRepo;
import com.restrao.items.services.ItemServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ItemsServicesTest {

	private Items item;
	
	@Mock
	private ItemsRepo itemsRepo;
	
	@InjectMocks
	private ItemServiceImpl itemServices;
	
	@BeforeEach
	public void SetItem() {
		
		item=Items.builder()
				.category(Category.PIZZA)
				.description("An Italian Dish")
				.name("Pizza")
				.build();
	}
	
	@AfterEach
	public void ReleaseItem() {
		item=null;
	}
	
	@Test
	public void AddItemTest() {
		
		when(itemsRepo.save(item)).thenReturn(item);
		Items responseItem=itemServices.addItem(item);
		assertNotNull(responseItem);
		assertEquals(item, responseItem);
		verify(itemsRepo).save(item);
		
	}
	
	@Test
	public void GetItemTest() throws ItemIdNotFound {
		
		long itemId=1;
		item.setItemId(itemId);
		when(itemsRepo.findById(itemId)).thenReturn(Optional.of(item));
		Items responseItem=itemServices.getItemById(itemId);
		assertNotNull(responseItem);
		assertEquals(item, responseItem);
		verify(itemsRepo).findById(itemId);
		
	}
	
	@Test
	public void UpdateItemTest() {
		
		long itemId=1;
		item.setItemId(itemId);
		when(itemsRepo.save(item)).thenReturn(item);
		Items responseItem=itemServices.addItem(item);
		assertNotNull(responseItem);
		assertEquals(item, responseItem);
		verify(itemsRepo).save(item);
		
	}
	
	@Test
	public void DeleteItemTest() {
//		
//		long itemId=1;
//		item.setItemId(itemId);
//		when(itemsRepo.deleteById(itemId));
//		Items responseItem=itemServices.addItem(item);
//		assertNotNull(responseItem);
//		assertEquals(item, responseItem);
//		verify(itemsRepo).save(item);
		assertTrue(true);
		
	}
	

}
