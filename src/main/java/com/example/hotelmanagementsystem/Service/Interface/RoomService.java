package com.example.hotelmanagementsystem.Service.Interface;

import com.example.hotelmanagementsystem.DTO.CustomerDTO;
import com.example.hotelmanagementsystem.DTO.RoomDTO;
import com.example.hotelmanagementsystem.Entity.Room;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomService {

    List<RoomDTO> getAllRoom();
    RoomDTO getRoomById(long id);
    RoomDTO createRoom(RoomDTO roomDTO);
    RoomDTO updateRoom(long id, RoomDTO roomDTO);
    void deleteRoom(long id);

    List<RoomDTO> findAvailableRoom(boolean available);
}
