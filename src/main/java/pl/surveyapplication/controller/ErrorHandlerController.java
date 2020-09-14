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
import pl.surveyapplication.exception.ConnectionNotFoundException;
import pl.surveyapplication.exception.SurveyNotFoundException;
import pl.surveyapplication.exception.UserNotFoundException;

/**
 * @author Dawid
 * @version 1.0
 * Klasa kontrolera błędów na stronie.
 * */
@ControllerAdvice
@RestController
public class ErrorHandlerController extends ResponseEntityExceptionHandler {
    /**
     * Zmienna odwolujaca się informacji przy błędzie brakuje danej ankiety
     * */
    @Value("${api_error_not_found_survey_details}")
    private String detailsSurvey;
    /**
     * Zmienna odwolujaca się informacji przy błędzie brakuje danego użytkownika
     * */
    @Value("${api_error_not_found_user_details}")
    private String detailsUser;
    /**
     * Zmienna odwolujaca się informacji przy błędzie brakuje danego połączenia user - survey
     * */
    @Value("${api_error_not_found_connection_details}")
    private String detailsConnection;
    /**
     * Metoda przechwutuje błędy przy wyszukiwaniu ankiety.
     * @param exception Błąd jaki ma się wyswietlić
     * @param webRequest Pytanie w którym może generować się błąd.
     * @return Błąd w postaci HTTP NOT FOUND 404 z naszymi informacjami w nim.
     * */
    @ExceptionHandler(SurveyNotFoundException.class)
    public ResponseEntity<ApplicationError> handleSurveyNotFoundException(SurveyNotFoundException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();
        error.setCode(100);
        error.setMesseage(exception.getMessage());
        error.setDetails(detailsSurvey);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Metoda przechwutuje błędy przy wyszukiwaniu użytkownika.
     * @param exception Błąd jaki ma się wyswietlić
     * @param webRequest Pytanie w którym może generować się błąd.
     * @return Błąd w postaci HTTP NOT FOUND 404 z naszymi informacjami w nim.
     * */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApplicationError> handleUserNotFoundException (UserNotFoundException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();
        error.setCode(100);
        error.setMesseage(exception.getMessage());
        error.setDetails(detailsUser);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Metoda przechwutuje błędy przy wyszukiwaniu połączenia user - survey.
     * @param exception Błąd jaki ma się wyswietlić
     * @param webRequest Pytanie w którym może generować się błąd.
     * @return Błąd w postaci HTTP NOT FOUND 404 z naszymi informacjami w nim.
     * */
    @ExceptionHandler(ConnectionNotFoundException.class)
    public ResponseEntity<ApplicationError> handleConnectionNotFoundException (UserNotFoundException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();
        error.setCode(100);
        error.setMesseage(exception.getMessage());
        error.setDetails(detailsConnection);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }



}
