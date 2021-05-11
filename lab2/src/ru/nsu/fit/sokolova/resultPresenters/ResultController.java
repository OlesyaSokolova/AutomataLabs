package ru.nsu.fit.sokolova.resultPresenters;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import ru.nsu.fit.sokolova.algorithms.AlgorithmName;
import ru.nsu.fit.sokolova.resultPresenters.preparers.ResultPreparer;
import ru.nsu.fit.sokolova.views.ViewSettings;

import java.io.IOException;


public class ResultController
{
    @FXML
    private Button startButton;

    @FXML
    private HBox results;


    @FXML
    void initialize()
    {
        startButton.setOnAction(event ->
        {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(ViewSettings.INPUT_FORM_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle(ViewSettings.PROGRAM_TITLE);
            stage.setScene(new Scene(root, ViewSettings.INPUT_FORM_WIDTH, ViewSettings.INPUT_FORM_HEIGHT));
            stage.show();
        });
    }

    public void setPane(Pane value, AlgorithmName title)
    {
        results.setHgrow(value, Priority.ALWAYS);
        value.setMaxWidth(ViewSettings.LETTER_WIDTH * ResultPreparer.getTitle(title).length());
        results.getChildren().add(value);
    }
}

