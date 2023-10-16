package com.restrao.items;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.restrao.items.controller.ItemController;
import com.restrao.items.entities.Category;
import com.restrao.items.entities.Items;
import com.restrao.items.services.ItemServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ItemsControllerTests {

	@Mock
	private ItemServiceImpl itemService;
	
	@InjectMocks
	private ItemController itemController;
	
	ResponseEntity<Items> item;
	
	@BeforeEach
	public void SetItem() {
		
//		item=Items.builder()
//				.category(Category.PIZZA)
//				.description("An Italian Dish")
//				.name("Pizza")
//				.build();
		item=new ResponseEntity<>(Items.builder()
				.category(Category.PIZZA)
				.description("An Italian Dish")
				.name("Pizza")
				.build(),HttpStatus.CREATED);
		
	}
	
	@AfterEach
	public void ReleaseItem() {
		item=null;
	}
	
	@Test
	public void AddItemTest() {
		
		
		
	}
}
