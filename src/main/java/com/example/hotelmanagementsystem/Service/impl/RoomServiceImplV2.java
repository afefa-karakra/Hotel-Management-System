package com.example.hotelmanagementsystem.Service.impl;
import com.example.hotelmanagementsystem.DTO.RoomDTO;
import com.example.hotelmanagementsystem.Entity.Room;
import com.example.hotelmanagementsystem.Repository.RoomRepository;
import com.example.hotelmanagementsystem.Service.Interface.RoomServiceV2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class RoomServiceImplV2 implements RoomServiceV2 {

    RoomRepository roomRepository;

    public RoomServiceImplV2(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> getAllRoomV2() {
        List<Room> room = roomRepository.findAll();
        return room.stream().map(rooms -> mapToDTO(rooms))
                .collect(Collectors.toList());
    }


    private RoomDTO mapToDTO (Room room){

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setType(room.getType());
        roomDTO.setStatus(room.getStatus());
        roomDTO.setSize(room.getSize());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setFacilities(room.getFacilities());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setAvailable(room.isAvailable());

        return roomDTO;

    }



}
