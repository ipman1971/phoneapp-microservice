package com.masmovil.challenge.controllers;

import com.masmovil.challenge.domain.Order;
import com.masmovil.challenge.services.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by jcorredera on 17/02/18.
 */
@RestController
@RequestMapping(value = "/mm/v1/orders")
@Api(value = "Orders", description = "API for Orders")
public class OrdersController extends AbstractControllerHandler {

    @Autowired
    OrdersService ordersService;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "create new order",nickname = "add-orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @ResponseBody
    public Order create(@RequestBody Order order, HttpServletRequest request,
                              HttpServletResponse response) {
        Order calculateOrder = ordersService.create(order);
        calculateOrder.removeLinks();
        calculateOrder.add(linkTo(OrdersController.class).slash(order.getReference()).withSelfRel());
        return calculateOrder;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all orders availables in database",nickname = "orders-list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @ResponseBody
    public List<Order> findAll() {
        List<Order> orders = ordersService.findAll();
        orders.stream().forEach(order -> {
            order.removeLinks();
            order.add(linkTo(OrdersController.class).slash(order.getReference()).withSelfRel());
        });
        return orders;
    }

    @RequestMapping(value = "/{reference}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all info for order",nickname = "find-order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @ResponseBody
    public Order findOne(@PathVariable Integer reference, HttpServletRequest request,
                            HttpServletResponse response) {
        Order order = ordersService.findOne(reference);
        order.removeLinks();
        order.add(linkTo(OrdersController.class).slash(order.getReference()).withSelfRel());
        return order;
    }

}
