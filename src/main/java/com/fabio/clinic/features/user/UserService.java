package com.fabio.clinic.features.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll(){
        return repository.findAll();
    }
    public User create(UserRequestDTO data){
        User newUser = new User(data);
        String encPassword = passwordEncoder.encode(data.password());
        newUser.setPassword(encPassword);
        return repository.save(newUser);
    }

}
