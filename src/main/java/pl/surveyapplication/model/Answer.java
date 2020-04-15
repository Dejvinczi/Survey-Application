package pl.surveyapplication.model;

import javax.persistence.*;

/**
 * Obiekt <code>Answer</code> reprezetuje jedna z odpowiedzi na jakieś pytanie.
 * @author Dawid Gurgul
 * @version 1.0
 */

@Entity
@Table(name = "ANSWERS")
public class Answer{
    /**
     *Zmienna odpowiadajaca za ID odpowiedzi
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID")
        private int answerId;
    /**
     *Zmienna odpowiadajaca za tekst odpowiedzi
     */
    @Column(name = "ANSWER")
        private String answer;

    /**
     * Konstruktor bezparametrowy
     */
    public Answer(){}

    /**
     * Pobieranie wartosci ID obiektu
     * @return answerId
     */
    public int getAnswerId() {
        return answerId;
    }

    /**
     * Ustawienie ID obiektu
     * @param answerId - wartosc na ktora bedziemy ustawiac zmienna ID
     */
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    /**
     * Zwrócenie ciągu tekstowego odpowiedzi
     * @return answer - zwraca odpowiedz
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Ustawienie ciągu tekstowego odpowiedzi
     * @param answer - wartosc na ktora bedziemy ustawiac nasza odpowiedz
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
