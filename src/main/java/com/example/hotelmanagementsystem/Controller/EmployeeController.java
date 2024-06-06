package com.example.hotelmanagementsystem.Controller;

import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.Exception.BadRequestException;
import com.example.hotelmanagementsystem.Service.Interface.EmployeeService;
import com.example.hotelmanagementsystem.Service.impl.EmployeeServiceImpl;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);


    @ApiOperation(value = "Get Employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@RequestParam long id){

        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @ApiOperation(value = "Get all Employee")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee (){

        return ResponseEntity.ok().body(employeeService.getAllEmployee());
    }

    @ApiOperation(value = "Create a new Employee")
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee (@Valid @RequestBody EmployeeDTO employeeDTO) {

        if (employeeDTO.getName() ==null) {
            log.error("Cannot have an name {}", employeeDTO);
            throw new BadRequestException(EmployeeController.class.getSimpleName(),
                    "Name");
        }

        return ResponseEntity.ok().body(employeeService.createEmployee(employeeDTO));
        //return new ResponseEntity(employeeServiceInterface.createEmployee(employeeDTO), HttpStatus.CREATED);

    }
    @ApiOperation(value = "Update Employee")
    @PutMapping ("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee
            (@Valid @RequestBody EmployeeDTO employeeDTO
                    , @PathVariable(name = "id") long id) {

        return new ResponseEntity<>(employeeService.updateEmployee(employeeDTO, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Employee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee (@PathVariable(name = "id") long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }

}
