package com.fabio.clinic.features.user;

import com.fabio.clinic.features.auth.AuthDTO;
import com.fabio.clinic.features.auth.AuthResponseDTO;
import com.fabio.clinic.features.auth.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper mapper, TokenService tokenService){
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = mapper;
        this.tokenService = tokenService;
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO data){
        long UserCount = repository.count();
        UserRole role = (UserCount == 0) ? UserRole.ADMIN : UserRole.STAFF;
        String encPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.name(), encPassword, role);
        return new UserResponseDTO(repository.save(newUser));
    }
    public User findByName(String name){
        return repository.findByName(name).orElseThrow(()-> new RuntimeException("Usuário não encontrado com o nome: ." + name));
    }

    public UserResponseDTO findById(Long id){
        User user = repository.findById(id).orElseThrow();
        return new UserResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO update(Long id, UserUpdateDTO data){
        User user = repository.findById(id).orElseThrow();
        userMapper.updateEntityFromDTO(data, user);

        if (data.password() != null && !data.password().isBlank()) {
            if(!data.password().equals(data.confirmPassword())){
                throw new ValidationException("As senhas não são iguais");
            }
            user.setPassword(passwordEncoder.encode(data.password()));
        }
        var updatedUser = repository.save(user);
        return new UserResponseDTO(updatedUser);
    }

    @Transactional
    public void deleteById(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Nenhum usuário encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
    //LOGIN
    public AuthResponseDTO login(AuthDTO data){
        User user = repository.findByName(data.name())
                .orElseThrow(()-> new BadCredentialsException("Usuário ou senha incorretos."));
        if (!passwordEncoder.matches(data.password(), user.getPassword())){
            throw new BadCredentialsException("Usuário ou senha incorretos.");
        }
        String token = tokenService.generateToken(user);
        return new AuthResponseDTO(token);
    }


}
