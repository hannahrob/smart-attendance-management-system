package com.smartattendance.smart_attendance_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
        @NotNull(message = "level is required.")
        private Integer level;          // 300
        @NotNull(message = "Course unit is required.")
        private Integer courseUnit;     // 3
        @NotBlank(message = "department is required.")
        private String department;
           @NotBlank(message = "department is required.")
          private String semester;
        @NotBlank(message = "Lecturer Id is required.")
       private String lecturerIdentifier;   //lecturer id

}
