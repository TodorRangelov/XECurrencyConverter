package com.service;

import com.domain.entities.User;
import com.domain.entities.dtos.OrderDto;

import java.util.Set;

public interface OrderService {

    void addOrder(OrderDto orderDto, User user);

    Set<OrderDto> getAllUsersOrders(Integer userId);

}
