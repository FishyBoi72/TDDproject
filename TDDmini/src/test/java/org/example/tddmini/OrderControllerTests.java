package org.example.tddmini;


import org.example.tddmini.entity.Order;
import org.example.tddmini.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder() throws Exception {
        String orderJson = "{\"customerName\":\"John Doe\",\"orderDate\":\"2024-09-12\",\"shippingAddress\":\"123 Main St\",\"total\":100.50}";

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.shippingAddress").value("123 Main St"));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order("John Doe", LocalDate.now(), "123 Main St", 100.50);
        order = orderRepository.save(order);

        mockMvc.perform(get("/api/orders/" + order.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Order order = new Order("John Doe", LocalDate.now(), "123 Main St", 100.50);
        order = orderRepository.save(order);

        String updatedOrderJson = "{\"customerName\":\"Jane Doe\",\"orderDate\":\"2024-09-12\",\"shippingAddress\":\"456 Elm St\",\"total\":150.00}";

        mockMvc.perform(put("/api/orders/" + order.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedOrderJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Jane Doe"))
                .andExpect(jsonPath("$.total").value(150.00));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        Order order = new Order("John Doe", LocalDate.now(), "123 Main St", 100.50);
        order = orderRepository.save(order);

        mockMvc.perform(delete("/api/orders/" + order.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}