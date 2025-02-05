package org.telemedicine.serverside.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;
import org.telemedicine.serverside.dto.api.ApiResponse;
import org.telemedicine.serverside.dto.auth.AuthResponse;
import org.telemedicine.serverside.dto.register.RecordBookRequest;
import org.telemedicine.serverside.dto.register.RegisterRequest;
import org.telemedicine.serverside.dto.register.RegisterResponse;
import org.telemedicine.serverside.entity.User;
import org.telemedicine.serverside.enums.Roles;
import org.telemedicine.serverside.service.AuthenticationService;
import org.telemedicine.serverside.service.MailService;
import org.telemedicine.serverside.utils.CodeUtil;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class AuthenticationController {
    @Autowired
    private CodeUtil codeUtil;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private MailService mailService;

    @GetMapping("/test")
    String test(){
        return "test";
    }
    //login with google
    @GetMapping("/api/auth/SSO/sign-in-google")
    public ResponseEntity<ApiResponse<User>> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationService.saveUserLoginGoogle(
                        oAuth2AuthenticationToken
                            .getPrincipal()
                            .getAttributes()));
    }


    @PostMapping("/auth/register")
    ResponseEntity<ApiResponse<Void>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        authenticationService.register(registerRequest); //kểm tra email
        String verificationCode = UUID.randomUUID().toString(); // taạo code xác thực
        codeUtil.save(verificationCode, registerRequest, 5);//5 phút //lưu max xasc thucj
        mailService.sendEmailToVerifyRegister(registerRequest.getEmail(), verificationCode); // guiw mail
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code("auth-s-01")
                .message("Request register successfully, check your email")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    //Tao so kham benh
    @GetMapping("/auth/register/createRecordBook/{verificationCode}")
    public RedirectView redirectCreateRecordBook(@PathVariable String verificationCode) {
        String redirectUrl = "http://localhost:3000/auth/registerRecordBook?verificationCode=" + verificationCode;
        return new RedirectView(redirectUrl);
    }
    //xac nhan email user
    @PostMapping("/auth/register/verify/{verificationCode}")
    public ResponseEntity<ApiResponse<RegisterResponse>> verifyRegister(@PathVariable String verificationCode, @RequestBody @Valid RecordBookRequest request) {
        RegisterRequest emailRequest = (RegisterRequest) codeUtil.get(verificationCode);
        authenticationService.verifyRegister(emailRequest, request);
        codeUtil.remove(verificationCode);
        mailService.sendEmailToWelcome(emailRequest.getEmail());
        ApiResponse<RegisterResponse> apiResponse = ApiResponse.<RegisterResponse>builder()
                .code("auth-s-01")
                .message("Register successful")
                .data(RegisterResponse.builder()
                        .name(request.getName())
                        .email(emailRequest.getEmail())
                        .roles(Roles.USER)
                        .createdAt(LocalDateTime.now())
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
