package pl.cp.view;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class MainController {

    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize() {
        loadMenuWindow();
    }

    public void loadMenuWindow() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/menuWindow.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles/myBundle");
        loader.setResources(bundle);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MenuController menu = loader.getController();
        menu.setBundle(bundle);
        menu.setMenu(this);
        setWindow(pane);
    }

    public void setWindow(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }
}
