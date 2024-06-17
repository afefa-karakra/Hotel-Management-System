package com.example.hotelmanagementsystem.Repository;

import com.example.hotelmanagementsystem.Entity.Billing;
import com.example.hotelmanagementsystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {
}
