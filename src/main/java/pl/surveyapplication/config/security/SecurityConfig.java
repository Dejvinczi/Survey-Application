package pl.surveyapplication.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.surveyapplication.service.MyUserDetailsService;

/**
 * @author Dawid
 * @version 1.0
 * Klasa rozszerzająca WebSecurityConfigurerAdapter ze Spring Security. Odpowiada za konfiguracje bezpieczeństwa naszej aplikacji Spring.
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *System kodowania haseł.
     * */
    private final PasswordEncoder passwordEncoder;

    /**
     *Zbiór informacji o zalogowanym użykowniku
     * */
    @Autowired
    MyUserDetailsService userDetailsService;

    /**
     * @param passwordEncoder System kodowania haseł
     * Konstruktor, przypisuje system kodowania haseł do konfiguracji kodowania haseł.
     * */
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param auth Budowanie zależności naszej autentykacji podczas logowania
     * Metoda configure odpowiada za przypisanie budowania naszych zalezności użytkownika..
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * @param http Zapytanie HTTP
     * Metoda przetwarza zapytanie oraz decyduje czy zalogowany użytkownik ma dostęp do zasobu.
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/surveys/**").permitAll()
                .antMatchers("/user/surveys/finish-from-angular").permitAll()
                .antMatchers("/user/surveys/finish/**").permitAll()
                .antMatchers("/home","/user/**/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/admin/**/**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .cors();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
