package com.example.hotelmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingDTO {

    private long id;
    private Date invoiceDate;
    private int totalAmount;
    private String status;

    private long reservationId;
}
