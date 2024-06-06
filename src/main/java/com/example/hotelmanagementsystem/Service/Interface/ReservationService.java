package com.example.hotelmanagementsystem.Service.Interface;

import com.example.hotelmanagementsystem.DTO.CustomerDTO;
import com.example.hotelmanagementsystem.DTO.ReservationDTO;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllReservation();
    ReservationDTO updateReservation(long id, ReservationDTO reservationDTO);

    void deleteReservation(long id);
}

