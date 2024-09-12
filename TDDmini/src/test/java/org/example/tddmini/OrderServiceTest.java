package org.example.tddmini;

import org.example.tddmini.entity.Order;
import org.example.tddmini.service.OrderService;
import org.example.tddmini.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order("John Doe", LocalDate.now(), "123 Main St", 150.00);
    }

    @Test
    public void testCreateOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);
        assertEquals("John Doe", createdOrder.getCustomerName());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testGetOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById(1L);
        assertEquals("John Doe", foundOrder.get().getCustomerName());
    }

    @Test
    public void testUpdateOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updatedOrder = new Order("Jane Doe", LocalDate.now(), "456 Elm St", 200.00);
        Order result = orderService.updateOrder(1L, updatedOrder);
        assertEquals("Jane Doe", result.getCustomerName());
        assertEquals(200.00, result.getTotal());
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);
        orderService.deleteOrder(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateOrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        Order updatedOrder = new Order("Jane Doe", LocalDate.now(), "456 Elm St", 200.00);
        assertThrows(IllegalArgumentException.class, () -> orderService.updateOrder(1L, updatedOrder));
    }
}
