package pl.surveyapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.surveyapplication.model.Survey;

import java.util.List;

@Repository
public interface SurveyRepository extends CrudRepository<Survey,Integer> {
    @Override
    List<Survey> findAll();
}
