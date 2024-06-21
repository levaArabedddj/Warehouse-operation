package com.example.projectone.Controllers;


import com.example.projectone.Entity.Warehouse;
import com.example.projectone.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/createWarehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(warehouseService.createWarehouse(warehouse));
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