package com.fabio.clinic.features.user;

import jakarta.transaction.Transactional;
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

    @Transactional
    public User create(UserRequestDTO data){
        long UserCount = repository.count();
        UserRole role = (UserCount == 0) ? UserRole.ADMIN : UserRole.STAFF;
        String encPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.name(), encPassword, role);
        return repository.save(newUser);
    }
    public User findByName(String name){
        return repository.findByName(name).orElseThrow(()-> new RuntimeException("Usuário não encontrado com o nome: ." + name));
    }

}
