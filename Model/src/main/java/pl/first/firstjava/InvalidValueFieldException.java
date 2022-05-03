package pl.first.firstjava;

import java.util.ResourceBundle;

public class InvalidValueFieldException extends InvalidValueException {
    private ResourceBundle listBundle =
            ResourceBundle.getBundle("pl.first.firstjava.LanguageException");

    public InvalidValueFieldException(String message) {
        super(message);
    }

    @Override
    public String getLocalizedMessage() {

        return listBundle.getString(getMessage());
    }

}
