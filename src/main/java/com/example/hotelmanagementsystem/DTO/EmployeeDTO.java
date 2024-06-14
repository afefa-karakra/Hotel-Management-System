package com.example.hotelmanagementsystem.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private long id;
    private String name;
    private String email;
   // @JsonIgnore
    private String password;
}
