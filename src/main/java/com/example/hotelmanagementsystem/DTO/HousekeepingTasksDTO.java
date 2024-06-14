package com.example.hotelmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HousekeepingTasksDTO {
    private long id;
    private String taskDescription;
    private String taskStatus;
    private Date scheduledDate;
    private Date completedDate;

    private long roomId;
    private long employeeId;

}
