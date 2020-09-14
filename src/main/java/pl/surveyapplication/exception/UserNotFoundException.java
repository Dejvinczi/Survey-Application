package pl.surveyapplication.exception;

/**
 * @author Dawid
 * @version 1.0
 * Klasa tworząca informacje która ma być wypisana przy wyjątku brakuje danego użytkownika.
 * */
public class UserNotFoundException extends RuntimeException {
    /**
     * Metoda ustawia wiadomość jaka ma się wyswietlić podczas przechwycenia błędu.
     * @param message Treść błędu.
     * */
    public UserNotFoundException(String message){
        super(message);
    }
}

