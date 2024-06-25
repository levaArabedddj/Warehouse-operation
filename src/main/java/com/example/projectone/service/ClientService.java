package com.example.projectone.service;

import com.example.projectone.DTO.ClientDTO;
import com.example.projectone.DTO.OrderDTO;
import com.example.projectone.DTO.ProductDTO;
import com.example.projectone.Entity.Client;
import com.example.projectone.Repository.ClientRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @SneakyThrows
    public Client updateClient(Long id, Client clientDetails) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();
            existingClient.setName(clientDetails.getName());
            existingClient.setSurName(clientDetails.getSurName());
            existingClient.setPhoneNumber(clientDetails.getPhoneNumber());
            existingClient.setOrders(clientDetails.getOrders());
            return clientRepository.save(existingClient);
        } else {
            throw new Exception("Client with id " + id + " not found");
        }
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    // Получение всех клиентов
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(this::convertToClientDTO).collect(Collectors.toList());
    }

    // Получение одного клиента по ID
    public ClientDTO getClientById(Long id) throws Exception {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new Exception("Client not found with id " + id));
        return convertToClientDTO(client);
    }

    private ClientDTO convertToClientDTO(Client client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setSurName(client.getSurName());
        clientDTO.setPhoneNumber(client.getPhoneNumber());

        List<OrderDTO> dtoList = client.getOrders() != null ?
                client.getOrders().stream().map(order -> {
                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setId(order.getId());
                    orderDTO.setDateCreatedOrders(order.getDateCreatedOrders());
                    orderDTO.setOrderStatus(order.getOrderStatus());
                    orderDTO.setQuantityProduct(order.getQuantityProduct());
                    orderDTO.setPrice(order.getPrice());

                    // Преобразуем список продуктов в заказе
                    Set<ProductDTO> productDTOList = order.getProducts() != null ?
                            order.getProducts().stream().map(product -> {
                                ProductDTO productDTO = new ProductDTO();
                                productDTO.setId(product.getId());
                                productDTO.setNameProduct(product.getNameProduct());
                                productDTO.setPrice(product.getPrice());
                                productDTO.setQuantity(product.getQuantity());
                                // Заполните другие поля ProductDTO по необходимости
                                return productDTO;
                            }).collect(Collectors.toSet()) : new HashSet<>();

                    // Устанавливаем список продуктов в OrderDTO
                    orderDTO.setProducts(productDTOList);
                    return orderDTO;
                }).collect(Collectors.toList()) : new ArrayList<>();

         clientDTO.setOrders(dtoList);
         return clientDTO;
    }
}

