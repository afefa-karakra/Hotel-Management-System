package com.example.hotelmanagementsystem.Service.impl;

import com.example.hotelmanagementsystem.DTO.HousekeepingTasksDTO;
import com.example.hotelmanagementsystem.Entity.Employee;
import com.example.hotelmanagementsystem.Entity.HousekeepingTasks;
import com.example.hotelmanagementsystem.Entity.Reservation;
import com.example.hotelmanagementsystem.Entity.Room;
import com.example.hotelmanagementsystem.Exception.ResourceNotFoundException;
import com.example.hotelmanagementsystem.Repository.EmployeeRepository;
import com.example.hotelmanagementsystem.Repository.HousekeepingTasksRepository;
import com.example.hotelmanagementsystem.Repository.RoomRepository;
import com.example.hotelmanagementsystem.Service.Interface.HousekeepingTasksService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HousekeepingTasksServiceImpl implements HousekeepingTasksService {

    private RoomRepository roomRepository;
    private EmployeeRepository employeeRepository;
    private HousekeepingTasksRepository housekeepingRepository;


    public HousekeepingTasksServiceImpl(RoomRepository roomRepository , EmployeeRepository employeeRepository , HousekeepingTasksRepository housekeepingRepository  ) {
        this.roomRepository = roomRepository;
        this.housekeepingRepository = housekeepingRepository;
        this.employeeRepository = employeeRepository;
    }
    @Override
    public HousekeepingTasksDTO getHousekeepingTaskById(long id) {
        HousekeepingTasks housekeeping = housekeepingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Housekeeping", "id", id));
        return mapToDTO(housekeeping);
    }

    @Override
    public HousekeepingTasksDTO createHousekeepingTask(HousekeepingTasksDTO housekeepingDTO) {
        HousekeepingTasks housekeeping = mapToEntity(housekeepingDTO);
        HousekeepingTasks newHousekeeping = housekeepingRepository.save(housekeeping);

        // convert entity to DTO
        HousekeepingTasksDTO housekeepingResponse = mapToDTO(newHousekeeping);
        return housekeepingResponse;
    }

    @Override
    public HousekeepingTasksDTO updateHousekeepingTask( long id ,HousekeepingTasksDTO housekeepingDTO) {
        housekeepingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation" , "id" , id));

        HousekeepingTasks housekeepingTasks = mapToEntity(housekeepingDTO);
        housekeepingTasks.setId(id);
        HousekeepingTasks updateHousekeepingTasks = housekeepingRepository.save(housekeepingTasks);
        return mapToDTO(updateHousekeepingTasks);
    }

    @Override
    public List<HousekeepingTasksDTO> getAllHousekeepingTask() {
        List<HousekeepingTasks> housekeeping = housekeepingRepository.findAll();
        return housekeeping.stream().map(housekeepings -> mapToDTO(housekeepings))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHousekeepingTaskById(long id) {
        HousekeepingTasks housekeepingTasks = housekeepingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        housekeepingRepository.delete(housekeepingTasks);
    }


    private HousekeepingTasksDTO mapToDTO(HousekeepingTasks housekeeping){
        HousekeepingTasksDTO housekeepingDTO = new HousekeepingTasksDTO();
        housekeepingDTO.setId(housekeeping.getId());
        housekeepingDTO.setCompletedDate(housekeeping.getCompletedDate());
        housekeepingDTO.setScheduledDate(housekeeping.getScheduledDate());
        housekeepingDTO.setTaskStatus(housekeeping.getTaskStatus());
        housekeepingDTO.setTaskDescription(housekeeping.getTaskDescription());


        if (housekeeping.getRoom() != null) {
            housekeepingDTO.setRoomId(housekeeping.getRoom().getId());
        }

        if (housekeeping.getEmployee() != null) {
            housekeepingDTO.setEmployeeId(housekeeping.getEmployee().getId());
        }
        return housekeepingDTO;
    }

    private HousekeepingTasks mapToEntity (HousekeepingTasksDTO housekeepingDTO){
        HousekeepingTasks housekeeping = new HousekeepingTasks();
        housekeeping.setId(housekeepingDTO.getId());
        housekeeping.setCompletedDate(housekeepingDTO.getCompletedDate());
        housekeeping.setTaskStatus(housekeepingDTO.getTaskStatus());
        housekeeping.setScheduledDate(housekeepingDTO.getScheduledDate());
        housekeeping.setTaskDescription(housekeepingDTO.getTaskDescription());

        Employee employee = employeeRepository.findById(housekeepingDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", housekeepingDTO.getEmployeeId()));
        housekeeping.setEmployee(employee);

        Room room = roomRepository.findById(housekeepingDTO.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", housekeepingDTO.getRoomId()));
        housekeeping.setRoom(room);



        return  housekeeping;

    }



}
