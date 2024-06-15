package com.example.hotelmanagementsystem.Controller;

import com.example.hotelmanagementsystem.DTO.ReservationDTO;
import com.example.hotelmanagementsystem.Service.Interface.ReservationService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    private final Logger log = LoggerFactory.getLogger(ReservationController.class);

    @ApiOperation(value = "Update reservation")
     @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation (@PathVariable long id, @RequestBody ReservationDTO reservationDTO){
        return ResponseEntity.ok(reservationService.updateReservation(id,reservationDTO));
     }

    @ApiOperation(value = "Delete Reservation by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation (@PathVariable long id){
        reservationService.deleteReservation(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }

    @ApiOperation(value = "Get all Reservation")
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservation(){
        return ResponseEntity.ok().body(reservationService.getAllReservation());
    }

    @GetMapping("/name")
    public ResponseEntity<List<ReservationDTO>> getReservationByCustomerName(@RequestParam("name")String name ){
        List<ReservationDTO> reservations = reservationService.getReservationByCustomerName(name);
        return ResponseEntity.ok().body(reservations);
    }

    @GetMapping("/CustomerID")
    public ResponseEntity<List<ReservationDTO>> getReservationByCustomerID(@RequestParam("id") long id) {
        List<ReservationDTO> reservations = reservationService.getReservationByCustomerID(id);
        return ResponseEntity.ok(reservations);
    }
}
