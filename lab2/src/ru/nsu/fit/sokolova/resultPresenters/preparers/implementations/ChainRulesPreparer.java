package ru.nsu.fit.sokolova.resultPresenters.preparers.implementations;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import ru.nsu.fit.sokolova.algorithms.AlgorithmName;
import ru.nsu.fit.sokolova.resultPresenters.preparers.ResultPreparer;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;
import ru.nsu.fit.sokolova.views.ViewSettings;

import java.io.IOException;

public class ChainRulesPreparer extends ResultPreparer
{
    public ChainRulesPreparer()
    {
        super(AlgorithmName.CHAIN_RULES_ELIMINATION);
    }

    public Pane prepareResult(Grammar processedGrammar) throws IOException
    {
        Label title = new Label(getTitle(algorithmName_));
        title.setTextFill(ViewSettings.FILLER);
        title.setFont(new Font(ViewSettings.MAIN_FONT));
        title.setAlignment(Pos.TOP_CENTER);
        title.setWrapText(true);

        Label startSymbol = new Label("Start symbol = " + processedGrammar.getStartSymbol().toString());
        startSymbol.setFont(new Font(ViewSettings.MAIN_FONT));

        Label nonterminals = new Label(arrayToString("N", processedGrammar.getNonterminals()));
        nonterminals.setFont(new Font(ViewSettings.MAIN_FONT));
        nonterminals.setWrapText(true);

        Label terminals = new Label(arrayToString("∑", processedGrammar.getTerminals()));
        terminals.setFont(new Font(ViewSettings.MAIN_FONT));
        terminals.setWrapText(true);

        Label rules = new Label(arrayToString("R", processedGrammar.getRules()));
        rules.setFont(new Font(ViewSettings.MAIN_FONT));
        rules.setWrapText(true);

        VBox pane = new VBox();
        pane.setBorder(new Border(new BorderStroke(ViewSettings.FILLER, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        pane.getChildren().addAll(title, startSymbol, nonterminals, terminals, rules);

        return pane;
    }
}
