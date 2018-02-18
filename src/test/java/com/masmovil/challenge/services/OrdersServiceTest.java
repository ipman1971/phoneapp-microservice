package com.masmovil.challenge.services;

import com.masmovil.challenge.domain.Order;
import com.masmovil.challenge.repository.OrdersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jcorredera on 18/02/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersServiceTest {

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrdersRepository ordersRepository;

    @Test
    public void create() throws Exception {
        clearTable();
        Order order = createOrder();
        assertThat(order.getAmount(),is(equalTo(0.0)));

        Order otherOrder = ordersService.create(order);

        assertThat(otherOrder.getReference(),is(equalTo(order.getReference())));
        assertThat(otherOrder.getAmount()>0.0,is(equalTo(true)));
    }

    @Test
    public void findAll() throws Exception {
        clearTable();
        List<Order> orders = createOrderList();
        orders.stream().forEach(order -> ordersService.create(order));
        List<Order> otherOrders = ordersService.findAll();

        assertThat(otherOrders.size(),is(equalTo(orders.size())));
        assertThat(otherOrders.get(0).getReference(),is(equalTo(orders.get(0).getReference())));
        assertThat(otherOrders.get(0).getAmount()>0,is(equalTo(true)));
    }

    @Test
    public void findOne() throws Exception {
        clearTable();
        List<Order> orders = createOrderList();
        orders.stream().forEach(order -> ordersService.create(order));
        Order order = ordersService.findOne(orders.get(5).getReference());

        assertThat(order,is(equalTo(orders.get(5))));
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

    private void clearTable() {
        List<Order> orders = ordersService.findAll();
        orders.stream().forEach(order -> ordersRepository.delete(order.getReference()));
    }

}