package com.service;

import com.domain.entities.Order;
import com.domain.entities.User;
import com.domain.entities.dtos.OrderDto;
import com.repository.OrderRepository;
import com.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private ModelMapper modelMapper;
    private final UserRepository userRepository;



    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void addOrder(OrderDto orderDto, User user) {

        Order order = modelMapper.map(orderDto, Order.class);
        order.setUser(user);

        user.getOrders().add(order);


        userRepository.saveAndFlush(user);
//        orderRepository.saveAndFlush(order);
    }
}
