package com.smartattendance.smart_attendance_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCourse {
    @NotBlank(message = "identifier is required.")
        private String identifier;
    @NotBlank(message = "course id is required.")
        private Long courseId;

}
