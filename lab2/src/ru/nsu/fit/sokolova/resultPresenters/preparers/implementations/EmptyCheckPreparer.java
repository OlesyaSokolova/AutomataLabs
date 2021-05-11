package ru.nsu.fit.sokolova.resultPresenters.preparers.implementations;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import ru.nsu.fit.sokolova.algorithms.AlgorithmName;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;
import ru.nsu.fit.sokolova.resultPresenters.preparers.ResultPreparer;
import ru.nsu.fit.sokolova.views.ViewSettings;


public class EmptyCheckPreparer extends ResultPreparer
{
    private static final String EMPTY_LANGUAGE = "Language of the grammar is empty.";
    private static final String NOT_EMPTY_LANGUAGE = "Language of the grammar is not empty.";

    public EmptyCheckPreparer()
    {
        super(AlgorithmName.EMPTY_CHECK);
    }
    public Pane prepareResult(Grammar processedGrammar)
    {
        Label title = new Label(getTitle(algorithmName_));
        title.setTextFill(ViewSettings.FILLER);
        title.setFont(new Font(ViewSettings.MAIN_FONT));
        title.setAlignment(Pos.TOP_CENTER);
        title.setWrapText(true);

        String resultToShowString = processedGrammar == null ? EMPTY_LANGUAGE : NOT_EMPTY_LANGUAGE;
        Label resultToShow = new Label(resultToShowString);
        resultToShow.setFont(new Font(ViewSettings.MAIN_FONT));
        resultToShow.setWrapText(true);

        VBox pane = new VBox();
        pane.setBorder(new Border(new BorderStroke(ViewSettings.FILLER, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        pane.getChildren().addAll(title, resultToShow);

        return pane;
    }
}
