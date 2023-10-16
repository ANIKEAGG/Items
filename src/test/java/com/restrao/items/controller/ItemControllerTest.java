package com.restrao.items.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restrao.items.entities.Category;
import com.restrao.items.entities.Items;
import com.restrao.items.services.ItemServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ItemController.class})
@ExtendWith(SpringExtension.class)
class ItemControllerTest {
    @Autowired
    private ItemController itemController;

    @MockBean
    private ItemServices itemServices;

    /**
     * Method under test: {@link ItemController#addItem(Items)}
     */
    @Test
    void testAddItem() throws Exception {
        Items items = new Items();
        items.setCategory(Category.FAST_FOOD);
        items.setDescription("The characteristics of someone or something");
        items.setItemId(1L);
        items.setName("Name");
        items.setPrice(10.0d);
        when(itemServices.addItem(Mockito.<Items>any())).thenReturn(items);

        Items items2 = new Items();
        items2.setCategory(Category.FAST_FOOD);
        items2.setDescription("The characteristics of someone or something");
        items2.setItemId(1L);
        items2.setName("Name");
        items2.setPrice(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(items2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(itemController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"itemId\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"category\":\"FAST"
                                        + "_FOOD\",\"price\":10.0}"));
    }
    /**
     * Method under test: {@link ItemController#updateItem(Items)}
     */
//    @Test
//    void testUpdateItem() throws Exception {
//        Items items = new Items();
//        items.setCategory(Category.FAST_FOOD);
//        items.setDescription("The characteristics of someone or something");
//        items.setItemId(1L);
//        items.setName("Name");
//        items.setPrice(10.0d);
//        when(itemServices.updateItem(Mockito.<Items>any())).thenReturn(items);
//
//        Items items2 = new Items();
//        items2.setCategory(Category.FAST_FOOD);
//        items2.setDescription("The characteristics of someone or something");
//        items2.setItemId(1L);
//        items2.setName("Name");
//        items2.setPrice(10.0d);
//        String content = (new ObjectMapper()).writeValueAsString(items2);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateItem")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(itemController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"itemId\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"category\":\"FAST"
//                                        + "_FOOD\",\"price\":10.0}"));
//    }

    /*
     * Method under test: {@link ItemController#deleteItem(Long)}
     */
    @Test
    void testDeleteItem() throws Exception {
        when(itemServices.deleteItem(Mockito.<Long>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteItem/{itemId}", 1L);
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link ItemController#getItem(Long)}
     */
    @Test
    void testGetItem() throws Exception {
        Items items = new Items();
        items.setCategory(Category.FAST_FOOD);
        items.setDescription("The characteristics of someone or something");
        items.setItemId(1L);
        items.setName("Name");
        items.setPrice(10.0d);
        when(itemServices.getItemById(Mockito.<Long>any())).thenReturn(items);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getItem/{itemId}", 1L);
        MockMvcBuilders.standaloneSetup(itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"itemId\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"category\":\"FAST"
                                        + "_FOOD\",\"price\":10.0}"));
        
    }
}

