package com.smartattendance.smart_attendance_system.service;

import com.smartattendance.smart_attendance_system.dto.AttendanceResponse;
import com.smartattendance.smart_attendance_system.dto.CreateCourseDto;
import com.smartattendance.smart_attendance_system.dto.RegisterCourse;
import com.smartattendance.smart_attendance_system.dto.UpdateCourseDto;
import com.smartattendance.smart_attendance_system.entity.Course;
import com.smartattendance.smart_attendance_system.entity.CourseRegistration;
import com.smartattendance.smart_attendance_system.entity.User;
import com.smartattendance.smart_attendance_system.enums.Role;
import com.smartattendance.smart_attendance_system.repository.CourseRegistrationRepository;
import com.smartattendance.smart_attendance_system.repository.CourseRepository;
import com.smartattendance.smart_attendance_system.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository, CourseRegistrationRepository courseRegistrationRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
    }

    public ResponseEntity<AttendanceResponse> createCourse(CreateCourseDto dto) {
        try {
            Optional<Course> existingCourse = courseRepository.findByCourseCode(dto.getCourseCode());
            if (existingCourse.isPresent()) {
                return new ResponseEntity<>(new AttendanceResponse("99", "Course already exists", LocalDateTime.now(), null), HttpStatus.CONFLICT);
            }

            Optional<User> lecturer = userRepository.findByIdentifier(dto.getLecturerIdentifier());
            if (lecturer.isEmpty() || lecturer.get().getRole() != Role.LECTURER) {
                return new ResponseEntity<>(new AttendanceResponse("99", "Lecturer not found", LocalDateTime.now(), null), HttpStatus.NOT_FOUND);
            }

            Course course = new Course();
            course.setCourseCode(dto.getCourseCode());
            course.setCourseTitle(dto.getCourseTitle());
            course.setLevel(dto.getLevel());
            course.setCourseUnit(dto.getCourseUnit());
            course.setDepartment(dto.getDepartment());
            course.setSemester(dto.getSemester());
            course.setLecturerIdentifier(dto.getLecturerIdentifier());

            courseRepository.save(course);

        } catch (Exception e) {
            return new ResponseEntity<>(new AttendanceResponse("99", "Failed to create course", LocalDateTime.now(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new AttendanceResponse("00", "Course created successfully", LocalDateTime.now(), null), HttpStatus.OK);
    }

    public ResponseEntity<AttendanceResponse> updateCourse(UpdateCourseDto dto) {
        try {
            Optional<Course> opt = courseRepository.findByCourseCode(dto.getCourseCode());
            if (opt.isEmpty()) {
                return new ResponseEntity<>(new AttendanceResponse("99", "Course not found", LocalDateTime.now(), null), HttpStatus.NOT_FOUND);
            }

            Course course = opt.get();

            if (dto.getCourseTitle() != null) course.setCourseTitle(dto.getCourseTitle());
            if (dto.getLevel() != null) course.setLevel(dto.getLevel());
            if (dto.getCourseUnit() != null) course.setCourseUnit(dto.getCourseUnit());
            if (dto.getDepartment() != null) course.setDepartment(dto.getDepartment());
            if (dto.getSemester() != null) course.setSemester(dto.getSemester());
            if (dto.getLecturerIdentifier() != null)course.setLecturerIdentifier(dto.getLecturerIdentifier()); {
//                Optional<User> lecturer = userRepository.findByIdentifier(dto.getLecturerIdentifier());
//                if (lecturer.isPresent() && lecturer.get().getRole() == Role.LECTURER) {
//                    course.setLecturer(lecturer.get());
//                }
            }

            courseRepository.save(course);

        } catch (Exception e) {
            return new ResponseEntity<>(new AttendanceResponse("99", "Failed to update course", LocalDateTime.now(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new AttendanceResponse("00", "Course updated successfully", LocalDateTime.now(), null), HttpStatus.OK);
    }

    public ResponseEntity<AttendanceResponse> getCourseByCourseId(Long courseId) {
        Optional<Course> optCourse = courseRepository.findByCourseId(courseId);

        if (optCourse.isEmpty()) {
            return new ResponseEntity<>(new AttendanceResponse("99", "Course not found", LocalDateTime.now(), null),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                new AttendanceResponse("00", "Course found", LocalDateTime.now(), optCourse.get()),
                HttpStatus.OK
        );
    }


    public ResponseEntity<List<CreateCourseDto>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        // Convert to DTOs if needed
        List<CreateCourseDto> courseDtos = courses.stream().map(c ->
                new CreateCourseDto(c.getCourseCode(), c.getCourseTitle(), c.getLevel(), c.getCourseUnit(), c.getDepartment(), c.getSemester(), c.getLecturerIdentifier())
        ).toList();

        return new ResponseEntity<>(courseDtos, HttpStatus.OK);
    }
    public ResponseEntity<AttendanceResponse> deleteCourseByCode(String courseCode) {
        Optional<Course> optCourse = courseRepository.findByCourseCode(courseCode);

        if (optCourse.isEmpty()) {
            return new ResponseEntity<>(
                    new AttendanceResponse("99", "Course not found", LocalDateTime.now(), null),
                    HttpStatus.NOT_FOUND
            );
        }

        try {
            courseRepository.deleteByCourseCode(courseCode);
            return new ResponseEntity<>(
                    new AttendanceResponse("00", "Course deleted successfully", LocalDateTime.now(), null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new AttendanceResponse("99", "Failed to delete course", LocalDateTime.now(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<AttendanceResponse> registerCourse(RegisterCourse dto) {
        try {
            // 1️⃣ Check if student exists
            Optional<User> optStudent = userRepository.findByIdentifier(dto.getIdentifier());
            if (optStudent.isEmpty() || optStudent.get().getRole() != Role.STUDENT) {
                return new ResponseEntity<>(new AttendanceResponse("99", "Student not found", LocalDateTime.now(), null),
                        HttpStatus.NOT_FOUND);
            }

            User student = optStudent.get();
            List<String> registeredCourses = new ArrayList<>();
            List<String> skippedCourses = new ArrayList<>();

            // 2️⃣ Loop through all courses to register
            for (RegisterCourse.CourseToRegister courseDto : dto.getCourses()) {

                String courseCode = courseDto.getCourseCode();

                Optional<Course> optCourse =
                        courseRepository.findByCourseCode(courseCode);

                if (optCourse.isEmpty()) {
                    skippedCourses.add(courseCode + " (not found)");
                    continue;
                }

                // 3️⃣ Check if already registered
                boolean alreadyRegistered =
                        courseRegistrationRepository
                                .existsByStudentIdentifierAndCourseCode(
                                        dto.getIdentifier(),
                                        courseCode
                                );

                if (alreadyRegistered) {
                    skippedCourses.add(courseCode + " (already registered)");
                    continue;
                }

                // 4️⃣ Register student
                CourseRegistration registration = new CourseRegistration();
                registration.setStudentIdentifier(dto.getIdentifier());
                registration.setCourseCode(courseCode);
                registration.setRegisteredAt(LocalDateTime.now());

                courseRegistrationRepository.save(registration);
                registeredCourses.add(courseCode);
            }

            // 5️⃣ Build a single response message
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("Registration complete. Registered: ").append(registeredCourses);

            if (!skippedCourses.isEmpty()) {
                messageBuilder.append(". Skipped: ").append(skippedCourses);
            }

            return new ResponseEntity<>(
                    new AttendanceResponse("00", messageBuilder.toString(), LocalDateTime.now(), null),
                    HttpStatus.OK
            );


        } catch (Exception e) {
            return new ResponseEntity<>(new AttendanceResponse("99", "Failed to register courses", LocalDateTime.now(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


