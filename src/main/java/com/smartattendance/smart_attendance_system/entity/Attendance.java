//package com.smartattendance.smart_attendance_system.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity(name = "attendance")
//public class Attendance {
//
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        private LocalDateTime timestamp;
//
//        private boolean present;
//
//        @ManyToOne
//        @JoinColumn(name = "student_id")
//        private User student; // only users with role = STUDENT
//
//        @ManyToOne
//        @JoinColumn(name = "class_id")
//        private Course studentClass;
//}
//
//
