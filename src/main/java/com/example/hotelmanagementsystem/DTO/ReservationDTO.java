package com.example.hotelmanagementsystem.DTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private long id ;
    private Date checkIn;
    private Date checkOut;
    private int numberOfCheckers;


}
