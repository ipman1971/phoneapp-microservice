package com.masmovil.challenge.repository.impl;

import com.masmovil.challenge.domain.Order;
import com.masmovil.challenge.repository.AbstractBaseRepository;
import com.masmovil.challenge.repository.OrdersRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jcorredera on 17/02/18.
 */
@Repository
public class OrdersRepositoryImpl extends AbstractBaseRepository<Integer,Order > implements OrdersRepository<Integer,Order> {
}
