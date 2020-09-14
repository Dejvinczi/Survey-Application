package pl.surveyapplication.model;

import javax.persistence.*;

/**
 * @author Dawid
 * @version 1.0
 * Klasa przechowująca połączenia survey - user.
 * */
@Entity
@Table(name = "CONNECTION")
public class Connection {
    /**
     * Zmienna przechowuje ID połączenia
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "CONNECTION_ID")
    private Long connectionId;

    /**
     * Zmienna przechowuje użytkownika
     * */
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "USER_ID")
    private User userId;

    /**
     * Zmienna przechowuje ankiete
     * */
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "SURVEY_ID")
    private Survey surveyId;

    /**
     * Zmienna przechowuje informacje o odesłaniu ankiety w postaci true lub false
     * */
    @Column(name = "DEVOTED")
    private boolean devoted;

    /**
     * Konstruktor bezparametrowy
     * */
    public Connection(){}

    /**
     * Metoda pobiera ID połączenia.
     * @return connectionId ID połączenia.
     * */
    public Long getConnectionId() {
        return connectionId;
    }

    /**
     * Metoda ustawia ID połączenia.
     * @param connectionId ID połączenia.
     * */
    public void setConnectionId(Long connectionId) {
        this.connectionId = connectionId;
    }

    /**
     * Metoda pobiera uzytkownika.
     * @return userId czyli obiekt klasy USER.
     * */
    public User getUser() {
        return userId;
    }

    /**
     * Metoda ustawia użytkownika w połączeniu.
     * @param user czyli obiekt klasy USER.
     * */
    public void setUser(User user) {
        this.userId = user;
    }

    /**
     * Metoda pobiera ankiete.
     * @return surveyId czyli obiekt klasy SURVEY.
     * */
    public Survey getSurvey() {
        return surveyId;
    }

    /**
     * Metoda ustawia ankiete.
     * @param survey czyli obiekt klasy SURVEY.
     * */
    public void setSurvey(Survey survey) {
        this.surveyId = survey;
    }

    /**
     * Metoda pobiera stan odesłania ankiety.
     * @return devoted czyli true jeżeli wysłane lub false jeżeli nie.
     * */
    public boolean isDevoted() {
        return devoted;
    }

    /**
     * Metoda ustawia stan odesłania ankiety.
     * @param devoted czyli true lub false.
     * */
    public void setDevoted(boolean devoted) {
        this.devoted = devoted;
    }
}
