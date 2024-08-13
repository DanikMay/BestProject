package danik.may.org.app;

import danik.may.org.controller.AppController;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameSession extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AppController appController = new AppController(primaryStage);
        appController.updateScene();
    }
}
