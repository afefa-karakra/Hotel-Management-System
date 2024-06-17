package com.example.hotelmanagementsystem.Service.impl;

import com.example.hotelmanagementsystem.DTO.BillingDTO;
import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.DTO.ReservationDTO;
import com.example.hotelmanagementsystem.Entity.*;
import com.example.hotelmanagementsystem.Exception.ResourceNotFoundException;
import com.example.hotelmanagementsystem.Repository.BillingRepository;
import com.example.hotelmanagementsystem.Repository.ReservationRepository;
import com.example.hotelmanagementsystem.Service.Interface.BillingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    private BillingRepository billingRepository;
    private ReservationRepository reservationRepository;

    public BillingServiceImpl(BillingRepository billingRepository , ReservationRepository reservationRepository) {
        this.billingRepository = billingRepository;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public BillingDTO getBillingById(long id) {
        Billing billing = billingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Billing", "id", id));
        return mapToDTO(billing);
    }

    @Override
    public BillingDTO createBilling(BillingDTO billingDTO) {
        Billing billing = mapToEntity(billingDTO);
        Billing newBilling = billingRepository.save(billing);

        // convert entity to DTO
        BillingDTO billingResponse = mapToDTO(newBilling);
        return billingResponse;
    }

    @Override
    public BillingDTO updateBilling(BillingDTO billingDTO, long id) {

        Billing billing = billingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Billing", "id", id));
        billing.setId(id);
        billing.setInvoiceDate(billingDTO.getInvoiceDate());
        billing.setTotalAmount(billingDTO.getTotalAmount());
        billing.setStatus(billingDTO.getStatus());

      //  billing.setReservation(billingDTO.getReservationId());
        Billing updateBilling = billingRepository.save(billing);
        return mapToDTO(updateBilling);
    }

    @Override
    public List<BillingDTO> getAllBilling() {
        List<Billing> billings = billingRepository.findAll();
        return billings.stream().map(billing -> mapToDTO(billing))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBillingById(long id) {

        Billing billing = billingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Billing", "id", id));
        billingRepository.delete(billing);

    }

    public BillingDTO mapToDTO(Billing billing) {
        BillingDTO billingDTO = new BillingDTO();
        billingDTO.setId(billing.getId());
        billingDTO.setStatus(billing.getStatus());
        billingDTO.setInvoiceDate(billing.getInvoiceDate());
        billingDTO.setTotalAmount(billing.getTotalAmount());


        if (billing.getReservation() != null) {
            billingDTO.setReservationId(billing.getReservation().getId());
        }

        return billingDTO;
    }


    public Billing mapToEntity(BillingDTO billingDTO) {
        Billing billing = new Billing();
        billing.setId(billingDTO.getId());
        billing.setInvoiceDate(billingDTO.getInvoiceDate());
        billing.setTotalAmount(billingDTO.getTotalAmount());
        billing.setStatus(billingDTO.getStatus());

        Reservation reservation = reservationRepository.findById(billingDTO.getReservationId())
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", billingDTO.getReservationId()));
        billing.setReservation(reservation);

        return billing;

    }
}