package com.example.hotelmanagementsystem.Service.impl;

import com.example.hotelmanagementsystem.DTO.ReservationDTO;
import com.example.hotelmanagementsystem.Entity.Reservation;
import com.example.hotelmanagementsystem.Exception.ResourceNotFoundException;
import com.example.hotelmanagementsystem.Repository.ReservationRepository;
import com.example.hotelmanagementsystem.Service.Interface.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ReservationDTO> getAllReservation() {
        return reservationRepository.findAll().stream().map(reservation -> ConvertToDTO(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO updateReservation(long id, ReservationDTO reservationDTO) {
        reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation" , "id" , id));

        Reservation reservation = ConvertToEntity(reservationDTO);
        reservation.setId(id);
        Reservation updateReservation = reservationRepository.save(reservation);
        return ConvertToDTO(updateReservation);
    }

    @Override
    public void deleteReservation(long id) {

    }

    public ReservationDTO ConvertToDTO (Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setCheckOut(reservation.getCheckOut());
        reservationDTO.setCheckIn(reservation.getCheckIn());
        reservationDTO.setNumberOfCheckers(reservation.getNumberOfCheckers());

        return reservationDTO;
    }
    public Reservation ConvertToEntity (ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setCheckIn(reservationDTO.getCheckIn());
        reservation.setCheckOut(reservationDTO.getCheckOut());
        reservation.setNumberOfCheckers(reservationDTO.getNumberOfCheckers());

        return reservation;

    }
}
