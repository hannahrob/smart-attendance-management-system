package com.smartattendance.smart_attendance_system.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCourse {
    @NotBlank(message = "identifier is required.")
        private String identifier;
    @NotEmpty(message = "At least one course must be selected.")
    private List<CourseToRegister> courses;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseToRegister {

        @NotBlank(message = "Course code is required.")
        private String courseCode;      // e.g., CSC301



    }


}




