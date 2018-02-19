package com.masmovil.challenge;

import com.masmovil.challenge.domain.Order;
import com.masmovil.challenge.domain.Phone;
import com.masmovil.challenge.repository.OrdersRepository;
import com.masmovil.challenge.repository.PhonesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DbUtilTest {

    @Autowired
    private PhonesRepository phonesRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    protected Phone createPhone(int id, double price) {
        return new Phone(id,"model-"+id,"description for model-"+id,"./model-"+id+".png",price);
    }

    protected List<Phone> createPhoneList(int size, double price) {
        List<Phone> phones=new ArrayList<>();
        for(int i=0; i<size;i++) {
            phones.add(createPhone(i,price + (i*10)));
        }
        return phones;
    }

    protected void removePhones() {
        List<Phone> phones = phonesRepository.findAll();
        phones.stream().forEach(phone -> phonesRepository.delete(phone.getReference()));
    }

    protected Order createOrder(int id,int... productsReference) {
        List<Integer> products=createProducts(productsReference);
        return new Order(id,"name-"+id,"surname-"+id,"email"+id+"@gmail.com",products);
    }

    protected List<Order> createOrderList(int size,int... productsReference) {
        List<Order> orders=new ArrayList<>();
        for(int i=0;i<size;i++) {
            Order order = createOrder(i,productsReference);
            orders.add(order);
        }
        return orders;
    }

    protected List<Integer> createProducts(int... productsReference) {
        List<Integer> products=new ArrayList<>();
        for(int productReference: productsReference) {
            Phone phone = createPhone(productReference,100.50);
            phonesRepository.create(phone.getReference(),phone);
            products.add(productReference);
        }
        return products;
    }

    protected void removeOrders() {
        List<Order> orders = ordersRepository.findAll();
        orders.stream().forEach(order -> ordersRepository.delete(order.getReference()));
    }

}
