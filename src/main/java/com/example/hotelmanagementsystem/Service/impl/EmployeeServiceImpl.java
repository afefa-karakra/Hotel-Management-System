package com.example.hotelmanagementsystem.Service.impl;

import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.Entity.Employee;
import com.example.hotelmanagementsystem.Exception.ResourceNotFoundException;
import com.example.hotelmanagementsystem.Repository.EmployeeRepository;
import com.example.hotelmanagementsystem.Service.Interface.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl (EmployeeRepository employeeRepository ){this.employeeRepository = employeeRepository;
    }
    @Override
    public EmployeeDTO getEmployeeById(long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return mapToDTO(employee);
    }
    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> mapToDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        // convert DTO to entity
        Employee employee = mapToEntity(employeeDTO);
        Employee newEmployee = employeeRepository.save(employee);

        // convert entity to DTO
        EmployeeDTO emloyeeResponse = mapToDTO(newEmployee);
        return emloyeeResponse;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.setName(employeeDTO.getName());
        Employee updateEmployee = employeeRepository.save(employee);
        return mapToDTO(updateEmployee);
    }

    @Override
    public void deleteEmployeeById(long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);

    }

    private Employee mapToEntity (EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());

        return  employee;

    }

    private EmployeeDTO mapToDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPassword(employee.getPassword());

        return  employeeDTO;
    }

}
