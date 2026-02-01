package com.smartattendance.smart_attendance_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseDto {
        @NotBlank(message = "Course code is required.")
        private String courseCode;
        private String courseTitle;
        private String lecturerIdentifier;   //lecturer id

}
