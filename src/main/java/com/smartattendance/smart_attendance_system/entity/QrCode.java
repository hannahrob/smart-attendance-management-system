//package com.smartattendance.smart_attendance_system.entity;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity(name = "Qr_codes")
//
//public class QrCode {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        private String codeValue; // e.g., UUID or QR string
//
//        @ManyToOne
//        @JoinColumn(name = "class_id")
//        private Course relatedClass;
//}
//
//
