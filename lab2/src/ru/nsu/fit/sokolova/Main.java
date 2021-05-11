package ru.nsu.fit.sokolova;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.fit.sokolova.views.ViewSettings;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(ViewSettings.INPUT_FORM_PATH));
        primaryStage.setTitle(ViewSettings.PROGRAM_TITLE);
        primaryStage.setScene(new Scene(root, ViewSettings.INPUT_FORM_WIDTH, ViewSettings.INPUT_FORM_HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}