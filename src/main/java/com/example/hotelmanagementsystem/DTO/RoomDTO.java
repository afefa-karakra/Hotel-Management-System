package com.example.hotelmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private long id;
    private String type;
    private boolean available;
    private String status;
    private double price;
    private String facilities;
    private int capacity;
    private double size;
}
