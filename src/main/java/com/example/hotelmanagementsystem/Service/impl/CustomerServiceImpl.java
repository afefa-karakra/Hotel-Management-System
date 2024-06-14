package com.example.hotelmanagementsystem.Service.impl;


import com.example.hotelmanagementsystem.DTO.CustomerDTO;
import com.example.hotelmanagementsystem.Entity.Customer;
import com.example.hotelmanagementsystem.Exception.ResourceNotFoundException;
import com.example.hotelmanagementsystem.Repository.CustomerRepository;
import com.example.hotelmanagementsystem.Service.Interface.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public CustomerDTO getCustomerById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer" , "id" , id));
        return convertToDTO(customer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        return convertToDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO updateCustomer(long id, CustomerDTO customerDTO) {
        customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer" , "id" , id));

        Customer customer = convertToEntity(customerDTO);
        customer.setId(id);
        return convertToDTO(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }



    @Override
    public List<CustomerDTO> findCustomerByName(String customerName) {
        List<Customer> customers = customerRepository.findCustomerByName(customerName);
        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    // Method to convert Customer to CustomerDTO
    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPassword(customer.getPassword());
        return customerDTO;
    }

    //convert CustomerDTO to Customer
    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        return customer;
    }
}