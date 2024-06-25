package com.example.projectone.service;


import com.example.projectone.DTO.*;
import com.example.projectone.Entity.Product;
import com.example.projectone.Repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product createProduct(Product product){
        productRepo.save(product);
        return product;
    }

    public List<Product> getAllProductInSite() {
        return productRepo.findAll();
    }



    public Product update(long id, Product product) throws Exception {
       Optional<Product>productOptional =  productRepo.findById(id);
       if (productOptional.isPresent()){
           Product existingProduct = productOptional.get();
           existingProduct.setNameProduct(product.getNameProduct());
           existingProduct.setCategory(product.getCategory());
           existingProduct.setPrice(product.getPrice());
           existingProduct.setQuantity(product.getQuantity());
           existingProduct.setSupplier(product.getSupplier());
           existingProduct.setOrders(product.getOrders());
           existingProduct.setWarehouseSet(product.getWarehouseSet());

           return productRepo.save(existingProduct);
       } else {
           throw new Exception("Product not found");
       }

    }
    public void deleteProduct(long id){
        productRepo.deleteById(id);
    }


    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(this::convertToProductDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) {
            return convertToProductDTO(productOptional.get());
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    private ProductDTO convertToProductDTO(Product product) {
        // Преобразование Category в CategoryDTO
        CategoryDTO categoryDTO = new CategoryDTO();
        if (product.getCategory() != null) {
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setNameCategory(product.getCategory().getNameCategory());
        }

        // Преобразование Supplier в SupplierDTO
        SupplierDTO supplierDTO = new SupplierDTO();
        if (product.getSupplier() != null) {
            supplierDTO.setId(product.getSupplier().getId());
            supplierDTO.setNameProduct(product.getSupplier().getNameProduct());
            supplierDTO.setNumberPhone(product.getSupplier().getNumberPhone());
        }

        // Преобразование Order в OrderDTO
        Set<OrderDTO> orderDTOs = product.getOrders().stream()
                .map(order -> {
                    ClientDTO clientDTO = new ClientDTO();
                    if (order.getClient() != null) {
                        clientDTO.setId(order.getClient().getId());
                        clientDTO.setName(order.getClient().getName());
                        clientDTO.setSurName(order.getClient().getSurName());
                        clientDTO.setPhoneNumber(order.getClient().getPhoneNumber());
                    }

                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setId(order.getId());
                    orderDTO.setDateCreatedOrders(order.getDateCreatedOrders());
                    orderDTO.setOrderStatus(order.getOrderStatus());
                    orderDTO.setClient(clientDTO);
                    orderDTO.setQuantityProduct(order.getQuantityProduct());
                    orderDTO.setPrice(order.getPrice());

                    return orderDTO;
                })
                .collect(Collectors.toSet());

        // Преобразование Warehouse в WarehouseDTO
        Set<WarehouseDTO> warehouseDTOs = product.getWarehouseSet().stream()
                .map(warehouse -> {
                    WarehouseDTO warehouseDTO = new WarehouseDTO();
                    warehouseDTO.setId(warehouse.getId());
                    warehouseDTO.setAdress(warehouse.getAdress());
                    warehouseDTO.setSquare(warehouse.getSquare());

                    return warehouseDTO;
                })
                .collect(Collectors.toSet());

        // Создание ProductDTO
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setNameProduct(product.getNameProduct());
        productDTO.setCategory(categoryDTO);
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setSupplier(supplierDTO);
        productDTO.setOrders(orderDTOs);
        productDTO.setWarehouseSet(warehouseDTOs);

        return productDTO;
    }
}
