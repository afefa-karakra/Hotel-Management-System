package com.example.hotelmanagementsystem.Service.Interface;

import com.example.hotelmanagementsystem.DTO.BillingDTO;
import com.example.hotelmanagementsystem.DTO.EmployeeDTO;

import java.util.List;

public interface BillingService {

    BillingDTO getBillingById (long id);
    BillingDTO createBilling (BillingDTO billingDTO);
    BillingDTO updateBilling (BillingDTO billingDTO , long id);
    List<BillingDTO> getAllBilling();
    void deleteBillingById (long id);

}
