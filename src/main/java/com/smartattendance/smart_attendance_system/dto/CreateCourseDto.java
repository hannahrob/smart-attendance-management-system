package com.smartattendance.smart_attendance_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseDto {
        @NotBlank(message = "Course code is required.")
        private String courseCode;
        @NotBlank(message = "Course title is required.")
        private String courseTitle;
        @NotBlank(message = "Lecturer Id is required.")
       private String lecturerIdentifier;   //lecturer id

}
