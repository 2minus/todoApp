package io._2minus.todoapp.controller;

import io._2minus.todoapp.dto.LoginRequestDTO;
import io._2minus.todoapp.dto.SignupRequestDTO;
import io._2minus.todoapp.dto.UserInfoDTO;
import io._2minus.todoapp.entity.UserRoleEnum;
import io._2minus.todoapp.security.UserDetailsImpl;
import io._2minus.todoapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/v1.0")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public void signup (@Valid SignupRequestDTO dto, BindingResult bindingResult) {
        List<FieldError> fieldsErrors = bindingResult.getFieldErrors();
        if(fieldsErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
        }

        userService.signup(dto);
    }

    @PostMapping("/user/login")
    public void login(@Valid LoginRequestDTO dto) {

    }

    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDTO getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDTO(username, isAdmin);
    }
}
