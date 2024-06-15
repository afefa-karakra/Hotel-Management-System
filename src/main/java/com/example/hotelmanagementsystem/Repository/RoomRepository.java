package com.example.hotelmanagementsystem.Repository;

import com.example.hotelmanagementsystem.Entity.Customer;
import com.example.hotelmanagementsystem.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.available = :available")
    List<Room> findAvailableRoom(@Param("available") boolean available);
}
