package com.smartattendance.smart_attendance_system.dto;

import com.smartattendance.smart_attendance_system.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    @NotBlank(message = "Identifier is required.")
        private String identifier;   // Matric number for students, Staff ID for lecturers/admins
    @NotBlank(message = "First name is required.")
        private String firstName;
    @NotBlank(message = "Last name is required.")
    private String lastName;
    @NotBlank(message = "Email is required.")
        private String email;
    @NotBlank(message = "Password is required.")
        private String password;
    @NotNull(message = "Role is required.")
        private Role role;
    @NotBlank(message = "level is required.")
    private Integer level;
    @NotBlank(message = "department is required.")
    private String department;
    @NotBlank(message = "Device Id is required.")
        private String deviceId;

}
