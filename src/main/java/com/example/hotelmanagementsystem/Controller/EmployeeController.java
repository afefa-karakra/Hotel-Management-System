package com.example.hotelmanagementsystem.Controller;

import com.example.hotelmanagementsystem.DTO.CustomerDTO;
import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.Exception.BadRequestException;
import com.example.hotelmanagementsystem.Service.Interface.EmployeeService;
import com.example.hotelmanagementsystem.Service.impl.EmployeeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(tags = "Employee")
@Tag(name = "Customers")
@RestController
@RequestMapping("/api/v1/Employee")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);


    @ApiOperation(value = "Get Employee by ID")
    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get Employee by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get Employee by id!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get Employee by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable long id, Authentication auth) {

        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDTO);

    }

    @ApiOperation(value = "Get all Employee")
    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get all Employee service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get all Employee!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get all Employee!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee (){

        return ResponseEntity.ok().body(employeeService.getAllEmployee());
    }


    @ApiOperation(value = "Create a new Employee")
    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create Employee service",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create Employee!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully create Employee!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {

        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }
    @ApiOperation(value = "Update Employee")
    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Update Employee info service",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successfully Update Employee info!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully Update Employee info!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeDTO , id);
        return ResponseEntity.ok(updatedEmployee);
    }

    @ApiOperation(value = "Delete Employee by ID")
    @DeleteMapping("/{id}")

    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Delete Employee service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully delete Employee!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully delete Employee!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<String> deleteEmployee (@PathVariable(name = "id") long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }

}
