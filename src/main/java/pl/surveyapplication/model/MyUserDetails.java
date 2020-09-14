package pl.surveyapplication.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dawid
 * @version 1.0
 * Klasa przechowująca informacje o użytkowniku i jego prawach dostępu.
 * */
public class MyUserDetails implements UserDetails {
    /**
     * Zmienna przechowuje id użytkownika
     * */
    private Long id;
    /**
     * Zmienna przechowuje imie użytkownika
     * */
    private String firstName;
    /**
     * Zmienna przechowuje nazwisko użytkownika
     * */
    private String lastName;
    /**
     * Zmienna przechowuje nazwe użytkownika
     * */
    private String userName;
    /**
     * Zmienna przechowuje hasło użytkownika
     * */
    private String password;
    /**
     * Zmienna przechowuje stan aktywności użutkownika
     * */
    private boolean active;
    /**
     * Zmienna przechowuje prawa dostępu do zasobów użytkownika
     * */
    private List<GrantedAuthority> authorities;

    /**
     * Konstruktor ustawiający informacje o użytkowniku oraz jego praw
     * @param user użytkownik
     * */
    public MyUserDetails(User user) {
        this.id = user.getUserId();
        this.userName = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.active = user.isActive();
        StringBuilder sb = new StringBuilder();
        sb.append("ROLE_");
        sb.append(user.getRoles());
        this.authorities = Arrays.stream(sb.toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Konstruktor bezparametrowy
     * */
    public MyUserDetails() {}

    /**
     * Metoda pobiera liste praw dostępu użytkownika.
     * @return authorities lista praw dostępu użytkownika.
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Metoda pobiera hasło użytkownika.
     * @return password hasło użytkownika.
     * */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Metoda pobiera nazwa użytkownika.
     * @return userName nazwa użytkownika.
     * */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * Metoda pobiera ID użytkownika.
     * @return id ID użytkownika.
     * */
    public Long getId(){return id;}


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return active;
    }
}
