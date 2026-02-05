package com.smartattendance.smart_attendance_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
        private Integer level;          // 300
        private Integer courseUnit;     // 3
        private String department;
        private String semester;
        private String lecturerIdentifier;   //lecturer id

}
