package com.smartattendance.smart_attendance_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartSessionDto {
        private Long courseId;
        private Double latitude;
        private Double longitude;

}
