package com.smartattendance.smart_attendance_system.service;

import com.smartattendance.smart_attendance_system.dto.AttendanceResponse;
import com.smartattendance.smart_attendance_system.dto.CreateCourseDto;
import com.smartattendance.smart_attendance_system.dto.RegisterCourse;
import com.smartattendance.smart_attendance_system.dto.UpdateCourseDto;
import com.smartattendance.smart_attendance_system.entity.Course;
import com.smartattendance.smart_attendance_system.entity.User;
import com.smartattendance.smart_attendance_system.repository.CourseRepository;
import com.smartattendance.smart_attendance_system.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

        private final CourseRepository courseRepository;
        private final UserRepository userRepository;

        public CourseService(CourseRepository courseRepository, UserRepository userRepository){
            this.courseRepository = courseRepository;
            this.userRepository = userRepository;
        }

        public ResponseEntity<AttendanceResponse> createCourse(CreateCourseDto dto){
            try{
                Optional<Course> existingCourse = courseRepository.findByCourseCode(dto.getCourseCode());
                if(existingCourse.isPresent()){
                    return new ResponseEntity<>(new AttendanceResponse("99","Course already exists", null), HttpStatus.CONFLICT);
                }

                Optional<User> lecturer = userRepository.findByIdentifier(dto.getLecturerIdentifier());
                if(lecturer.isEmpty() || lecturer.get().getRole() != User.Role.LECTURER){
                    return new ResponseEntity<>(new AttendanceResponse("99","Lecturer not found", null), HttpStatus.NOT_FOUND);
                }

                Course course = new Course();
                course.setCourseCode(dto.getCourseCode());
                course.setCourseTitle(dto.getCourseTitle());
                course.setLecturer(lecturer.get());

                courseRepository.save(course);

            }catch(Exception e){
                return new ResponseEntity<>(new AttendanceResponse("99","Failed to create course", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(new AttendanceResponse("00","Course created successfully", null), HttpStatus.OK);
        }

        public ResponseEntity<AttendanceResponse> updateCourse(UpdateCourseDto dto){
            try{
                Optional<Course> opt = courseRepository.findByCourseCode(dto.getCourseCode());
                if(opt.isEmpty()){
                    return new ResponseEntity<>(new AttendanceResponse("99","Course not found", null), HttpStatus.NOT_FOUND);
                }

                Course course = opt.get();

                if(dto.getCourseTitle() != null) course.setCourseTitle(dto.getCourseTitle());
                if(dto.getLecturerIdentifier() != null){
                    Optional<User> lecturer = userRepository.findByIdentifier(dto.getLecturerIdentifier());
                    if(lecturer.isPresent() && lecturer.get().getRole() == User.Role.LECTURER){
                        course.setLecturer(lecturer.get());
                    }
                }

                courseRepository.save(course);

            }catch(Exception e){
                return new ResponseEntity<>(new AttendanceResponse("99","Failed to update course", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(new AttendanceResponse("00","Course updated successfully", null), HttpStatus.OK);
        }

        public ResponseEntity<AttendanceResponse> getCourseById(Long courseId){
            Optional<Course> opt = courseRepository.findById(courseId);
            if(opt.isEmpty()){
                return new ResponseEntity<>(new AttendanceResponse("99","Course not found", null), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(new AttendanceResponse("00","Course found", opt.get()), HttpStatus.OK);
        }

        public ResponseEntity<List<CreateCourseDto>> getAllCourses(){
            List<Course> courses = courseRepository.findAll();
            // Convert to DTOs if needed
            List<CreateCourseDto> courseDtos = courses.stream().map(c ->
                    new CreateCourseDto(c.getCourseCode(), c.getCourseTitle(), c.getLecturer().getIdentifier())
            ).toList();

            return new ResponseEntity<>(courseDtos, HttpStatus.OK);
        }

        public ResponseEntity<AttendanceResponse> registerCourse(RegisterCourse dto){
            try{
                Optional<Course> optCourse = courseRepository.findById(dto.getCourseId());
                if(optCourse.isEmpty()){
                    return new ResponseEntity<>(new AttendanceResponse("99","Course not found", null), HttpStatus.NOT_FOUND);
                }

                Optional<User> optStudent = userRepository.findByIdentifier(dto.getIdentifier());
                if(optStudent.isEmpty() || optStudent.get().getRole() != User.Role.STUDENT){
                    return new ResponseEntity<>(new AttendanceResponse("99","Student not found", null), HttpStatus.NOT_FOUND);
                }

                Course course = optCourse.get();
                User student = optStudent.get();

                if(course.getStudents().contains(student)){
                    return new ResponseEntity<>(new AttendanceResponse("99","Student already registered", null), HttpStatus.CONFLICT);
                }

                course.getStudents().add(student);
                courseRepository.save(course);

            }catch(Exception e){
                return new ResponseEntity<>(new AttendanceResponse("99","Failed to register student to course", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(new AttendanceResponse("00","Student registered to course successfully", null), HttpStatus.OK);
        }

}


