package com.masmovil.challenge.services;

import com.masmovil.challenge.domain.Order;

import java.util.List;

/**
 * Created by jcorredera on 17/02/18.
 */
public interface OrdersService {

    public Order create(Order order);

    public List<Order> findAll();

    public Order findOne(Integer reference);

}
