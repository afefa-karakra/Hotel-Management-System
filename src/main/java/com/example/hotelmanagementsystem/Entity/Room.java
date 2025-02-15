package com.example.hotelmanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "type" , nullable = false)
    private String type;

    @Column(name = "available" , nullable = false)
    private boolean available;

    @Column(name = "status" , nullable = false)
    private String status;

    @Column(name = "price" , nullable = false)
    private double price;

    @Column(name = "facilities" , nullable = false)
    private String facilities;

    @Column(name = "capacity" , nullable = false)
    private int capacity;

    @Column(name = "size" , nullable = false)
    private double size;
}
