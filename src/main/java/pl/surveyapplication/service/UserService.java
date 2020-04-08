package pl.surveyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.surveyapplication.exception.UserNotFoundException;
import pl.surveyapplication.model.User;
import pl.surveyapplication.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(int userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent())
            throw new UserNotFoundException("User of this ID is not available...");
        return optionalUser.get();
    }

    public User updateUser(int userId, User user){
        Optional<User> optionalSurvey = userRepository.findById(userId);
        if(!optionalSurvey.isPresent())
            throw new UserNotFoundException("User of this ID is not available...");
        user.setUserId(userId);
        return userRepository.save(user);
    }

    public void deleteUser(int userId){
        Optional<User> optionalSurvey = userRepository.findById(userId);
        if(!optionalSurvey.isPresent())
            throw new UserNotFoundException("User of this ID is not available...");
        userRepository.deleteById(userId);
    }
}
