package com.service;

import com.domain.entities.User;
import com.domain.entities.dtos.OrderDto;

public interface OrderService {

    void addOrder(OrderDto orderDto, User user);

}
