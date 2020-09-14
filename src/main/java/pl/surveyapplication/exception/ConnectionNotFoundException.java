package pl.surveyapplication.exception;

/**
 * @author Dawid
 * @version 1.0
 * Klasa tworząca informacje która ma być wypisana przy wyjątku brakuje danego połączenia user - survey.
 * */
public class ConnectionNotFoundException extends RuntimeException {
    /**
     * Metoda ustawia wiadomość jaka ma się wyswietlić podczas przechwycenia błędu.
     * @param message Treść błędu.
     * */
    public ConnectionNotFoundException(String message){
        super(message);
    }
}
