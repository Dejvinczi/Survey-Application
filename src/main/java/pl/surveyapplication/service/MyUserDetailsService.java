package pl.surveyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.surveyapplication.model.MyUserDetails;
import pl.surveyapplication.model.User;
import pl.surveyapplication.repository.UserRepository;

import java.util.Optional;

/**
 * @author Dawid
 * @version 1.0
 * Klasa serwisów informacji o uzytkowniku.
 * */
@Service
public class MyUserDetailsService implements UserDetailsService {

    /**
     * Zmienna przechowuje repozytorium użytkowników
     * */
    @Autowired
    UserRepository userRepository;

    /**
     * Metoda zwraca informacje użytkownika o podanym emailu
     * @param email email użytkownika.
     * @return userDetails informacje o użytkowniku.
     * */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found user with login: " + email));

        return user.map(MyUserDetails::new).get();
    }
}
