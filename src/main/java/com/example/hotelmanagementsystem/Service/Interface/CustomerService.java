package com.example.hotelmanagementsystem.Service.Interface;

import com.example.hotelmanagementsystem.DTO.CustomerDTO;
import com.example.hotelmanagementsystem.Entity.Customer;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(long id);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(long id, CustomerDTO customerDTO);

    void deleteCustomer(long id);

    List<CustomerDTO> findCustomerByName(String customerName);

}
