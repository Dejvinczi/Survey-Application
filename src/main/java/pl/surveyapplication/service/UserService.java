package pl.surveyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.surveyapplication.exception.UserNotFoundException;
import pl.surveyapplication.model.User;
import pl.surveyapplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    MyUserDetailsService userDetails;

    public List<User> getAllUsers()
    {
        List<User> result = (List<User>) repository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<User>();
        }
    }

    public User getUserById(Long id)
    {
        Optional<User> user = repository.findById(id);

        if(user.isPresent())
            return user.get();
        else
            throw new UserNotFoundException("User of this ID is not available...");
    }

    public User createOrUpdateUser(User entity)
    {
        if(entity.getUserId() == null)
        {
            entity = repository.save(entity);

            return entity;
        }
        else
        {
            Optional<User> user = repository.findById(entity.getUserId());

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
                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);
                return entity;
            }
        }
    }

    public void deleteUserById(Long id)
    {
        Optional<User> employee = repository.findById(id);

        if(employee.isPresent())
            repository.deleteById(id);
        else
            throw new UserNotFoundException("User of this ID is not available...");
    }
}
