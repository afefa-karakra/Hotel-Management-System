package com.example.hotelmanagementsystem.Service.impl;

import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.DTO.ReservationDTO;
import com.example.hotelmanagementsystem.Entity.Customer;
import com.example.hotelmanagementsystem.Entity.Employee;
import com.example.hotelmanagementsystem.Entity.Reservation;
import com.example.hotelmanagementsystem.Entity.Room;
import com.example.hotelmanagementsystem.Exception.ResourceNotFoundException;
import com.example.hotelmanagementsystem.Repository.CustomerRepository;
import com.example.hotelmanagementsystem.Repository.ReservationRepository;
import com.example.hotelmanagementsystem.Repository.RoomRepository;
import com.example.hotelmanagementsystem.Service.Interface.ReservationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private CustomerRepository customerRepository;

    private RoomRepository roomRepository;


    public ReservationServiceImpl(ReservationRepository reservationRepository , CustomerRepository customerRepository , RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
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

    @Override
    public List<ReservationDTO> getReservationByCustomerName(String name) {
        List<Reservation> entityClasses = reservationRepository.getReservationByCustomerName(name);
        return entityClasses.stream()
                .map(reservation -> mapToDTO(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getReservationByCustomerID(long id) {
        List<Reservation> reservations = reservationRepository.getReservationByCustomerID(id);
        return reservations.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getReservationsByDate(Date date) {
        return null;
    }


    public ReservationDTO mapToDTO (Reservation reservation ){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setCheckOut(reservation.getCheckOut());
        reservationDTO.setCheckIn(reservation.getCheckIn());
        reservationDTO.setNumberOfCheckers(reservation.getNumberOfCheckers());
       reservationDTO.setReservationDate(reservation.getReservationDate());


        if (reservation.getCustomer() != null) {
            reservationDTO.setCustomerName(reservation.getCustomer().getName());
            reservationDTO.setCustomerId(reservation.getCustomer().getId());
        }

        if (reservation.getRoom() != null) {
            reservationDTO.setRoomId(reservation.getRoom().getId());
        }

//        if (reservation.getCustomer() != null) {
//
//        }

        return reservationDTO;
    }
    public Reservation mapToEntity (ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setCheckIn(reservationDTO.getCheckIn());
        reservation.setCheckOut(reservationDTO.getCheckOut());
        reservation.setNumberOfCheckers(reservationDTO.getNumberOfCheckers());
        reservation.setReservationDate(reservationDTO.getReservationDate());

        if (reservationDTO.getCustomerName() != null) {
            Customer customer = customerRepository.findById(reservationDTO.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", reservationDTO.getCustomerId()));
            reservation.setCustomer(customer);
        }

        if (reservationDTO.getCustomerId() != 0) {
            Customer customer = customerRepository.findById(reservationDTO.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", reservationDTO.getCustomerId()));
            reservation.setCustomer(customer);
        }

        Room room = roomRepository.findById(reservationDTO.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", reservationDTO.getRoomId()));
            reservation.setRoom(room);


        return reservation;

    }
}
