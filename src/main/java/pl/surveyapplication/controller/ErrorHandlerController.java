package pl.surveyapplication.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.surveyapplication.exception.ApplicationError;
import pl.surveyapplication.exception.SurveyNotFoundException;
import pl.surveyapplication.exception.UserNotFoundException;

@ControllerAdvice
@RestController
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    @Value("${api_error_not_found_survey_details}")
    private String detailsSurvey;

    @Value("${api_error_not_found_user_details}")
    private String detailsUser;

    @ExceptionHandler(SurveyNotFoundException.class)
    public ResponseEntity<ApplicationError> handleSurveyNotFoundException(SurveyNotFoundException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();
        error.setCode(100);
        error.setMesseage(exception.getMessage());
        error.setDetails(detailsSurvey);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApplicationError> handleUserNotFoundException (UserNotFoundException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();
        error.setCode(100);
        error.setMesseage(exception.getMessage());
        error.setDetails(detailsUser);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }



}
