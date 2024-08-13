package danik.may.org.controller;

import danik.may.org.constant.Settings;
import danik.may.org.entity.Person;
import danik.may.org.map.DataMapper;
import danik.may.org.scroller.DataScroller;
import danik.may.org.storage.CharactersStorage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class AppController {

    private static final String ENTER_INT_VALUE = "Enter intelligence value";
    private static final String ENTER_AGILITY_VALUE = "Enter agility value";
    private static final String ENTER_STRENGTH_VALUE = "Enter strength value";
    private static final String ENTER_SPEC_VALUE = "Enter specialization value";
    private static final String BATTLE_CRY = "Enter battle-cry";

    private Stage primaryStage;
    private DataScroller scroller = new DataScroller();
    private TextField intelligenceTextField = null;
    private TextField agilityTextField = null;
    private TextField strengthTextField = null;
    private TextField specializationTextField = null;
    private TextField battleCryTextField = null;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void next() {
        Person updatePerson = new Person();
        DataMapper.updatePerson(updatePerson,
                (intelligenceTextField.getText()),
                (agilityTextField.getText()),
                (strengthTextField.getText()),
                specializationTextField.getText());
        scroller.chooseNextPerson(updatePerson);
        updateScene();
    }

    private void previous() {
        Person updatePerson = new Person();
        DataMapper.updatePerson(updatePerson,
                (intelligenceTextField.getText()),
                (agilityTextField.getText()),
                (strengthTextField.getText()),
                specializationTextField.getText());
        scroller.choosePreviousPerson(updatePerson);
        updateScene();
    }

    private void saved() {
        Person updatePerson = new Person();
        DataMapper.updatePerson(updatePerson,
                (intelligenceTextField.getText()),
                (agilityTextField.getText()),
                (strengthTextField.getText()),
                specializationTextField.getText());
        scroller.chooseCurrentPerson(updatePerson);
        updateScene();
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

        Button buttonSaved = new Button();
        buttonSaved.setText("Saved");
        buttonSaved.setPrefWidth(250);
        buttonSaved.setFont(new Font(25));
        buttonSaved.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saved();
                System.out.println("save button pressed");
                CharactersStorage.getState().saveData();
            }
        });

        GridPane nextPrevGrid = new GridPane();
        nextPrevGrid.add(buttonPrevious, 0, 0);
        nextPrevGrid.add(buttonNext, 1, 0);

        GridPane savedGrid = new GridPane();
        savedGrid.add(buttonSaved, 0, 0);
        savedGrid.setPadding(new Insets(0, 0, 0, 150));

        GridPane characteristicsGrid = new GridPane();

        Label intelligenceLabel = new Label("Intelligence");
        Label agilityLabel = new Label("Agility");
        Label strengthLabel = new Label("Strength");
        Label specializationLabel = new Label("Specialization");
        Label battleCryLabel = new Label("Battle-cry");

        intelligenceLabel.setFont(new Font(40));
        agilityLabel.setFont(new Font(40));
        strengthLabel.setFont(new Font(40));
        specializationLabel.setFont(new Font(40));
        battleCryLabel.setFont(new Font(40));

        characteristicsGrid.add(intelligenceLabel, 0, 0);
        characteristicsGrid.add(agilityLabel, 0, 1);
        characteristicsGrid.add(strengthLabel, 0, 2);
        characteristicsGrid.add(specializationLabel, 0, 3);
        characteristicsGrid.add(battleCryLabel, 0, 4);

        characteristicsGrid.setMinSize(500, 500);
        characteristicsGrid.setAlignment(Pos.CENTER);
        characteristicsGrid.setVgap(30);
        characteristicsGrid.setHgap(30);

        String intValue = person.getIntelligence() == 0 ? ENTER_INT_VALUE : "" + person.getIntelligence();
        String agilityValue = person.getAgility() == 0 ? ENTER_AGILITY_VALUE : "" + person.getAgility();
        String strengthValue = person.getStrength() == 0 ? ENTER_STRENGTH_VALUE : "" + person.getStrength();
        String specValue = person.getSpecialization() == null && person.getSpecialization().isEmpty()
                ? ENTER_SPEC_VALUE : person.getSpecialization();

        intelligenceTextField = new TextField(intValue);
        agilityTextField = new TextField(agilityValue);
        strengthTextField = new TextField(strengthValue);
        specializationTextField = new TextField(specValue);
        battleCryTextField = new TextField(BATTLE_CRY);
//        Label intelligenceValue = new Label(person.getIntelligence() + "");
//        Label agilityValue = new Label(person.getAgility() + "");
//        Label strengthValue = new Label(person.getStrength() + "");
//        Label specializationValue = new Label(person.getSpecialization());

//        intelligenceValue.setFont(new Font(40));
//        agilityValue.setFont(new Font(40));
//        strengthValue.setFont(new Font(40));
//        specializationValue.setFont(new Font(40));

        characteristicsGrid.add(intelligenceTextField, 1, 0);
        characteristicsGrid.add(agilityTextField, 1, 1);
        characteristicsGrid.add(strengthTextField, 1, 2);
        characteristicsGrid.add(specializationTextField, 1, 3);
        characteristicsGrid.add(battleCryTextField, 1, 4);

        Image image = null;

        try {
            image = new Image(Files.newInputStream(Paths.get(person.getPathForImage())));
        } catch (Exception exception) {

        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(500);
        imageView.setFitWidth(500);

        GridPane mainGrid = new GridPane();
        mainGrid.setPrefSize(1000, 1000);
        mainGrid.add(characteristicsGrid, 1, 0);
        mainGrid.add(imageView, 0, 0);
        mainGrid.add(nextPrevGrid, 0, 1);
        mainGrid.add(savedGrid, 1, 1);
        mainGrid.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(mainGrid);
        Scene scene = new Scene(root, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        scene.getStylesheets().add("dark-theme.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
