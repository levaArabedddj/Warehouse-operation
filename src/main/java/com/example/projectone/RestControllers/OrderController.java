package com.example.projectone.RestControllers;

import com.example.projectone.DTO.OrderDTO;
import com.example.projectone.Entity.Order;
import com.example.projectone.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }
    @GetMapping("/getAllOrder")
    public List<OrderDTO> getAllOrder(){
        return orderService.getAllOrder();
    }
    @GetMapping("/getOrder/{}")
    public OrderDTO orderDTO(@PathVariable long id) throws Exception {
        return orderService.getOderById(id);
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<Order> updateOrder(@RequestParam Long id, @RequestBody Order orderDetails) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDetails));
    }

    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Void> deleteOrder(@RequestParam Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

