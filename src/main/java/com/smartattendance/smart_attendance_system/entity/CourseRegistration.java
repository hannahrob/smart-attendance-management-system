package com.smartattendance.smart_attendance_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "course_registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistration {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long courseRegistrationId;
    @Column(name = "courseCode")
        private String courseCode;
    @Column(name = "studentIdentifier")
        private String studentIdentifier;
    @Column(name = "registeredAt")
        private LocalDateTime registeredAt;

}
