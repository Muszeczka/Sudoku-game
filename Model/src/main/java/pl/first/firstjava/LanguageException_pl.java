package pl.first.firstjava;

import java.util.ListResourceBundle;

public class LanguageException_pl extends ListResourceBundle  {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"InvalidValueField", "Niepoprawna wartość wpisana do pola."},
                {"InvalidValue", "Niepoprawna wartość int: "},
                {"lackClass", "Zapisana plansza jest niepoprawna: "},
                {"invalidSL", "Niepoprawna operacja na plikach zapisu: "}
        };
    }
}
