package com.smartattendance.smart_attendance_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScanRequestDto {

        private Long sessionId;

        private String matricNo;

        private String totp;

        private Double latitude;

        private Double longitude;

        private String deviceId;

}
