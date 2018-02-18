package com.masmovil.challenge.controllers;

import com.masmovil.challenge.domain.Order;
import com.masmovil.challenge.services.OrdersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by jcorredera on 17/02/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = OrdersController.class, secure = false)
public class OrdersControllerTest {

    private String orderJson = "{\n" +
            "  \"reference\":1000,\n" +
            "  \"name\":\"Pepe\",\n" +
            "  \"surname\":\"Corredera\", \n" +
            "  \"email\":\"pca@gmail.com\",\n" +
            "  \"productList\":[1,2],\n" +
            "  \"amount\":0.0\n" +
            "}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrdersService service;

    @Test
    public void create() throws Exception {
        when(service.create(any(Order.class))).thenReturn(createOrder());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/mm/v1/orders/")
                .accept(MediaType.APPLICATION_JSON)
                .content(orderJson)
                .contentType(MediaType.APPLICATION_JSON);

        assertThat(HttpStatus.CREATED.value(),is(equalTo(mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus())));
    }

    @Test
    public void findAll() throws Exception {
        when(service.findAll()).thenReturn(createOrderList());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/mm/v1/orders/")
                .contentType(MediaType.APPLICATION_JSON);

        assertThat(HttpStatus.OK.value(),is(equalTo(mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus())));
    }

    @Test
    public void findOne() throws Exception {
        when(service.findOne(anyInt())).thenReturn(createOrder());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/mm/v1/orders/1000")
                .contentType(MediaType.APPLICATION_JSON);

        assertThat(HttpStatus.OK.value(),is(equalTo(mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus())));
    }

    private Order createOrder() {
        List<Integer> products = new ArrayList<>();
        products.add(1);
        products.add(2);
        return new Order(1000,"Pepe","Corredera","pca@gmail.com",products);
    }

    private List<Order> createOrderList() {
        List<Integer> products = new ArrayList<>();
        products.add(2);
        products.add(3);
        List<Order> orders=new ArrayList<>();
        for(int i=0;i<10;i++) {
            Order order = new Order(99+i,"name-"+i,"surname-"+i,"email"+i+"@gmail.com",products);
            orders.add(order);
        }
        return orders;
    }

//    private void clearTable() {
//        List<Order> orders = ordersService.findAll();
//        orders.stream().forEach(order -> ordersRepository.delete(order.getReference()));
//    }

}