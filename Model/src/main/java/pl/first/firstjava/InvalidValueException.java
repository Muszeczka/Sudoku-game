package pl.first.firstjava;

import java.util.ResourceBundle;

public class InvalidValueException extends Exception {
    private ResourceBundle listBundle =
            ResourceBundle.getBundle("pl.first.firstjava.LanguageException");

    public InvalidValueException(String message) {
        super(message);
    }

    @Override
    public String getLocalizedMessage() {

        return listBundle.getString("InvalidValue") + getMessage();
    }
}
