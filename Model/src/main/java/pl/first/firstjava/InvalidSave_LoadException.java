package pl.first.firstjava;

import java.io.IOException;
import java.util.ResourceBundle;

public class InvalidSave_LoadException  extends IOException {
    private ResourceBundle listBundle =
            ResourceBundle.getBundle("pl.first.firstjava.LanguageException");

    public InvalidSave_LoadException(String s, Throwable ex) {
        super(s, ex);
    }

    @Override
    public String getLocalizedMessage() {
        return listBundle.getString(getMessage());
    }
}
