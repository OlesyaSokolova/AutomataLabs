package ru.nsu.fit.sokolova.resultPresenters;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.nsu.fit.sokolova.algorithms.AlgorithmName;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;
import ru.nsu.fit.sokolova.views.ViewSettings;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResultShower
{
    private ResultsManager resultPresenters_;
    private ResultController resultController_;
    private FXMLLoader fxmlLoader_;

    public ResultShower()
    {
        resultPresenters_ = new ResultsManager();
    }
    public void show(HashMap<AlgorithmName, Grammar> results)
    {
        int resultsLength = 0;
        for(AlgorithmName algorithmName : results.keySet())
        {
            resultsLength += algorithmName.toString().length();
        }
        ViewSettings.setResultsSettings(results.size(), resultsLength);
        fxmlLoader_ = new FXMLLoader(getClass().getResource(ViewSettings.RESULT_PATH));
        Stage resultStage = new Stage(StageStyle.DECORATED);
        try {

            Scene scene  = new Scene(fxmlLoader_.load(), ViewSettings.RESULT_WIDTH, ViewSettings.RESULT_HEIGHT);
            resultStage.setScene(scene);
            resultController_ = fxmlLoader_.getController();
            HashMap<AlgorithmName, Pane> preparedResults = resultPresenters_.prepareAllResults(results);

            for(Map.Entry<AlgorithmName, Pane> preparedResult: preparedResults.entrySet())
            {
                resultController_.setPane(preparedResult.getValue(), preparedResult.getKey());
            }
            resultStage.setTitle(ViewSettings.RESULT_TITLE);
            resultStage.show();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addResultPresenter(AlgorithmName algorithmName)
    {
        resultPresenters_.add(algorithmName);
    }
}
