package org.telemedicine.serverside.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telemedicine.serverside.dto.api.ApiResponse;
import org.telemedicine.serverside.dto.auth.AuthResponse;
import org.telemedicine.serverside.dto.register.RecordBookRequest;
import org.telemedicine.serverside.dto.register.RegisterRequest;
import org.telemedicine.serverside.entity.MedicalRecordBook;
import org.telemedicine.serverside.entity.User;
import org.telemedicine.serverside.enums.Roles;
import org.telemedicine.serverside.exception.AppException;
import org.telemedicine.serverside.repository.MedicalRecordBookRepository;
import org.telemedicine.serverside.repository.UserRepository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MedicalRecordBookRepository medicalRecordBookRepository;


    public void register(@Valid RegisterRequest registerRequest) {
        //tìm email tồn tại
        if(userRepository.existsByEmailAndGoogleIdIsNull(registerRequest.getEmail())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Email has existed", "auth-e-01");
        }
    }

    @Transactional
    public void verifyRegister(RegisterRequest emailRequest, RecordBookRequest request) {
        //tìm email tồn tại
//        if(userRepository.existsByEmail(emailRequest.getEmail())) {
//            throw new AppException(HttpStatus.BAD_REQUEST, "Email has existed", "auth-e-01");
//        }

        //hashPassword
        String hashPassword = passwordEncoder.encode(emailRequest.getPassword());

        User user = User.builder()
                .name(request.getName())
                .isEnable(true)
                .role(Roles.USER)
                .createdAt(LocalDateTime.now())
                .email(emailRequest.getEmail())
                .password(hashPassword)
                .build();
        userRepository.save(user);

        MedicalRecordBook medicalRecordBook = MedicalRecordBook.builder()
                .dob(request.getDob())
                .phone(request.getPhone())
                .bhyt(request.getBhyt())
                .gender(request.getGender())
                .name(request.getName())
                .address(request.getAddress())
                .userId(user)
                .build();
        medicalRecordBookRepository.save(medicalRecordBook);
    }

    public ApiResponse<User> saveUserLoginGoogle(Map<String, Object> attribute) {
        String googleId = (String) attribute.get("sub");
        String email = (String) attribute.get("email");
        String picture = (String) attribute.get("picture");
        String name = (String) attribute.get("given_name");

        Optional<User> user = userRepository.findByGoogleId(googleId);
        if(user.isPresent()) {
            User existingUser = user.get();
            existingUser.setName(name);
            existingUser.setGoogleId(googleId);
            userRepository.save(existingUser);

            MedicalRecordBook newMedicalRecordBook = medicalRecordBookRepository.findByUserId(existingUser);
            newMedicalRecordBook.setAvatar(picture);
            medicalRecordBookRepository.save(newMedicalRecordBook);

            return ApiResponse.<User>builder()
                    .data(user.get())
                    .code("ok")
                    .message("updated")
                    .build();
        }else{
            User newUser = User.builder()
                    .googleId(googleId)
                    .role(Roles.USER)
                    .createdAt(LocalDateTime.now())
                    .isEnable(true)
                    .name(name)
                    .email(email)
                    .build();
            userRepository.save(newUser);

            MedicalRecordBook medicalRecordBook = MedicalRecordBook.builder()
                    .userId(newUser)
                    .name(name)
                    .avatar(picture)
                    .build();
            medicalRecordBookRepository.save(medicalRecordBook);
            return ApiResponse.<User>builder()
                    .data(newUser)
                    .code("ok")
                    .message("save success")
                    .build();
        }

    }
}
