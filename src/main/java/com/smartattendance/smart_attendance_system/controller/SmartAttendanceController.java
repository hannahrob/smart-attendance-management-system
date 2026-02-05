package com.smartattendance.smart_attendance_system.controller;

import com.smartattendance.smart_attendance_system.dto.*;
//import com.smartattendance.smart_attendance_system.service.AttendanceService;
import com.smartattendance.smart_attendance_system.entity.User;
import com.smartattendance.smart_attendance_system.service.CourseService;
//import com.smartattendance.smart_attendance_system.service.QrCodeService;
import com.smartattendance.smart_attendance_system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("smart-attendance-system/api")
@RequiredArgsConstructor
@Slf4j
public class SmartAttendanceController {
        private final UserService userService;
        private final CourseService courseService;
//        private final AttendanceService attendanceService;
//        private final QrCodeService qrCodeService;

        // ========================= USER ENDPOINTS =========================
        @PostMapping("/register-user")
        public ResponseEntity<AttendanceResponse> registerUser(@Validated @RequestBody RegisterUserDto dto){
            return userService.registerUser(dto);
        }

        @PutMapping("/update-user")
        public ResponseEntity<AttendanceResponse> updateUser(@RequestBody UpdateUserDto dto){
            return userService.updateUser(dto);
        }

        @PostMapping("/login")
        public ResponseEntity<AttendanceResponse> login(@Validated @RequestBody LoginRequestDto dto){
            return userService.login(dto);
        }

    @GetMapping("/get-user/{identifier}")
    public ResponseEntity<AttendanceResponse> getUser(@PathVariable String identifier) {
        return userService.getUser(identifier);
    }

    // 🔹 Get all users
    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    // 🔹 Delete user
    @DeleteMapping("/delete-user/{identifier}")
    public ResponseEntity<AttendanceResponse> deleteUser(@PathVariable String identifier) {
        return userService.deleteUser(identifier);
    }

        // ========================= COURSE ENDPOINTS =========================
        @PostMapping("/create-course")
        public ResponseEntity<AttendanceResponse> createCourse(@Validated @RequestBody CreateCourseDto dto){
            return courseService.createCourse(dto);
        }

        @PutMapping("/update-course")
        public ResponseEntity<AttendanceResponse> updateCourse(@RequestBody UpdateCourseDto dto){
            return courseService.updateCourse(dto);
        }

        @GetMapping("/get-course/{courseId}")
        public ResponseEntity<AttendanceResponse> getCourseById(@PathVariable Long courseId){
            return courseService.getCourseByCourseId(courseId);
        }

        @GetMapping("/get-all-courses")
        public ResponseEntity<List<CreateCourseDto>> getAllCourses(){
            return courseService.getAllCourses();
        }

        @PostMapping("/register-course")
        public ResponseEntity<AttendanceResponse> registerCourse(@Validated @RequestBody RegisterCourse dto){
            return courseService.registerCourse(dto);
        }
    @DeleteMapping("/delete-course/{courseCode}")
    public ResponseEntity<AttendanceResponse> deleteCourse(@Validated @RequestBody String courseCode){
        return courseService.deleteCourseByCode(courseCode);
    }


        // ========================= ATTENDANCE SESSION ENDPOINTS =========================
//        @PostMapping("/start-session")
//        public ResponseEntity<QrResponseDto> startSession(@Validated @RequestBody StartSessionDto dto){
//            return attendanceService.startSession(dto);
//        }
//
//        @PostMapping("/scan-attendance")
//        public ResponseEntity<AttendanceResponse> scanAttendance(@Validated @RequestBody ScanRequestDto dto){
//            return attendanceService.scanAttendance(dto);
//        }
//
//        @GetMapping("/attendance-report/{studentIdentifier}")
//        public ResponseEntity<AttendanceReport> getAttendanceReport(@PathVariable String studentIdentifier){
//            return attendanceService.getAttendanceReport(studentIdentifier);
//        }
//
//        // ========================= QR CODE ENDPOINTS =========================
//        @GetMapping("/qr/{sessionId}")
//        public ResponseEntity<QrResponseDto> getQr(@PathVariable Long sessionId){
//            return qrCodeService.getQr(sessionId);
//        }

}

