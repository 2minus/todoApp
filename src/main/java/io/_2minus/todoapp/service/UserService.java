package io._2minus.todoapp.service;

import io._2minus.todoapp.dto.SignupRequestDTO;
import io._2minus.todoapp.entity.User;
import io._2minus.todoapp.entity.UserRoleEnum;
import io._2minus.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDTO dto) {
        String nickname = dto.getNickname();
        String username = dto.getUsername();
        String password = dto.getPassword();

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalStateException("이미 존재하는 ID 입니다.");
        }

        Optional<User> checkNickname = userRepository.findByNickname(nickname);
        if (checkNickname.isPresent()) {
            throw new IllegalStateException("이미 존재하는 별명 입니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if(dto.isAdmin()) {
            if(!ADMIN_TOKEN.equals(dto.getAdminToken())) {
                throw new IllegalStateException("잘못된 관리자 암호입니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(nickname, username, password, role);
        userRepository.save(user);

    }
}
