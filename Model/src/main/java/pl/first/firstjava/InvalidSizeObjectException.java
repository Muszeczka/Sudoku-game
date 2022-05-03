package pl.first.firstjava;

import java.util.ResourceBundle;

public class InvalidSizeObjectException extends InvalidValueException {
    private ResourceBundle listBundle =
            ResourceBundle.getBundle("pl.first.firstjava.LanguageException");

    public InvalidSizeObjectException(String message) {
        super(message);
    }

    @Override
    public String getLocalizedMessage() {

        return listBundle.getString(getMessage());
    }
}
