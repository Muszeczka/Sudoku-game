package pl.first.firstjava;

import java.util.ListResourceBundle;

public class LanguageException_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"InvalidValueField", "Invalid value entered in the field."},
                {"InvalidValue", "Invalid value int: "},
                {"lackClass", "The saved board is incorrect: "},
                {"invalidSL", "Invalid operation on save files: "}
        };
    }
}
