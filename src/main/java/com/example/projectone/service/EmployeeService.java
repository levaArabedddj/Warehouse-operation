package com.example.projectone.service;

import com.example.projectone.DTO.EmployeeDTO;
import com.example.projectone.DTO.WarehouseDTO;
import com.example.projectone.Entity.Employee;
import com.example.projectone.Repository.EmployeeRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @SneakyThrows
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setGmail(employeeDetails.getGmail());
            existingEmployee.setWarehouse(employeeDetails.getWarehouse());
            existingEmployee.setRole(employeeDetails.getRole());
            return employeeRepository.save(existingEmployee);
        } else {
            throw new Exception("Employee with id " + id + " not found");
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> employee = employeeRepository.findAll();
        return employee.stream().map(this::convertToEmployeeDTO).collect(Collectors.toList());
    }
    public EmployeeDTO getEmployeeById(long id) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new Exception("Employee not found") );
        return convertToEmployeeDTO(employee);
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setGmail(employee.getGmail());
        employeeDTO.setRole(employee.getRole());

        WarehouseDTO warehouseDTO = new WarehouseDTO();
        if(employee.getWarehouse()!= null){
            warehouseDTO.setId(employee.getWarehouse().getId());
            warehouseDTO.setAdress(employee.getWarehouse().getAdress());
            warehouseDTO.setSquare(employee.getWarehouse().getSquare());
        }
        employeeDTO.setWarehouse(warehouseDTO);
        return employeeDTO;
    }


}

