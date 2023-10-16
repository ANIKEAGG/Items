package com.restrao.items.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restrao.items.dto.ItemDTO;
import com.restrao.items.entities.Items;
import com.restrao.items.exceptions.ItemIdNotFound;
import com.restrao.items.services.ItemServices;

import jakarta.validation.Valid;

@RestController
public class ItemController {

	@Autowired
	private ItemServices itemServices;
	
	@PostMapping(value="/addItem" , consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Items> addItem(@RequestBody @Valid Items item){
		return new ResponseEntity<Items>(itemServices.addItem(item), HttpStatus.CREATED);
	}
	
	@GetMapping("/getItem/{itemId}")
	public ResponseEntity<Items> getItem(@PathVariable Long itemId) throws ItemIdNotFound{
		return new ResponseEntity<Items>(itemServices.getItemById(itemId), HttpStatus.FOUND);
	}
	
	@PutMapping(value="/updateItem")//, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Items> updateItem(@RequestBody ItemDTO item) throws ItemIdNotFound{
		System.out.println("request"+item);
		Items items=itemServices.updateItem(item);
		System.out.println("response"+items);
		return new ResponseEntity<Items>(items, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteItem/{itemId}")
	public ResponseEntity<Boolean> deleteItem(@PathVariable Long itemId) throws ItemIdNotFound{
		return new ResponseEntity<>(itemServices.deleteItem(itemId), HttpStatus.OK);
	}
}