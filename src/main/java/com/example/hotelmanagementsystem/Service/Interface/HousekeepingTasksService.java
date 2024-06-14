package com.example.hotelmanagementsystem.Service.Interface;

import com.example.hotelmanagementsystem.DTO.HousekeepingTasksDTO;

import java.util.List;

public interface HousekeepingTasksService {

    HousekeepingTasksDTO getHousekeepingTaskById (long id);
    HousekeepingTasksDTO createHousekeepingTask (HousekeepingTasksDTO housekeepingDTO);
    HousekeepingTasksDTO updateHousekeepingTask ( long id , HousekeepingTasksDTO housekeepingDTO);
    List<HousekeepingTasksDTO> getAllHousekeepingTask();
    void deleteHousekeepingTaskById (long id);

}
