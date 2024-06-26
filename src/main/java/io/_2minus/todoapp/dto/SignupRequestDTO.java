package io._2minus.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {

    @NotBlank
    private String nickname;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String username;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]{8,15}$")
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}
