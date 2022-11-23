package amalfi.wgu_c482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/** This is my first javadoc comment */
public class Main extends Application {
    /** This is the start method. This is the first method called
     @param stage This is the stage passed to the start method*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/amalfi/wgu_c482/mainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("AMALFI Makes It");
        stage.getIcons().add(new Image("/icon3.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}