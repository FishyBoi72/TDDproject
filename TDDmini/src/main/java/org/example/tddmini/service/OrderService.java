package org.example.tddmini.service;

import org.example.tddmini.entity.Order;
import org.example.tddmini.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomerName(updatedOrder.getCustomerName());
                    order.setOrderDate(updatedOrder.getOrderDate());
                    order.setShippingAddress(updatedOrder.getShippingAddress());
                    order.setTotal(updatedOrder.getTotal());
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}