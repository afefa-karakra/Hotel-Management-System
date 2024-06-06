package com.example.hotelmanagementsystem.Service.impl;

import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.DTO.RoomDTO;
import com.example.hotelmanagementsystem.Entity.Employee;
import com.example.hotelmanagementsystem.Entity.Room;
import com.example.hotelmanagementsystem.Exception.ResourceNotFoundException;
import com.example.hotelmanagementsystem.Repository.RoomRepository;
import com.example.hotelmanagementsystem.Service.Interface.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> getAllRoom() {
        List<Room> room = roomRepository.findAll();
        return room.stream().map(employee -> mapToDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO getRoomById(long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        return mapToDTO(room);
    }

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = mapToEntity(roomDTO);
        Room newRoom = roomRepository.save(room);

        // convert entity to DTO
        RoomDTO roomResponse = mapToDTO(newRoom);
        return roomResponse;
    }

    @Override
    public RoomDTO updateRoom(long id, RoomDTO roomDTO) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        room.setId(id);
        room.setStatus(roomDTO.getStatus());
        room.setType(roomDTO.getType());
        Room updateRoom = roomRepository.save(room);
        return mapToDTO(updateRoom);
    }

    @Override
    public void deleteRoom(long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        roomRepository.delete(room);


    }

    private RoomDTO mapToDTO (Room room){

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setType(room.getType());
        roomDTO.setStatus(room.getStatus());
       // roomDTO.setAvailable(room.get);

        return roomDTO;

    }

    private Room mapToEntity (RoomDTO roomDTO){
        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setType(roomDTO.getType());
        room.setStatus(room.getStatus());

        return room;
    }
}
