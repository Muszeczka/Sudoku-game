package pl.cp.view;

import java.util.ListResourceBundle;

public class Autors extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{{"1", "Piotr Chłapiński"},
                {"2", "Marcin Muszyński"}};
    }
}
