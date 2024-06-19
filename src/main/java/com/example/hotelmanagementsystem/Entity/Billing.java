package com.example.hotelmanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Billing")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "invoiceDate", nullable = false)
    private Date invoiceDate;

    @Column(name = "totalAmount", nullable = false)
    private int totalAmount;

    @Column(name = "status", nullable = false)
    private String status;  // e.g., PAID, UNPAID, CANCELLED

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_idFK", nullable = false)
    private Reservation reservation;


}
