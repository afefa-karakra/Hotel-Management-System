package com.example.hotelmanagementsystem.Repository;

import com.example.hotelmanagementsystem.Entity.Customer;
import com.example.hotelmanagementsystem.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("SELECT r.numberOfCheckers FROM Reservation r JOIN Customer c ON r.customer.id = c.id WHERE c.name LIKE  %:customerName% ")
      List<Reservation> getReservationByCustomerName (@Param("customerName")String customerName);


}
