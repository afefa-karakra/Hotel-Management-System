package com.example.hotelmanagementsystem.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//hi hi
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

}
