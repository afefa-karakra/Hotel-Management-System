package com.example.hotelmanagementsystem.Controller;

import com.example.hotelmanagementsystem.DTO.RoomDTO;
import com.example.hotelmanagementsystem.Service.Interface.CustomerService;
import com.example.hotelmanagementsystem.Service.Interface.RoomService;
import com.example.hotelmanagementsystem.Service.Interface.RoomServiceV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "RoomV2")
@RestController
@RequestMapping()
public class RoomControllerV2 {


    RoomServiceV2 roomServiceV2;

    public RoomControllerV2(RoomServiceV2 roomServiceV2) {
        this.roomServiceV2 = roomServiceV2;
    }


    @ApiOperation(value = "Get all Rooms v2")
    @GetMapping("/api/v2/room")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> rooms = roomServiceV2.getAllRoomV2();

        for (RoomDTO room : rooms) {
            room.setPrice(calculateRating(room.getId()));
        }
        return ResponseEntity.ok().body(rooms);
    }

    @GetMapping("/api/room")
    public ResponseEntity<List<RoomDTO>> getAllRoomsByQuery(
            @RequestParam(value = "version", defaultValue = "1") String version) {
        List<RoomDTO> rooms = roomServiceV2.getAllRoomV2();

        if ("2".equals(version)) {
            // Custom logic for version 2
            for (RoomDTO room : rooms) {
                room.setPrice(calculateRating(room.getId()));
            }
        }

        return ResponseEntity.ok().body(rooms);
    }

    @GetMapping("/api/rooms")
    public ResponseEntity<List<RoomDTO>> getAllRoomsByHeader (
            @RequestHeader(value = "API-Version", defaultValue = "1") String apiVersion) {
        List<RoomDTO> rooms = roomServiceV2.getAllRoomV2();

        if ("2".equals(apiVersion)) {
            for (RoomDTO room : rooms) {
                room.setPrice(calculateRating(room.getId()));
            }
        }
        return ResponseEntity.ok().body(rooms);
    }

    private double calculateRating(long id) {
        return 100.0;
    }
}
