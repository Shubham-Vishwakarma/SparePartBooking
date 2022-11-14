package com.ford.sparepartbooking.service;

import com.ford.sparepartbooking.exceptions.UserAbsentException;
import com.ford.sparepartbooking.models.*;
import com.ford.sparepartbooking.models.order.Order;
import com.ford.sparepartbooking.models.order.OrderDTO;
import com.ford.sparepartbooking.models.order.OrderItem;
import com.ford.sparepartbooking.models.order.OrderItemDTO;
import com.ford.sparepartbooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(long userId) {
        Optional<User> oUser = userRepository.findById(userId);

        if(oUser.isEmpty())
            throw new UserAbsentException(userId);

        return oUser.get();
    }

    public boolean isAdmin(long userId) {
        User user = getUser(userId);
        return user.getRole() != null && user.getRole().equals("ADMIN");
    }

    public Iterable<OrderDTO> getOrders(long userId) {
        User user = getUser(userId);

        List<OrderDTO> orders = new LinkedList<>();

        for(Order order : user.getOrders()) {
            List<OrderItemDTO> orderItems = new LinkedList<>();
            for(OrderItem item : order.getOrderItems()) {
                OrderItemDTO itemDTO = new OrderItemDTO();
                itemDTO.setOrderItemId(item.getId());
                itemDTO.setSparePart(item.getSparePart().getName());
                itemDTO.setQuantity(item.getQuantity());
                orderItems.add(itemDTO);
            }
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(order.getId());
            orderDTO.setOrderItems(orderItems);
            orders.add(orderDTO);
        }

        return orders;
    }
}
