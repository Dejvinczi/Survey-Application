package pl.surveyapplication.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dawid
 * @version 1.0
 * Klasa reprezentuje użytkownika w naszym projekcie.
 * */
@Entity
@Table(name = "USERS")
public class User {
    /**
     * Zmienna przechowuje id użytkownika
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "USER_ID")
    private Long userId;
    /**
     * Zmienna przechowuje imie użytkownika
     * */
    @Column(name = "FIRST_NAME")
    private String firstName;
    /**
     * Zmienna przechowuje nazwisko użytkownika
     * */
    @Column(name = "LAST_NAME")
    private String lastName;
    /**
     * Zmienna przechowuje email użytkownika
     * */
    @Column(name = "EMAIL")
    private String email;
    /**
     * Zmienna przechowuje hasło użytkownika
     * */
    @Column(name = "PASSWORD")
    private String password;
    /**
     * Zmienna przechowuje stan aktywności użutkownika
     * */
    @Column(name="ACTIVE")
    private boolean active;
    /**
     * Zmienna przechowuje informacje o prawach dostępu
     * */
    @Column(name ="ROLE")
    private String roles;

    /**
     * Konstruktor bezparametrowy
     * */
    public User(){}
    /**
     * Konstruktor ustawiający informacje o użytkowniku oraz jego praw
     * @param user użytkownik
     * */
    public User(User user){
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.roles = user.getRoles();
    }

    /**
     * Metoda pobiera ID użytkownika.
     * @return userId ID użytkownika.
     * */
    public Long getUserId() {
        return userId;
    }

    /**
     * Metoda ustawia ID użytkownika.
     * @param userId ID użytkownika.
     * */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Metoda pobiera imie użytkownika.
     * @return firstName imie użytkownika.
     * */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Metoda ustawia imie użytkownika.
     * @param firstName imie użytkownika.
     * */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Metoda pobiera nazwisko użytkownika.
     * @return lastName nazwisko użytkownika.
     * */
    public String getLastName() {
        return lastName;
    }

    /**
     * Metoda ustawia nazwisko użytkownika.
     * @param lastName nazwisko użytkownika.
     * */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Metoda pobiera email użytkownika.
     * @return email email użytkownika.
     * */
    public String getEmail() {
        return email;
    }

    /**
     * Metoda ustawia email użytkownika.
     * @param email email użytkownika.
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metoda pobiera hasło użytkownika.
     * @return password hasło użytkownika.
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Metoda ustawia hasło użytkownika.
     * @param password hasło użytkownika.
     * */
    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encodedPassword = encoder.encode(password);
        this.password = encodedPassword;
    }

    /**
     * Metoda pobiera stan aktywności użytkownika.
     * @return active stan aktywności użytkownika czyli true jeżeli aktywnu lub false jeżeli nie.
     * */
    public boolean isActive() {
        return active;
    }

    /**
     * Metoda ustawia stan aktywności użytkownika.
     * @param active stan aktywności użytkownika.
     * */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Metoda pobiera prawa dostępu użytkownika.
     * @return roles prawa dostępu użytkownika.
     * */
    public String getRoles() {
        return roles;
    }

    /**
     * Metoda ustawia prawa dostępu użytkownika.
     * @param roles prawa dostępu użytkownika.
     * */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * Metoda pozwalająca zmienić hasło użytkownika.
     * @param password nowe hasło użytkownika
     * */
    public void changePassword(String password) {
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String password, boolean active, String roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }
}
