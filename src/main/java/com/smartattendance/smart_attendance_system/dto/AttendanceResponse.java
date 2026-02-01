package com.smartattendance.smart_attendance_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponse {
        private String responseCode;
        private String message;
        private LocalDateTime time;
        private Object responseDetails;

        public AttendanceResponse(String responseCode, String message, LocalDateTime time){
                this.responseCode = responseCode;
                this.message = message;
                this.time = time;
                this.responseDetails = null;
        }

}
