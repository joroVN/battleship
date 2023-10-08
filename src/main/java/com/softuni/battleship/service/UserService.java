package com.softuni.battleship.service;

import com.softuni.battleship.model.UserEntity;
import com.softuni.battleship.model.dto.UserLoginDTO;
import com.softuni.battleship.model.dto.UserRegisterDTO;
import com.softuni.battleship.model.mapper.UserMapper;
import com.softuni.battleship.model.session.LoggedUser;
import com.softuni.battleship.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, LoggedUser currentUser) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = currentUser;
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        UserEntity user = userMapper.userDtoToUserEntity(userRegisterDTO);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(user);
    }

    public boolean login(UserLoginDTO userLoginDTO) {

        Optional<UserEntity> userOpt = this.userRepository.findByUsername(userLoginDTO.getUsername());

        if (userOpt.isEmpty()){
            return false;
        }

        String rawPassword = userLoginDTO.getPassword();
        String encodedPassword = userOpt.get().getPassword();
        boolean matches = this.passwordEncoder.matches(rawPassword, encodedPassword);
        if (!matches){
            return false;
        }

        this.loggedUser.login(userOpt.get());

        return true;
    }

    public UserEntity findLoggedUser() {
        Optional<UserEntity> userOpt = this.userRepository.findByUsername(this.loggedUser.getUsername());
        return userOpt.get();
    }

    public void logout() {
        this.loggedUser.logout();
    }

    public boolean isLoggedIn(){
        return this.loggedUser.getId() != null;
    }

    public Long getLoggedUserId(){
        return this.loggedUser.getId();
    }
}
