package com.example.hotelmanagementsystem.Service.Interface;

import com.example.hotelmanagementsystem.DTO.EmployeeDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDTO getEmployeeById (long id);
    EmployeeDTO createEmployee (EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee (EmployeeDTO employeeDTO , long id);
    List<EmployeeDTO> getAllEmployee();
    void deleteEmployeeById (long id);
}
