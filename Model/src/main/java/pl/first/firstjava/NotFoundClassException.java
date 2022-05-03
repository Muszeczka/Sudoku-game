package pl.first.firstjava;

import java.util.ResourceBundle;

public class NotFoundClassException extends ClassNotFoundException {
    private ResourceBundle listBundle =
            ResourceBundle.getBundle("pl.first.firstjava.LanguageException");

    public NotFoundClassException(String s, Throwable ex) {
        super(s, ex);
    }

    @Override
    public String getLocalizedMessage() {
        return listBundle.getString(getMessage());
    }
}
