package com.example.hotelmanagementsystem.Repository;

import com.example.hotelmanagementsystem.Entity.Billing;
import com.example.hotelmanagementsystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Billing b WHERE b.reservation.id = :reservationId")
    void deleteByReservationId(@Param("reservationId") Long reservationId);

}
