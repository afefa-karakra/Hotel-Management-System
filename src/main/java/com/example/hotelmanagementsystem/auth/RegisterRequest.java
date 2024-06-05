package com.example.hotelmanagementsystem.auth;

import com.example.hotelmanagementsystem.user.Role;
import com.example.hotelmanagementsystem.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String name;
  private String email;
  private String password;
}
