package pl.surveyapplication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dawid
 * @version 1.0
 * Klasa przechowująca treści pytań.
 * */
@Entity
@Table(name = "QUESTIONS")
public class Question{
    /**
     * Zmienna przechowuje id pytania
     * */
    @Id
    @Column(name = "QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int questionId;

    /**
     * Zmienna przechowuje treść pytania
     * */
    @Column(name = "QUESTION")
    private String question;

    /**
     * Lista przechowująca odpowiedzi
     * */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_ORIGIN")
    private List<Answer> answers = new ArrayList<>();

    /**
     * Konstruktor bezparametrowy
     * */
    public Question() {
    }

    /**
     * Metoda pobiera ID pytania.
     * @return questionId ID pytania.
     * */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Metoda ustawia ID pytania.
     * @param questionId ID pytania.
     * */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    /**
     * Metoda pobiera treść pytania.
     * @return question czyli treść pytania.
     * */
    public String getQuestion() {
        return question;
    }

    /**
     * Metoda ustawia treść pytania.
     * @param question treść pytania.
     * */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Metoda liste pytań.
     * @return answers lista pytań.
     * */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Metoda dodaje liste pytań.
     * @param answers lista pytań.
     * */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
