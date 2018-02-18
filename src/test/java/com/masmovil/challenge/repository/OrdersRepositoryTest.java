package com.masmovil.challenge.repository;

import com.masmovil.challenge.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by jcorredera on 15/02/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository repository;

    @Test
    public void create() throws Exception {
        clearTable();
        Order order = createOrder();
        repository.create(order.getReference(), order);

        Order oneOrder = (Order) repository.findOne(order.getReference());

        assertThat(order, is(equalTo(oneOrder)));
    }

    @Test
    public void findOne() throws Exception {
        clearTable();
        Order order = createOrder();
        repository.create(order.getReference(), order);

        Order oneOrder = (Order) repository.findOne(order.getReference());

        assertThat(order, is(equalTo(oneOrder)));
    }

    @Test
    public void notFindOne() throws Exception {
        clearTable();
        Order order = createOrder();
        repository.create(order.getReference(), order);

        Order oneOrder =(Order)repository.findOne(10);

        assertThat(oneOrder,is(nullValue()));
    }

    @Test
    public void findAll() throws Exception {
        clearTable();
        Order order = createOrder();
        repository.create(order.getReference(), order);

        assertThat(repository.findAll().size(), is(equalTo(1)));
    }

    @Test
    public void update() throws Exception {
        clearTable();
        Order order = createOrder();
        repository.create(order.getReference(), order);


        order.setName("Jose");
        repository.update(order.getReference(), order);

        Order oneOrder = (Order) repository.findOne(order.getReference());

        assertThat(oneOrder.getName(), is(equalTo("Jose")));
    }

    @Test
    public void delete() throws Exception {
        clearTable();
        Order order = createOrder();
        repository.create(order.getReference(), order);

        repository.delete(order.getReference());

        assertThat(repository.findAll().size(), is(equalTo(0)));

    }

    private Order createOrder() {
        List<Integer> products=new ArrayList<>();
        products.add(1);
        products.add(2);
        return new Order(100,"Pepe","Corredera","pca@gmail.com",products);
    }

    private void clearTable() {
        List<Order> orders = repository.findAll();
        orders.stream().forEach(order -> repository.delete(order.getReference()));
    }

}
