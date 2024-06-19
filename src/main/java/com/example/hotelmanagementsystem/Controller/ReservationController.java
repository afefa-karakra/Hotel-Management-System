package com.example.hotelmanagementsystem.Controller;

import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.DTO.ReservationDTO;
import com.example.hotelmanagementsystem.Service.Interface.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Api(tags = "Reservation")
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
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Update reservation info service",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successfully Update reservation info!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully Update reservation info!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<ReservationDTO> updateReservation (@PathVariable long id, @RequestBody ReservationDTO reservationDTO){
        return ResponseEntity.ok(reservationService.updateReservation(id,reservationDTO));
     }

    @ApiOperation(value = "Delete Reservation by ID")
    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Delete Reservation service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully Reservation customer!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully delete Reservation!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    //        reservationService.deleteReservation(id);
//        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    public ResponseEntity<String> deleteReservation (@PathVariable long id){


        try {
            reservationService.deleteReservation(id);
            return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            // Log the error and return a meaningful response
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @ApiOperation(value = "Get all Reservation")
    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get all Reservation service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get all Reservation!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get all Reservation!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<ReservationDTO>> getAllReservation(){
        return ResponseEntity.ok().body(reservationService.getAllReservation());
    }

    @ApiOperation(value = "Create a new reservation")
    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create reservation service",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create reservation!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully create reservation!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {

        return ResponseEntity.ok(reservationService.createReservation(reservationDTO));
    }

    @GetMapping("/name")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "find Reservation By Customer Name",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully find Reservation By Customer Name",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully find Reservation By Customer Name\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<ReservationDTO>> getReservationByCustomerName(@RequestParam("name")String name ){
        List<ReservationDTO> reservations = reservationService.getReservationByCustomerName(name);
        return ResponseEntity.ok().body(reservations);
    }

    @GetMapping("/CustomerID")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "find Reservation By Customer id",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully find Reservation By Customer id",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully find Reservation By Customer id\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<ReservationDTO>> getReservationByCustomerID(@RequestParam("id") long id) {
        List<ReservationDTO> reservations = reservationService.getReservationByCustomerID(id);
        return ResponseEntity.ok(reservations);
    }




    @GetMapping("/ReservationDate")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "find Reservation By  Date",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully find Reservation By Date",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully find Reservation By Date\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<ReservationDTO>> getReservationByDate(@RequestParam("ReservationDate") Date date) {
        List<ReservationDTO> reservations = reservationService.getReservationsByDate(date);
        return ResponseEntity.ok(reservations);
    }
}
