package com.masmovil.challenge.repository;

import com.masmovil.challenge.DbUtilTest;
import com.masmovil.challenge.domain.Order;
import org.junit.Before;
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
public class OrdersRepositoryTest extends DbUtilTest {

    @Autowired
    private OrdersRepository repository;

    @Before
    public void setup() {
        removeOrders();
    }

    @Test
    public void create() throws Exception {
        Order order = createOrder(10,1,2);
        repository.create(order.getReference(), order);

        Order oneOrder = (Order) repository.findOne(order.getReference());

        assertThat(order, is(equalTo(oneOrder)));
    }

    @Test
    public void findOne() throws Exception {
        Order order = createOrder(10,1,2);
        repository.create(order.getReference(), order);

        Order oneOrder = (Order) repository.findOne(order.getReference());

        assertThat(order, is(equalTo(oneOrder)));
    }

    @Test
    public void notFindOne() throws Exception {
        Order order = createOrder(10,1);
        repository.create(order.getReference(), order);

        Order oneOrder =(Order)repository.findOne(14);

        assertThat(oneOrder,is(nullValue()));
    }

    @Test
    public void findAll() throws Exception {
        Order order = createOrder(10,2);
        repository.create(order.getReference(), order);

        assertThat(repository.findAll().size(), is(equalTo(1)));
    }

    @Test
    public void update() throws Exception {
        Order order = createOrder(10,1,2,3);
        repository.create(order.getReference(), order);


        order.setName("name-34");
        repository.update(order.getReference(), order);

        Order oneOrder = (Order) repository.findOne(order.getReference());

        assertThat(oneOrder.getName(), is(equalTo("name-34")));
    }

    @Test
    public void delete() throws Exception {
        Order order = createOrder(5,2);
        repository.create(order.getReference(), order);

        repository.delete(order.getReference());

        assertThat(repository.findAll().size(), is(equalTo(0)));

    }

}
