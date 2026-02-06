package com.smartattendance.smart_attendance_system.dto;

import com.smartattendance.smart_attendance_system.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    @NotBlank(message = "Identifier is required.")
    private String identifier;

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;

    @NotNull(message = "Role is required.")
    private Role role;

    @NotNull(message = "Level is required.")
    @Min(value = 100, message = "Level must be at least 100")
    @Max(value = 600, message = "Level must not exceed 600")
    private Integer level;

    @NotBlank(message = "Department is required.")
    private String department;

    @NotBlank(message = "Device Id is required.")
    private String deviceId;
}

