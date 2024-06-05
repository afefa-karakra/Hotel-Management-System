package com.example.hotelmanagementsystem.Controller;


import com.example.hotelmanagementsystem.DTO.CustomerDTO;
import com.example.hotelmanagementsystem.Service.Interface.CustomerService;
import com.example.hotelmanagementsystem.Entity.User;
//import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.hotelmanagementsystem.user.Role.ADMIN;
// This class is a RestController which means it's a controller with @ResponseBody added to all methods by default.
// The @RestController annotation informs Spring that this class is a controller that should process HTTP requests
// The @RequestMapping("/api/v1/customers") annotation maps this controller to the specified URL path,
// which means that any requests starting with /api/v1/customers will be handled by methods in this controller
//@Api(tags = "Customers")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    // The @Autowired annotation tells Spring to inject the CustomerService into this controller
    // When the controller is created, Spring will create an instance of the CustomerService and assign it to this field
    @Autowired
    private CustomerService customerService;
    // This method returns all the customers.
    // The @GetMapping annotation informs that this method should respond to HTTP GET requests
    // It uses the customer service to fetch all the customers and return them

    @ApiOperation(value = "Get all customers")
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        // Calling the getAllCustomers method of the customerService
        List<CustomerDTO> cs=customerService.getAllCustomers();
//        for ( CustomerDTO c: cs ) {
//            // Linking each customer with their respective orders
//            Link ordersLink = linkTo(methodOn(OrderController.class)
//                    .getAllOrdersByCustomer(c.getId()))
//                    .withRel("customerOrders");
//            c.add(ordersLink);
//        }
        return ResponseEntity.ok(cs);
    }
    // This method fetches a specific customer by their id.
    // The @PathVariable annotation is used to bind the id parameter from the URL to the method's id parameter
    // If the requested customer id exists, the method returns the customer, otherwise it returns HTTP status code 404
    @ApiOperation(value = "Get customer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id, Authentication auth) {
//        UserDetails userDetails = (UserDetails) auth.getPrincipal();
//        User user = (User) userDetails;
//        System.out.println(user);
//        Integer authCustomerId = user.getCustomerId();


        //  boolean isAdmin = auth.getAuthorities().stream()
        //          .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ADMIN.name() ));

        //   if(isAdmin || authCustomerId==id) {

        CustomerDTO customerDTO = customerService.getCustomerById(id);
//        Link ordersLink = linkTo(methodOn(OrderController.class)
//                .getAllOrdersByCustomer(customerDTO.getId()))
//                .withRel("customerOrders");
//        customerDTO.add(ordersLink);
        return ResponseEntity.ok(customerDTO);
        // }else{  return  new ResponseEntity("UnAuthorized",HttpStatus.UNAUTHORIZED);}
    }
    // This method creates a new customer.
    // It receives the data for the new customer as a JSON in the body of the request, and Spring automatically converts it into a CustomerDTO object
    // The @PostMapping annotation informs that this method should respond to HTTP POST requests
    @ApiOperation(value = "Create a new customer")
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {

        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }
    // This method updates a specific customer.
    // The @PutMapping annotation informs that this method should respond to HTTP PUT requests

    @ApiOperation(value = "Update customer")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        // Calling the updateCustomer method of the customerService with the id of the customer to update and the new customer data
        // The updated customer is returned
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
    }
// This method deletes a specific customer.
// The @DeleteMapping annotation informs that this method should respond to HTTP DELETE requests

    @ApiOperation(value = "Delete customer by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        // Calling the deleteCustomer method of the customerService with the id of the customer to delete
        // After successful deletion, it returns an HTTP 200 status code without body content

        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}