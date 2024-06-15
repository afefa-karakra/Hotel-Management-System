package com.example.hotelmanagementsystem.Controller;


import com.example.hotelmanagementsystem.DTO.CustomerDTO;
import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.DTO.RoomDTO;
import com.example.hotelmanagementsystem.Exception.BadRequestException;
import com.example.hotelmanagementsystem.Service.Interface.CustomerService;
import com.example.hotelmanagementsystem.Service.Interface.RoomService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    RoomService roomService;
    CustomerService customerService;

    public RoomController(RoomService roomService , CustomerService customerService) {
        this.roomService = roomService;
        this.customerService = customerService;
    }

    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @ApiOperation(value = "Get all Rooms")
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRoom (){

        return ResponseEntity.ok().body(roomService.getAllRoom());
    }
    @ApiOperation(value = "Get Room by ID")
    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getCustomerById(@PathVariable long id, Authentication auth) {

        RoomDTO roomDTO = roomService.getRoomById(id);
        return ResponseEntity.ok(roomDTO);

    }

    @ApiOperation(value = "Create a new Room")
    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@Valid @RequestBody RoomDTO roomDTO) {

//        if (roomDTO.getType() ==null) {
//            log.error("Cannot have an type", roomDTO);
//            throw new BadRequestException(EmployeeController.class.getSimpleName(),
//                    "type");
//        }

        return ResponseEntity.ok().body(roomService.createRoom(roomDTO));

    }

    @ApiOperation(value = "Update Room")
    @PutMapping ("/{id}")
    public ResponseEntity<RoomDTO> updateRoom
            (@RequestBody RoomDTO roomDTO
                    , @PathVariable(name = "id") long id) {

        return new ResponseEntity<>(roomService.updateRoom(id,roomDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Room by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable(name = "id") long id){
        roomService.deleteRoom(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomDTO>> findAvailableRoom(@RequestParam boolean available) {
        List<RoomDTO> availableRooms = roomService.findAvailableRoom(available);
        return ResponseEntity.ok(availableRooms);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable long id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);

        return ResponseEntity.ok("Customer ID: " + id);
    }
}
