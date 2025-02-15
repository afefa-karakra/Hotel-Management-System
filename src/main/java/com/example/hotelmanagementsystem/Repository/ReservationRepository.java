package com.example.hotelmanagementsystem.Repository;

import com.example.hotelmanagementsystem.Entity.Customer;
import com.example.hotelmanagementsystem.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("SELECT r FROM Reservation r JOIN Customer c ON r.customer.id = c.id WHERE c.name LIKE  %:customerName% ")
      List<Reservation> getReservationByCustomerName (@Param("customerName")String customerName);


    @Query("SELECT r FROM Reservation r WHERE r.customer.id = :id")
    List<Reservation> getReservationByCustomerID(@Param("id") long id);

    @Query("SELECT r FROM Reservation r WHERE r.ReservationDate = :ReservationDate")
    List<Reservation> getReservationByDate(@Param("ReservationDate") Date ReservationDate);


    void deleteById(Long id);

}
