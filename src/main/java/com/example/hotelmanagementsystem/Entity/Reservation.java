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
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id ;

    @Column(name = "ReservationDate" , nullable = false)
    private Date ReservationDate;

    @Column(name = "checkIn" , nullable = false)
    private Date checkIn;

    @Column(name = "checkOut" , nullable = false)
    private Date checkOut;

    @Column(name = "numberOfCheckers" , nullable = false)
    private int numberOfCheckers;

    @ManyToOne
    @JoinColumn(name = "roomIdFK")
    private Room room;


    @ManyToOne
    @JoinColumn(name = "customerIdFK")
    private Customer customer;
}
