package com.example.hotelmanagementsystem.Repository;

import com.example.hotelmanagementsystem.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c.name FROM Customer c WHERE c.name = :customerName")
    List<Customer> findCustomerByName(@Param("customerName")String customerName);
}
