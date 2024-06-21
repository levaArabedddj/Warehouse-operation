package com.example.projectone.service;

import com.example.projectone.Entity.Warehouse;
import com.example.projectone.Repository.WareHouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}

