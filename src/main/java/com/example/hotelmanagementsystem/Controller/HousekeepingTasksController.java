package com.example.hotelmanagementsystem.Controller;

import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.DTO.HousekeepingTasksDTO;
import com.example.hotelmanagementsystem.Service.Interface.HousekeepingTasksService;
import io.swagger.annotations.ApiOperation;
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
    public ResponseEntity<HousekeepingTasksDTO> HousekeepingTasksById(@PathVariable long id, Authentication auth) {

        HousekeepingTasksDTO housekeepingTasksDTO = housekeepingTasksService.getHousekeepingTaskById(id);
        return ResponseEntity.ok(housekeepingTasksDTO);

    }

    @ApiOperation(value = "Get all HousekeepingTasks")
    @GetMapping
    public ResponseEntity<List<HousekeepingTasksDTO>> getAllHousekeepingTasks (){

        return ResponseEntity.ok().body(housekeepingTasksService.getAllHousekeepingTask());
    }

    @ApiOperation(value = "Create a new HousekeepingTasks")
    @PostMapping
    public ResponseEntity<HousekeepingTasksDTO> createHousekeepingTasksDTO(@RequestBody HousekeepingTasksDTO housekeepingTasksDTO) {

        return ResponseEntity.ok(housekeepingTasksService.createHousekeepingTask(housekeepingTasksDTO));
    }

    @ApiOperation(value = "Update HousekeepingTasks")
    @PutMapping("/{id}")
    public ResponseEntity<HousekeepingTasksDTO> updateHousekeepingTasksDTO(@PathVariable long id, @RequestBody HousekeepingTasksDTO housekeepingTasksDTO) {
        HousekeepingTasksDTO updatedHousekeepingTasks = housekeepingTasksService.updateHousekeepingTask(id , housekeepingTasksDTO);
        return ResponseEntity.ok(updatedHousekeepingTasks);
    }

    @ApiOperation(value = "Delete HousekeepingTasks by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHousekeepingTasks(@PathVariable(name = "id") long id){
        housekeepingTasksService.deleteHousekeepingTaskById(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
