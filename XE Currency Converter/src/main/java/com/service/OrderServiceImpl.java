package com.service;

import com.domain.entities.Order;
import com.domain.entities.User;
import com.domain.entities.dtos.OrderDto;
import com.repository.OrderRepository;
import com.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

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

        order.setDate(getCurrentDate());

        order.setUser(user);

        user.getOrders().add(order);


        userRepository.saveAndFlush(user);
//        orderRepository.saveAndFlush(order);
    }

    private LocalDate getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(now.format(format), DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    @Override
    public Set<OrderDto> getAllUsersOrders(Integer userId) {

        return mapOrderToOrderDto(orderRepository.findAllByUserId(userId));
    }

    private Set<OrderDto> mapOrderToOrderDto(Set<Order> orders) {

        Set<OrderDto> orderDtos = new LinkedHashSet<>();

        orders.forEach(o -> {
            OrderDto orderDto = modelMapper.map(o, OrderDto.class);
            orderDtos.add(orderDto);
        });

        return orderDtos;
    }

}
