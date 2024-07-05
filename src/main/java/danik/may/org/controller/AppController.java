package danik.may.org.controller;

import danik.may.org.constant.Settings;
import danik.may.org.entity.Person;
import danik.may.org.scroller.DataScroller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class AppController {
    private Stage primaryStage;
    private DataScroller scroller = new DataScroller();

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void next() {
        scroller.chooseNextPerson();
        updateScene();
    }

    private void previous() {
        scroller.choosePreviousPerson();
        updateScene();
    }

    private void saveData(){

    }

    public void updateScene() {
        Person person = scroller.getCurrentPerson();
        primaryStage.setTitle("RPG game");
        Button buttonNext = new Button();
        buttonNext.setText("Next");
        buttonNext.setPrefWidth(250);
        buttonNext.setFont(new Font(25));
        buttonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                next();
            }
        });

        Button buttonPrevious = new Button();
        buttonPrevious.setText("Previous");
        buttonPrevious.setPrefWidth(250);
        buttonPrevious.setFont(new Font(25));
        buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                previous();
            }
        });

        GridPane nextPrevGrid = new GridPane();
        nextPrevGrid.add(buttonNext, 0, 0);
        nextPrevGrid.add(buttonPrevious, 1, 0);

        GridPane characteristicsGrid = new GridPane();

        Label intellegenceLabel = new Label("Intellegence");
        Label agilityLabel = new Label("Agility");
        Label strengthLabel = new Label("Strength");
        Label specializationLabel = new Label("Specialization");

        intellegenceLabel.setFont(new Font(40));
        agilityLabel.setFont(new Font(40));
        strengthLabel.setFont(new Font(40));
        specializationLabel.setFont(new Font(40));

        characteristicsGrid.add(intellegenceLabel, 0, 0);
        characteristicsGrid.add(agilityLabel, 0, 1);
        characteristicsGrid.add(strengthLabel, 0, 2);
        characteristicsGrid.add(specializationLabel, 0, 3);

        characteristicsGrid.setMinSize(500, 500);
        characteristicsGrid.setAlignment(Pos.CENTER);
        characteristicsGrid.setVgap(30);
        characteristicsGrid.setHgap(30);


        Label intellegenceValueLabel = new Label(person.getIntelligence() + "");
        Label agilityValueLabel = new Label(person.getAgility() + "");
        Label sthrengthValueLabel = new Label(person.getStrength() + "");
        Label specializationValueLabel = new Label(person.getSpecialization());

        intellegenceValueLabel.setFont(new Font(40));
        agilityValueLabel.setFont(new Font(40));
        sthrengthValueLabel.setFont(new Font(40));
        specializationValueLabel.setFont(new Font(40));

        characteristicsGrid.add(intellegenceValueLabel, 1, 0);
        characteristicsGrid.add(agilityValueLabel, 1, 1);
        characteristicsGrid.add(sthrengthValueLabel, 1, 2);
        characteristicsGrid.add(specializationValueLabel, 1, 3);

        Image image = null;

        try {
            image = new Image(Files.newInputStream(Paths.get(person.getPathForImage())));
        } catch (Exception exception) {

        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(500);
        imageView.setFitWidth(500);

        GridPane mainGrid = new GridPane();
        mainGrid.add(characteristicsGrid, 1, 0);
        mainGrid.add(imageView, 0, 0);
        mainGrid.add(nextPrevGrid, 0, 1);
        mainGrid.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(mainGrid);
        Scene scene = new Scene(root, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        scene.getStylesheets().add("dark-theme.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
