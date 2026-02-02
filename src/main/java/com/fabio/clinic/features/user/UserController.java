package com.fabio.clinic.features.user;

import com.fabio.clinic.features.auth.AuthDTO;
import com.fabio.clinic.features.auth.AuthResponseDTO;
import com.fabio.clinic.features.auth.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, TokenService tokenService){
        this.service = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    //LOGIN
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data){
        try{
            var user = service.findByName(data.name());
            if(passwordEncoder.matches(data.password(), user.getPassword())){
                var token = tokenService.generateToken(user);
                return ResponseEntity.ok(new AuthResponseDTO(token));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (Exception e) {
            throw new RuntimeException("Usuário não encontrado", e);
        }
    }

    @GetMapping
    public List<UserResponseDTO> findAll(){

        return service.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO create(@Valid @RequestBody UserRequestDTO data){
        User newUser = service.create(data);
        return new UserResponseDTO(newUser);
    }
}
