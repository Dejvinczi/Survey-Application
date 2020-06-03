package pl.surveyapplication.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Dawid
 * @version 1.0
 * Klasa konfiguracyjna metody kodowania haseł użytkowników
 * */
@Configuration
public class PasswordConfig {

    /**
     * Metoda zwraca sposób kodowania który domyślnie jest ustawiony na BCryptPasswordEncoder(10)
     * @return PassEncoder
     * */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
