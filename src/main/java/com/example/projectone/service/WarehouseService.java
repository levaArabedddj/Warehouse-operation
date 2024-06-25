package com.example.projectone.service;

import com.example.projectone.DTO.CategoryDTO;
import com.example.projectone.DTO.EmployeeDTO;
import com.example.projectone.DTO.ProductDTO;
import com.example.projectone.DTO.WarehouseDTO;
import com.example.projectone.Entity.Warehouse;
import com.example.projectone.Repository.WareHouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    @Autowired
    private WareHouseRepo warehouseRepository;

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long id, Warehouse warehouseDetails) throws Exception {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()) {
            Warehouse existingWarehouse = optionalWarehouse.get();
            existingWarehouse.setAdress(warehouseDetails.getAdress());
            existingWarehouse.setSquare(warehouseDetails.getSquare());
            existingWarehouse.setProducts(warehouseDetails.getProducts());
            existingWarehouse.setEmployeeList(warehouseDetails.getEmployeeList());
            return warehouseRepository.save(existingWarehouse);
        } else {
            throw new Exception("Warehouse with id " + id + " not found");
        }
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    public List<WarehouseDTO> getAllWarehouse(){
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream().map(this::convertToWarehouseDTO).collect(Collectors.toList());
    }

    public WarehouseDTO getWarehouseById(Long id) throws Exception {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new Exception("Supplier not found with id " + id));
        return convertToWarehouseDTO(warehouse);
    }

    private WarehouseDTO convertToWarehouseDTO(Warehouse warehouse){
        WarehouseDTO warehouseDTOs = new WarehouseDTO();
        warehouseDTOs.setId(warehouse.getId());
        warehouseDTOs.setAdress(warehouse.getAdress());
        warehouseDTOs.setSquare(warehouse.getSquare());


        List<EmployeeDTO> employeeDTOS = warehouse.getEmployeeList().
                stream()
                .map(employee -> {
                    EmployeeDTO employeeDTO = new EmployeeDTO();
                    employeeDTO.setId(employee.getId());
                    employeeDTO.setGmail(employee.getGmail());
                    employeeDTO.setRole(employee.getRole());
                    return employeeDTO;
                }).collect(Collectors.toList());
        warehouseDTOs.setEmployeeList(employeeDTOS);

        Set<ProductDTO> productDTOS = warehouse.getProducts().
                stream().
                map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(product.getId());
                    productDTO.setNameProduct(product.getNameProduct());
                    productDTO.setPrice(product.getPrice());
                    productDTO.setQuantity(product.getQuantity());
                    if(product.getCategory()!= null){
                        CategoryDTO categoryDTO = new CategoryDTO();
                        categoryDTO.setId(product.getCategory().getId());
                        categoryDTO.setNameCategory(product.getCategory().getNameCategory());
                        productDTO.setCategory(categoryDTO);
                    }
                    return productDTO;
                }).collect(Collectors.toSet());

        warehouseDTOs.setProducts(productDTOS);
        return warehouseDTOs;
    }
}

