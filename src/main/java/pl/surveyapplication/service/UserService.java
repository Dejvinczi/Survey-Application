package pl.surveyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.surveyapplication.exception.UserNotFoundException;
import pl.surveyapplication.model.User;
import pl.surveyapplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Dawid
 * @version 1.0
 * Klasa serwisów użytkownika.
 * */
@Component
public class UserService {
    /**
     * Zmienna przechowuje repozytorium użytkownika
     * */
    @Autowired
    UserRepository userRepository;
    /**
     * Zmienna przechowuje repozytorium informacji o użytkowniku
     * */
    @Autowired
    MyUserDetailsService userDetails;

    /**
     * Metoda zwraca liste uzytkowników z naszej bazie danych.
     * @return liste obiektów klasy User
     * */
    public List<User> getAllUsers()
    {
        List<User> result = (List<User>) userRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<User>();
        }
    }

    /**
     * Metoda zwraca użytkownika z naszej bazy danych o konkretnym ID.
     * @param id id konkretnego użytkownika.
     * @return obiekt klasy User
     * */
    public User getUserById(Long id)
    {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent())
            return user.get();
        else
            throw new UserNotFoundException("User of this ID is not available...");
    }

    /**
     * Metoda dodaj lub uaktualnia użytkownika w naszej bazie danych.
     * @param entity nowy użytkownik lub użytkownik który już istnieje i którego będziemy uaktualniać.
     * @return obiekt klasy User
     * */
    public User createOrUpdateUser(User entity)
    {
        if(entity.getUserId() == null)
        {
            entity = userRepository.save(entity);

            return entity;
        }
        else
        {
            Optional<User> user = userRepository.findById(entity.getUserId());

            if(user.isPresent())
            {
                User newEntity = user.get();
                newEntity.setUserId(entity.getUserId());
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
                newEntity.changePassword(entity.getPassword());
                newEntity.setRoles(entity.getRoles());
                newEntity.setActive(entity.isActive());
                newEntity = userRepository.save(newEntity);

                return newEntity;
            } else {
                entity = userRepository.save(entity);
                return entity;
            }
        }
    }

    /**
     * Metoda usuwaużytkownika z naszej bazie danych o podanym ID.
     * @param id ID użytkownika
     * */
    public void deleteUserById(Long id)
    {
        Optional<User> employee = userRepository.findById(id);

        if(employee.isPresent())
            userRepository.deleteById(id);
        else
            throw new UserNotFoundException("User of this ID is not available...");
    }

    /**
     * Metoda wyszukuje i zwraca użytkownika z naszej bazie danych o podanym emailu.
     * @param email email użytkownika
     * @return liste Optional z obiektami klasy User
     * */
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
