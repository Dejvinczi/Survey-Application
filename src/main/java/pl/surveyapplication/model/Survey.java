package pl.surveyapplication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dawid
 * @version 1.0
 * Klasa przechowująca treści ankiet.
 * */
@Entity
@Table(name = "SURVEYS")
public class Survey{
    /**
     * Zmienna przechowuje id ankiety
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "SURVEY_ID")
    private Long surveyId;

    /**
     * Zmienna przechowuje treść nazwy ankiety
     * */
    @Column(name = "SURVEY_NAME")
    private String surveyName;

    /**
     * Lista pytań
     * */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SURVEY_ORIGIN")
    private List<Question> questions = new ArrayList<>();
    /**
     * Konstruktor bezparametrowy
     * */
    public Survey(){}
    /**
     * Konstruktor tworzący obiekt Survey na podstawie innego obiektu Survey
     * @param survey Ankieta klasy Survey
     * */
    public Survey(Survey survey){
        this.surveyId = survey.getSurveyId();
        this.surveyName = survey.getSurveyName();
        this.questions = survey.getQuestions();
    }

    /**
     * Metoda pobiera ID  ankiety.
     * @return surveyId ID  ankiety.
     * */
    public Long getSurveyId() {
        return surveyId;
    }

    /**
     * Metoda ustawia ID  ankiety.
     * @param surveyId ID  ankiety.
     * */
    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    /**
     * Metoda pobiera treść  ankiety.
     * @return surveyName czyli treść  ankiety.
     * */
    public String getSurveyName() {
        return surveyName;
    }

    /**
     * Metoda ustawia treść  ankiety.
     * @param surveyName treść  ankiety.
     * */
    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    /**
     * Metoda liste odpowiedzi.
     * @return questions lista odpowiedzi.
     * */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Metoda dodaje liste odpowiedzi.
     * @param questions lista odpowiedzi.
     * */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Metoda tworzy klase FilledSurvey na podstawie ankiety klasy Survey.
     * @return filledSurvey ankiete klasy FilledSurvey.
     * */
    public FilledSurvey getTemplate(){
        FilledSurvey filledSurvey = new FilledSurvey();
        filledSurvey.setSurveyName(this.surveyName);

        List<FilledQuestion> filledQuestionList = new ArrayList<>();
        for(Question question: questions){
            FilledQuestion filledQuestion = new FilledQuestion();
            filledQuestion.setQuestion(question.getQuestion());

            List<FilledAnswer> filledAnswerList = new ArrayList<>();
            for(Answer answer: question.getAnswers()){
                FilledAnswer filledAnswer = new FilledAnswer();
                filledAnswer.setAnswer(answer.getAnswer());
                filledAnswer.setCheck(false);
                filledAnswerList.add(filledAnswer);
            }
            filledQuestion.setFilledAnswers(filledAnswerList);
            filledQuestionList.add(filledQuestion);
        }
        filledSurvey.setFilledQuestions(filledQuestionList);
        return filledSurvey;
    }
}
