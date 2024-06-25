package com.example.projectone.RestControllers;


import com.example.projectone.DTO.WarehouseDTO;
import com.example.projectone.Entity.Warehouse;
import com.example.projectone.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/createWarehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(warehouseService.createWarehouse(warehouse));
    }
    @GetMapping("/getAllWarehouse")
    public List<WarehouseDTO> getAllWarehouse(){
        return warehouseService.getAllWarehouse();
    }

    @GetMapping("/getWarehouse")
    private WarehouseDTO getWarehouseById(@RequestParam Long id) throws Exception {
        return warehouseService.getWarehouseById(id);
    }

    @PutMapping("/updateWarehouse")
    public ResponseEntity<Warehouse> updateWarehouse(@RequestParam Long id,
                                                     @RequestBody Warehouse warehouseDetails) throws Exception {
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, warehouseDetails));
    }

    @DeleteMapping("/deleteWarehouse")
    public ResponseEntity<Void> deleteWarehouse(@RequestParam Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}