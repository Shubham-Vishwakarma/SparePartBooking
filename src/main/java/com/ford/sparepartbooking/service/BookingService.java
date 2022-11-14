package com.ford.sparepartbooking.service;

import com.ford.sparepartbooking.exceptions.BookingTerminatedException;
import com.ford.sparepartbooking.exceptions.OutOfStockException;
import com.ford.sparepartbooking.models.BookingItem;
import com.ford.sparepartbooking.models.BookingRequest;
import com.ford.sparepartbooking.models.User;
import com.ford.sparepartbooking.models.order.Order;
import com.ford.sparepartbooking.models.order.OrderItem;
import com.ford.sparepartbooking.repository.OrderItemRepository;
import com.ford.sparepartbooking.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class BookingService {

    private final SparePartService sparePartService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public BookingService(SparePartService sparePartService,
                          UserService userService,
                          OrderRepository orderRepository,
                          OrderItemRepository orderItemRepository) {
        this.sparePartService = sparePartService;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public long bookSpareParts(BookingRequest request) {
        User user = userService.getUser(request.getUserId());

        if(!canProceedWithBooking(request.getSpareParts()))
            throw new BookingTerminatedException();

        Order order = new Order();
        order.setUser(user);
        Order newOrder = orderRepository.save(order);

        bookItems(request.getSpareParts(), newOrder);

        return newOrder.getId();
    }

    private boolean canProceedWithBooking(Iterable<BookingItem> items) {
        for(BookingItem item : items) {
            if(!sparePartService.inStock(item.getSparePart(), item.getQuantity()))
                throw new OutOfStockException(item.getSparePart());
        }

        return true;
    }

    private void bookItems(Iterable<BookingItem> items, Order order) {
        for(BookingItem item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setSparePart(sparePartService.getSparePart(item.getSparePart()));
            orderItem.setQuantity(item.getQuantity());
            orderItemRepository.save(orderItem);
            sparePartService.updateSparePart(item.getSparePart(), item.getQuantity());
        }
    }
}
