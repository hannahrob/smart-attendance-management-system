package com.smartattendance.smart_attendance_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
        @NotBlank(message = "Identifier is required.")
        private String identifier;
        @NotBlank(message = "password is required.")
        private String password;
}

