package pl.cp.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pl.first.firstjava.*;

import java.io.IOException;
import java.util.List;

public class App extends Application {
    public static void main() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader louder = new FXMLLoader(this.getClass().getResource("/mainWindow.fxml"));
        StackPane pane = louder.load();
        Scene scene = new Scene(pane,600,500);
        stage.setScene(scene);
        stage.setTitle("SudokuGame");
        stage.show();
    }
}
