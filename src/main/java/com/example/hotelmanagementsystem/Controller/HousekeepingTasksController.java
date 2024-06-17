package com.example.hotelmanagementsystem.Controller;

import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.DTO.HousekeepingTasksDTO;
import com.example.hotelmanagementsystem.Service.Interface.HousekeepingTasksService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/HousekeepingTasks")
public class HousekeepingTasksController {

    private HousekeepingTasksService housekeepingTasksService;

    public HousekeepingTasksController(HousekeepingTasksService housekeepingTasksService) {
        this.housekeepingTasksService = housekeepingTasksService;
    }

    @ApiOperation(value = "Get HousekeepingTasks by ID")
    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get HousekeepingTasks by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get HousekeepingTasks by id!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get HousekeepingTasks by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<HousekeepingTasksDTO> HousekeepingTasksById(@PathVariable long id, Authentication auth) {

        HousekeepingTasksDTO housekeepingTasksDTO = housekeepingTasksService.getHousekeepingTaskById(id);
        return ResponseEntity.ok(housekeepingTasksDTO);

    }

    @ApiOperation(value = "Get all HousekeepingTasks")
    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get all HousekeepingTasks service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get all HousekeepingTasks!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get all HousekeepingTasks!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<HousekeepingTasksDTO>> getAllHousekeepingTasks (){

        return ResponseEntity.ok().body(housekeepingTasksService.getAllHousekeepingTask());
    }

    @ApiOperation(value = "Create a new HousekeepingTasks")
    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create HousekeepingTasks service",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create HousekeepingTasks!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully create HousekeepingTasks!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<HousekeepingTasksDTO> createHousekeepingTasksDTO(@RequestBody HousekeepingTasksDTO housekeepingTasksDTO) {

        return ResponseEntity.ok(housekeepingTasksService.createHousekeepingTask(housekeepingTasksDTO));
    }

    @ApiOperation(value = "Update HousekeepingTasks")
    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Update HousekeepingTasks info service",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successfully Update HousekeepingTasks info!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully Update HousekeepingTasks info!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<HousekeepingTasksDTO> updateHousekeepingTasksDTO(@PathVariable long id, @RequestBody HousekeepingTasksDTO housekeepingTasksDTO) {
        HousekeepingTasksDTO updatedHousekeepingTasks = housekeepingTasksService.updateHousekeepingTask(id , housekeepingTasksDTO);
        return ResponseEntity.ok(updatedHousekeepingTasks);
    }

    @ApiOperation(value = "Delete HousekeepingTasks by ID")
    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Delete HousekeepingTasks service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully delete HousekeepingTasks!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully delete HousekeepingTasks!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<String> deleteHousekeepingTasks(@PathVariable(name = "id") long id){
        housekeepingTasksService.deleteHousekeepingTaskById(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
