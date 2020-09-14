package pl.surveyapplication.model;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dawid
 * @version 1.0
 * Klasa przechowująca treści uzupełnionych pytań.
 * */
@Entity
@Table(name = "FILLED_QUESTION")
public class FilledQuestion {
    /**
     * Zmienna przechowuje id pytania
     * */
    @Id
    @Column(name = "QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long questionId;

    @Column(name = "QUESTION")
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_ORIGIN")
    private List<FilledAnswer> filledAnswers = new ArrayList<>();

    /**
     * Konstruktor bezparametrowy
     * */
    public FilledQuestion(){}

    /**
     * Metoda pobiera ID uzupełnionego pytania.
     * @return questionId ID uzupełnionego pytania.
     * */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * Metoda ustawia ID uzupełnionego pytania.
     * @param questionId ID uzupełnionego pytania.
     * */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * Metoda pobiera treść uzupełnionego pytania.
     * @return question czyli treść uzupełnionego pytania.
     * */
    public String getQuestion() {
        return question;
    }

    /**
     * Metoda ustawia treść uzupełnionego pytania.
     * @param question treść uzupełnionego pytania.
     * */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Metoda liste uzupełnionych pytań.
     * @return filledAnswer lista uzupełnionych pytań.
     * */
    public List<FilledAnswer> getFilledAnswers() {
        return filledAnswers;
    }

    /**
     * Metoda dodaje liste uzupełnionych pytań.
     * @param filledAnswers lista uzupełnionych pytań.
     * */
    public void setFilledAnswers(List<FilledAnswer> filledAnswers){
        this.filledAnswers = filledAnswers;
    }

    public FilledAnswer getFilledAnswer(int index) {
        return filledAnswers.get(index);
    }
}
