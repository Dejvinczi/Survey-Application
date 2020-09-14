package pl.surveyapplication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dawid
 * @version 1.0
 * Klasa przechowująca treści uzupełnionych ankiet.
 * */
@Entity
@Table(name = "FILLED_SURVEY")
public class FilledSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "SURVEY_ID")
    private Long surveyId;

    @JoinColumn(name = "HASH")
    private String hash;

    @Column(name = "SURVEY_NAME")
    private String surveyName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SURVEY_ORIGIN")
    private List<FilledQuestion> filledQuestions = new ArrayList<>();


    public FilledSurvey(){}

    /**
     * Metoda pobiera ID uzupełnionej ankiety.
     * @return surveyId ID uzupełnionej ankiety.
     * */
    public Long getSurveyId() {
        return surveyId;
    }

    /**
     * Metoda ustawia ID uzupełnionej ankiety.
     * @param surveyId ID uzupełnionej ankiety.
     * */
    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    /**
     * Metoda pobiera token ankiety.
     * @return hash czyli token.
     * */
    public String getHash() {
        return hash;
    }

    /**
     * Metoda ustawia token odnoszący się do ankiety.
     * @param hash token.
     * */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Metoda pobiera treść uzupełnionej ankiety.
     * @return surveyName czyli treść uzupełnionej ankiety.
     * */
    public String getSurveyName() {
        return surveyName;
    }

    /**
     * Metoda ustawia treść uzupełnionej ankiety.
     * @param surveyName treść uzupełnionej ankiety.
     * */
    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    /**
     * Metoda liste uzupełnionych odpowiedzi.
     * @return filledQuestions lista uzupełnionych odpowiedzi.
     * */
    public List<FilledQuestion> getFilledQuestions() {
        return filledQuestions;
    }

    /**
     * Metoda dodaje liste uzupełnionych odpowiedzi.
     * @param filledQuestions lista uzupełnionych odpowiedzi.
     * */
    public void setFilledQuestions(List<FilledQuestion> filledQuestions) {
        this.filledQuestions = filledQuestions;
    }

    public FilledQuestion getFilledQuestion(int index){
        return filledQuestions.get(index);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
