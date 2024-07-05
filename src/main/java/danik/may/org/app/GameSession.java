package danik.may.org.app;

import danik.may.org.controller.AppController;
import danik.may.org.loader.FileLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameSession extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppController appController = new AppController(primaryStage);
        appController.updateScene();

        FileLoader fileLoader = new FileLoader();
        String jsonData="yrffr";
        fileLoader.save(jsonData);
    }
}
