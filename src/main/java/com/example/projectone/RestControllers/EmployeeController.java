package com.example.projectone.RestControllers;

import com.example.projectone.DTO.EmployeeDTO;
import com.example.projectone.Entity.Employee;
import com.example.projectone.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @GetMapping("/getAllEmployee")
    public List<EmployeeDTO> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/getEmployee/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable long id) throws Exception {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestParam Long id, @RequestBody Employee employeeDetails) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDetails));
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<Void> deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

