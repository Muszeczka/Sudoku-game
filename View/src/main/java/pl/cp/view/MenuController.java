package pl.cp.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class MenuController {

    @FXML
    private MainController main;

    @FXML
    private ResourceBundle bundle;

    @FXML
    private ComboBox language;

    @FXML
    private Label autorsLabel;

    private Autors autors;

    ObservableList<String> list = FXCollections.observableArrayList("English", "Polski");

    @FXML
    public void initialize() {
        language.setItems(list);
        autors = new Autors();
        autorsLabel.setText(autors.getObject("1") + "\n" + autors.getObject("2"));
    }

    @FXML
    public void easyMode() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gameWindow.fxml"));
        loader.setResources(bundle);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController game = loader.getController();
        game.setMain(main);
        game.setBundle(bundle);
        game.setLevel("easy");
        game.levelShowSudoku();
        main.setWindow(pane);
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @FXML
    public void mediumMode() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gameWindow.fxml"));
        loader.setResources(bundle);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController game = loader.getController();
        game.setMain(main);
        game.setBundle(bundle);
        game.setLevel("medium");
        game.levelShowSudoku();
        main.setWindow(pane);
    }

    @FXML
    public void hardMode() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gameWindow.fxml"));
        loader.setResources(bundle);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController game = loader.getController();
        game.setMain(main);
        game.setLevel("hard");
        game.setBundle(bundle);
        game.levelShowSudoku();
        main.setWindow(pane);
    }

    @FXML
    private void chose() {
        if (language.getValue().toString().compareTo("Polski") == 0) {
            Locale.setDefault(new Locale("pl"));
        } else {
            Locale.setDefault(new Locale("en"));
        }
        main.initialize();
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    public void setMenu(MainController main) {
        this.main = main;
    }
}
