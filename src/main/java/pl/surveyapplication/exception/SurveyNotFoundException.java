package pl.surveyapplication.exception;

/**
 * @author Dawid
 * @version 1.0
 * Klasa tworząca informacje która ma być wypisana przy wyjątku brakuje danej ankiety.
 * */
public class SurveyNotFoundException extends RuntimeException {
    /**
     * Metoda ustawia wiadomość jaka ma się wyswietlić podczas przechwycenia błędu.
     * @param message Treść błędu.
     * */
    public SurveyNotFoundException(String message){
       super(message);
    }

}
