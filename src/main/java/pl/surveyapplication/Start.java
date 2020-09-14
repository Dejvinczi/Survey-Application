package pl.surveyapplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.surveyapplication.config.security.PasswordConfig;
import pl.surveyapplication.repository.UserRepository;

@Component
public class Start {

    private final PasswordConfig passwordConfig;
    private final UserRepository userRepository;

    @Autowired
    public Start(PasswordConfig passwordConfig, UserRepository userRepository) {
        this.passwordConfig = passwordConfig;
        this.userRepository = userRepository;
    }

}
