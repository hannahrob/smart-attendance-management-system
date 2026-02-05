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
public class UpdateUserDto {
        @NotBlank(message = "Identifier is required.")
        private String identifier;   // Matric number for students, Staff ID for lecturers/admins
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Role role;
    private Integer level;
    private String department;
        private String deviceId;

}
