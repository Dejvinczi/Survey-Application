package pl.surveyapplication.exception;

/**
 * @author Dawid
 * @version 1.0
 * Klasa przechowująca treści błedów na stronie.
 * */
public class ApplicationError {
    /**
     * Zmienna przechowuje kod błędu
     * */
    private int code;
    /**
     * Zmienna przechowuje treść błędu
     * */
    private String messeage;
    /**
     * Zmienna przechowuje możliwości naprawy błędu
     * */
    private String details;

    /**
     * Metoda pobiera kod błędu.
     * @return code czyli kod błędu.
     * */
    public int getCode() {
        return code;
    }

    /**
     * Metoda ustawia kod błędu.
     * @param code Kod błędu.
     * */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Metoda pobiera treść błędu.
     * @return messeage czyli treść błędu.
     * */
    public String getMesseage() {
        return messeage;
    }

    /**
     * Metoda ustawia treść błędu.
     * @param messeage Treść błędu.
     * */
    public void setMesseage(String messeage) {
        this.messeage = messeage;
    }

    /**
     * Metoda pobiera możliwości naprawy błedu.
     * @return details czyli możliwości naprawy błedu.
     * */
    public String getDetails() {
        return details;
    }

    /**
     * Metoda ustawia informacje o możliwej naprawie błędu.
     * @param details Informacje o możliwej naprawie błędu.
     * */
    public void setDetails(String details) {
        this.details = details;
    }
}
