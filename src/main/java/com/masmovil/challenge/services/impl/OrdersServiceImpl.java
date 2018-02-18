package com.masmovil.challenge.services.impl;

import com.masmovil.challenge.domain.Order;
import com.masmovil.challenge.domain.Phone;
import com.masmovil.challenge.repository.OrdersRepository;
import com.masmovil.challenge.repository.PhonesRepository;
import com.masmovil.challenge.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jcorredera on 17/02/18.
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    @Override
    public Order create(Order order) {
        Double amount = order.getProductList().stream().map(reference -> {
            Phone phone = (Phone) phonesRepository.findOne(reference);
            return phone.getPrice();
        }).reduce(0.0, Double::sum);
        order.setAmount(amount);
        ordersRepository.create(order.getReference(),order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Order findOne(Integer reference) {
        return (Order) ordersRepository.findOne(reference);
    }

}
