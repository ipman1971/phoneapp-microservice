package com.masmovil.challenge.services;

import com.masmovil.challenge.DbUtilTest;
import com.masmovil.challenge.domain.Order;
import com.masmovil.challenge.repository.OrdersRepository;
import org.junit.Before;
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
public class OrdersServiceTest extends DbUtilTest {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Before
    public void setup() {
        removeOrders();
    }

    @Test
    public void create() throws Exception {
        Order order = createOrder(2000,1,2);
        assertThat(order.getAmount(),is(equalTo(0.0)));

        Order otherOrder = ordersService.create(order);

        assertThat(otherOrder.getReference(),is(equalTo(order.getReference())));
        assertThat(otherOrder.getAmount()>0.0,is(equalTo(true)));
    }

    @Test
    public void findAll() throws Exception {
        List<Order> orders = createOrderList(10,2,3);
        orders.forEach(order -> ordersService.create(order));
        List<Order> otherOrders = ordersService.findAll();

        assertThat(otherOrders.size(),is(equalTo(orders.size())));
        assertThat(otherOrders.get(0).getReference(),is(equalTo(orders.get(0).getReference())));
        assertThat(otherOrders.get(0).getAmount()>0,is(equalTo(true)));
    }

    @Test
    public void findOne() throws Exception {
        List<Order> orders = createOrderList(5,1,2);
        orders.forEach(order -> ordersService.create(order));
        Order order = ordersService.findOne(orders.get(1).getReference());

        assertThat(order,is(equalTo(orders.get(1))));
    }

}