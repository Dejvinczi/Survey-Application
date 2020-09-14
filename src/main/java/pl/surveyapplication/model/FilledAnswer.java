package pl.surveyapplication.model;

import javax.persistence.*;

/**
 * @author Dawid
 * @version 1.0
 * Klasa przechowująca treści uzupełnionych odpowiedzi.
 * */
@Entity
@Table(name = "FILLED_ANSWER")
public class FilledAnswer {
    /**
     * Zmienna przechowuje id odpowiedzi
     * */
    @Id
    @Column(name = "ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answerId;

    /**
     * Zmienna przechowuje treść odpowiedzi
     * */
    @Column(name = "ANSWER")
    private String answer;

    /**
     * Zmienna przechowuje informacje o zaznaczeniu tej odpowiedzi
     * */
    @Column(name = "IS_CHECK")
    private boolean check;

    /**
     * Konstruktor bezparametrowy
     * */
    public FilledAnswer(){}

    /**
     * Metoda pobiera ID uzupełnionej odpowiedzi.
     * @return answerId ID uzupełnionej odpowiedzi.
     * */
    public Long getAnswerId() {
        return answerId;
    }

    /**
     * Metoda ustawia ID uzupełnionej odpowiedzi.
     * @param answerId ID uzupełnionej odpowiedzi.
     * */
    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    /**
     * Metoda pobiera treść uzupełnionej odpowiedzi.
     * @return answer czyli treść uzupełnionej odpowiedzi.
     * */
    public String getAnswer() {
        return answer;
    }

    /**
     * Metoda ustawia treść uzupełnionej odpowiedzi.
     * @param answer treść uzupełnionej odpowiedzi.
     * */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Metoda pobiera informacje o zaznaczeniu tej odpowiedzi.
     * @return check czyli true jeżeli ta odpowiedz jest zaznaczona lub false jeżeli nie.
     * */
    public boolean isCheck() {
        return check;
    }

    /**
     * Metoda ustawia informacje o zaznaczeniu tej odpowiedzi.
     * @param check informacje o zaznaczeniu tej odpowiedzi, true jeżeli zaznaczona lub false jeżeli nie.
     * */
    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "FilledAnswer{" +
                ", answer='" + answer + '\'' +
                ", check=" + check +
                '}';
    }
}
