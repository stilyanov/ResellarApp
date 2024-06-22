package com.resellerapp.service;

import com.resellerapp.config.UserSession;
import com.resellerapp.model.dto.UserLoginDTO;
import com.resellerapp.model.dto.UserRegisterDTO;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    public boolean register(UserRegisterDTO registerDTO) {
        Optional<User> byUsernameOrEmail = this.userRepository.findByUsernameOrEmail(registerDTO.getUsername(), registerDTO.getEmail());

        if (byUsernameOrEmail.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        this.userRepository.save(user);

        return true;
    }

    public boolean login(UserLoginDTO loginDTO) {
        Optional<User> optionalUser = this.userRepository.findByUsername(loginDTO.getUsername());

        if (optionalUser.isEmpty()) {
            return false;
        }

        boolean matches = passwordEncoder.matches(loginDTO.getPassword(), optionalUser.get().getPassword());

        if (!matches) {
            return false;
        }

        this.userSession.login(optionalUser.get().getId(), loginDTO.getUsername());

        return true;
    }

    public void logout() {
        this.userSession.logout();
    }
}
