package com.example.projectone.service;

import com.example.projectone.Entity.Order;
import com.example.projectone.Repository.OrderRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @SneakyThrows
    public Order updateOrder(Long id, Order orderDetails) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setDateCreatedOrders(orderDetails.getDateCreatedOrders());
            existingOrder.setOrderStatus(orderDetails.getOrderStatus());
            existingOrder.setProducts(orderDetails.getProducts());
            existingOrder.setClient(orderDetails.getClient());
            existingOrder.setQuantityProduct(orderDetails.getQuantityProduct());
            existingOrder.setPrice(orderDetails.getPrice());
            return orderRepository.save(existingOrder);
        } else {
            throw new Exception("Order with id " + id + " not found");
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

