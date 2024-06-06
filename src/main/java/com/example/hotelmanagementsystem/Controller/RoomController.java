package com.example.hotelmanagementsystem.Controller;


import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.DTO.RoomDTO;
import com.example.hotelmanagementsystem.Exception.BadRequestException;
import com.example.hotelmanagementsystem.Service.Interface.RoomService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @ApiOperation(value = "Get all Rooms")
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRoom (){

        return ResponseEntity.ok().body(roomService.getAllRoom());
    }
    @ApiOperation(value = "Get Room by ID")
    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@RequestParam long id){

        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @ApiOperation(value = "Create a new Room")
    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@Valid @RequestBody RoomDTO roomDTO) {

        if (roomDTO.getType() ==null) {
            log.error("Cannot have an type", roomDTO);
            throw new BadRequestException(EmployeeController.class.getSimpleName(),
                    "type");
        }

        return ResponseEntity.ok().body(roomService.createRoom(roomDTO));

    }

    @ApiOperation(value = "Update Room")
    @PutMapping ("/{id}")
    public ResponseEntity<RoomDTO> updateRoom
            (@RequestBody RoomDTO roomDTO
                    , @PathVariable(name = "id") long id) {

        return new ResponseEntity<>(roomService.updateRoom(id,roomDTO), HttpStatus.OK);
    }
}
