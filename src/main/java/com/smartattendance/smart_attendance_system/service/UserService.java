package com.smartattendance.smart_attendance_system.service;

import com.smartattendance.smart_attendance_system.dto.AttendanceResponse;
import com.smartattendance.smart_attendance_system.dto.LoginRequestDto;
import com.smartattendance.smart_attendance_system.dto.RegisterUserDto;
import com.smartattendance.smart_attendance_system.dto.UpdateUserDto;
import com.smartattendance.smart_attendance_system.entity.User;
import com.smartattendance.smart_attendance_system.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

        private final UserRepository userRepository;

        public UserService(UserRepository userRepository){
            this.userRepository = userRepository;
        }

        public ResponseEntity<AttendanceResponse> registerUser(RegisterUserDto dto){
            try {
                if(userRepository.existsByIdentifier(dto.getIdentifier())){
                    return new ResponseEntity<>(new AttendanceResponse("99","User already exists", LocalDateTime.now(), null), HttpStatus.CONFLICT);
                }

                User user = new User();
                user.setIdentifier(dto.getIdentifier());
                user.setFirstName(dto.getFirstName());
                user.setLastName(dto.getLastName());
                user.setEmail(dto.getEmail());
                user.setPassword(dto.getPassword()); // TODO: hash password
                user.setRole(dto.getRole());
                user.setLevel(dto.getLevel());
                user.setDepartment(dto.getDepartment());
                user.setDeviceId(dto.getDeviceId());

                userRepository.save(user);

            } catch(Exception e){
                return new ResponseEntity<>(new AttendanceResponse("99","Failed to register user", LocalDateTime.now(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(new AttendanceResponse("00","User registered successfully", LocalDateTime.now(), null), HttpStatus.OK);
        }

        public ResponseEntity<AttendanceResponse> updateUser(UpdateUserDto dto){
            try{
                Optional<User> opt = userRepository.findByIdentifier(dto.getIdentifier());
                if(opt.isEmpty()){
                    return new ResponseEntity<>(new AttendanceResponse("99","User not found", LocalDateTime.now(), null), HttpStatus.NOT_FOUND);
                }

                User user = opt.get();
                if(dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
                if(dto.getLastName() != null) user.setLastName(dto.getLastName());
                if(dto.getEmail() != null) user.setEmail(dto.getEmail());
                if(dto.getPassword() != null) user.setPassword(dto.getPassword());
                if(dto.getRole() != null) user.setRole(dto.getRole());
                if(dto.getLevel() != null) user.setLevel(dto.getLevel());
                if(dto.getDepartment() != null) user.setDepartment(dto.getDepartment());
                if(dto.getDeviceId() != null) user.setDeviceId(dto.getDeviceId());

                userRepository.save(user);

            } catch(Exception e){
                return new ResponseEntity<>(new AttendanceResponse("99","Failed to update user", LocalDateTime.now(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(new AttendanceResponse("00","User updated successfully", LocalDateTime.now(), null), HttpStatus.OK);
        }

        public ResponseEntity<AttendanceResponse> login(LoginRequestDto dto){
            try{
                Optional<User> opt = userRepository.findByIdentifier(dto.getIdentifier());
                if(opt.isEmpty()){
                    return new ResponseEntity<>(new AttendanceResponse("99","User not found", LocalDateTime.now(), null), HttpStatus.NOT_FOUND);
                }

                User user = opt.get();
                if(!user.getPassword().equals(dto.getPassword())){
                    return new ResponseEntity<>(new AttendanceResponse("99","Invalid credentials", LocalDateTime.now(), null), HttpStatus.UNAUTHORIZED);
                }

            } catch(Exception e){
                return new ResponseEntity<>(new AttendanceResponse("99","Login failed", LocalDateTime.now(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(new AttendanceResponse("00","Login successful", LocalDateTime.now(), null), HttpStatus.OK);
        }

    public ResponseEntity<AttendanceResponse> getUser(String identifier) {

        Optional<User> user = userRepository.findByIdentifier(identifier);

        if (user.isEmpty()) {
            return new ResponseEntity<>(
                    new AttendanceResponse("99", "User not found", LocalDateTime.now(), null),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                new AttendanceResponse("00", "User found", LocalDateTime.now(), user.get()),
                HttpStatus.OK
        );
    }

    // 2️⃣ Get all users
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 3️⃣ Delete user
    public ResponseEntity<AttendanceResponse> deleteUser(String identifier) {

        Optional<User> user = userRepository.findByIdentifier(identifier);

        if (user.isEmpty()) {
            return new ResponseEntity<>(
                    new AttendanceResponse("99", "User not found", LocalDateTime.now(), null),
                    HttpStatus.NOT_FOUND
            );
        }

        try {
            userRepository.delete(user.get());

            return new ResponseEntity<>(
                    new AttendanceResponse("00", "User deleted successfully", LocalDateTime.now(), null),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new AttendanceResponse("99", "Failed to delete user", LocalDateTime.now(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}

