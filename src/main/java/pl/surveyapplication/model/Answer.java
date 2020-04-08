package pl.surveyapplication.model;

import javax.persistence.*;

@Entity
@Table(name = "ANSWERS")
public class Answer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID")
        private int answerId;
    @Column(name = "ANSWER")
        private String answer;

    public Answer(){}

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
