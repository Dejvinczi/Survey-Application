package pl.surveyapplication.model;

import javax.persistence.*;

/**
 * @author Dawid
 * @version 1.0
 * Klasa przechowująca treści odpowiedzi.
 * */
@Entity
@Table(name = "ANSWERS")
public class Answer{
    /**
     * Zmienna przechowuje id odpowiedzi
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ANSWER_ID")
    private int answerId;

    /**
     * Zmienna przechowuje treść odpowiedzi
     * */
    @Column(name = "ANSWER")
    private String answer;

    /**
     * Konstruktor bezparametrowy
     * */
    public Answer() {
    }

    /**
     * Metoda pobiera ID odpowiedzi.
     * @return answerId czyli ID odpowiedzi.
     * */
    public int getAnswerId() {
        return answerId;
    }

    /**
     * Metoda ustawia ID odpowiedzi.
     * @param answerId ID odpowiedzi.
     * */
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    /**
     * Metoda pobiera treść odpowiedzi.
     * @return answer czyli treść odpowiedzi.
     * */
    public String getAnswer() {
        return answer;
    }

    /**
     * Metoda ustawia treść odpowiedzi.
     * @param answer Treść odpowiedzi.
     * */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}