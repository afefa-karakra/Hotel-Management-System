package com.example.hotelmanagementsystem.Service.impl;

import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.DTO.ReservationDTO;
import com.example.hotelmanagementsystem.Entity.Employee;
import com.example.hotelmanagementsystem.Entity.Reservation;
import com.example.hotelmanagementsystem.Entity.Room;
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
        return reservationRepository.findAll().stream().map(reservation -> mapToDTO(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {

        Reservation reservation = mapToEntity(reservationDTO);
        Reservation newReservation = reservationRepository.save(reservation);

        ReservationDTO reservationResponse = mapToDTO(newReservation);
        return reservationResponse;
    }

    @Override
    public ReservationDTO updateReservation(long id, ReservationDTO reservationDTO) {
        reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation" , "id" , id));

        Reservation reservation = mapToEntity(reservationDTO);
        reservation.setId(id);
        Reservation updateReservation = reservationRepository.save(reservation);
        return mapToDTO(updateReservation);
    }

    @Override
    public void deleteReservation(long id) {

        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        reservationRepository.delete(reservation);

    }

    public ReservationDTO mapToDTO (Reservation reservation ){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setCheckOut(reservation.getCheckOut());
        reservationDTO.setCheckIn(reservation.getCheckIn());
        reservationDTO.setNumberOfCheckers(reservation.getNumberOfCheckers());
        reservationDTO.setCustomerName(reservation.getCustomer().getName());

        reservationDTO.setRoomId(reservation.getRoom().getId());
        reservationDTO.setCustomerId(reservation.getCustomer().getId());




        return reservationDTO;
    }
    public Reservation mapToEntity (ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setCheckIn(reservationDTO.getCheckIn());
        reservation.setCheckOut(reservationDTO.getCheckOut());
        reservation.setNumberOfCheckers(reservationDTO.getNumberOfCheckers());

      //  reservation.setCustomer(reservationDTO.getCustomerName());

        return reservation;

    }
}
