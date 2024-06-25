package com.example.projectone.service;

import com.example.projectone.DTO.CategoryDTO;
import com.example.projectone.DTO.ClientDTO;
import com.example.projectone.DTO.OrderDTO;
import com.example.projectone.DTO.ProductDTO;
import com.example.projectone.Entity.Order;
import com.example.projectone.Repository.OrderRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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


    public List<OrderDTO> getAllOrder(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::convertToOrderDTO).collect(Collectors.toList());
    }

    public OrderDTO getOderById(Long id) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Supplier not found with id " + id));
        return convertToOrderDTO(order);
    }

    private OrderDTO convertToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setDateCreatedOrders(order.getDateCreatedOrders());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setQuantityProduct(order.getQuantityProduct());

        Set<ProductDTO> productDTOSet = order.getProducts()
                        .stream()
                .map( product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(product.getId());
                    productDTO.setNameProduct(product.getNameProduct());
                    productDTO.setQuantity(product.getQuantity());
                    productDTO.setPrice(product.getPrice());
                    if(product.getCategory() != null){
                        CategoryDTO categoryDTO = new CategoryDTO();
                        categoryDTO.setId(product.getCategory().getId());
                        categoryDTO.setNameCategory(product.getCategory().getNameCategory());
                        productDTO.setCategory(categoryDTO);
                    }
                    return productDTO;
                }).collect(Collectors.toSet());
        orderDTO.setProducts(productDTOSet);

        if(order.getClient()!= null) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setId(order.getClient().getId());
            clientDTO.setName(order.getClient().getName());
            clientDTO.setSurName(order.getClient().getSurName());
            clientDTO.setPhoneNumber(order.getClient().getPhoneNumber());

            orderDTO.setClient(clientDTO);
        }
        return orderDTO;
    }
}

