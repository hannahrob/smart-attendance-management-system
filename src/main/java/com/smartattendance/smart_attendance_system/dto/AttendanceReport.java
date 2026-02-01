package com.smartattendance.smart_attendance_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceReport {
        private String studentIdentifier;
        private String courseCode;
        private int totalClasses;
        private int attended;
}
