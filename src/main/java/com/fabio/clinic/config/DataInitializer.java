package com.fabio.clinic.config;

import com.fabio.clinic.features.user.User;
import com.fabio.clinic.features.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(UserRepository repository){
        return  args -> {
            if(repository.count() == 0){
                User u1 = new User();
                u1.setName("Fabio Cordeiro");
                u1.setPassword("123456789");
                repository.save(u1);
                System.out.println("Usuário salvo com sucesso");
            }
            else {
                System.out.println(">>> Banco já possui dados, pulando seeding.");
            }
        };
    }
}
