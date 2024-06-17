package com.example.hotelmanagementsystem.Service.Interface;

import com.example.hotelmanagementsystem.DTO.CustomerDTO;
import com.example.hotelmanagementsystem.DTO.ReservationDTO;
import com.example.hotelmanagementsystem.Entity.Reservation;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllReservation();
    ReservationDTO createReservation (ReservationDTO reservationDTO);
    ReservationDTO updateReservation(long id, ReservationDTO reservationDTO);
    void deleteReservation(long id);
    List<ReservationDTO> getReservationByCustomerName(String name);
    List<ReservationDTO> getReservationByCustomerID(long id);

    List<ReservationDTO> getReservationsByDate(Date date);

}

