package com.example.hotelmanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Housekeeping")
public class HousekeepingTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "taskDescription" , nullable = false)
    private String taskDescription;

    @Column(name = "taskStatus")
    private String taskStatus;

    @Column(name = "scheduledDate" , nullable = false)
    private Date scheduledDate;

    @Column(name = "completedDate" , nullable = false)
    private Date completedDate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomIdFK")
    private Room room;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeIdFK")
    private Employee employee;
}
