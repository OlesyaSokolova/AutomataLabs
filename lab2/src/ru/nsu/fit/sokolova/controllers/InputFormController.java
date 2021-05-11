package ru.nsu.fit.sokolova.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.nsu.fit.sokolova.GrammarProcessor;
import ru.nsu.fit.sokolova.dataModels.grammar.ComponentType;
import ru.nsu.fit.sokolova.dataModels.grammar.Rule;
import ru.nsu.fit.sokolova.dataModels.symbols.*;
import ru.nsu.fit.sokolova.parsers.IParser;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;
import ru.nsu.fit.sokolova.parsers.*;
import ru.nsu.fit.sokolova.parsers.RulesParser;
import ru.nsu.fit.sokolova.parsers.TerminalsParser;

public class InputFormController
{
    private static final Map<ComponentType, IParser> parsersMap_ = Map.of
            (
                    ComponentType.SET_OF_NONTERMINALS, new NonterminalsParser(),
                    ComponentType.SET_OF_TERMINALS, new TerminalsParser(),
                    ComponentType.SET_OF_RULES, new RulesParser(),
                    ComponentType.START_SYMBOL, new StartSymbolParser()
            );

    @FXML
    private TextField nonterminalsSetInput;

    @FXML
    private TextField terminalsSetInput;

    @FXML
    private TextField startSymbolInput;

    @FXML
    private TextArea rulesSetInput;

    @FXML
    private Button startButton;

    @FXML
    void initialize()
    {
        startButton.setOnAction(event ->
        {
            Grammar grammar = createGrammarFromInput();
            GrammarProcessor grammarProcessor = new GrammarProcessor(grammar);
            grammarProcessor.prepareAlgorithms();
            try {
                grammarProcessor.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Grammar createGrammarFromInput()
    {
        String nonterminalsString = nonterminalsSetInput.getText();
        String terminalsString = terminalsSetInput.getText();
        String rulesString = rulesSetInput.getText();
        String startSymbolString = startSymbolInput.getText();

        ArrayList<Nonterminal> nonterminals = (ArrayList<Nonterminal>)parsersMap_
                .get(ComponentType.SET_OF_NONTERMINALS)
                .parseInputString(nonterminalsString);

        ArrayList<Terminal> terminals = (ArrayList<Terminal>)parsersMap_
                .get(ComponentType.SET_OF_TERMINALS)
                .parseInputString(terminalsString);

        RulesParser rulesParser = (RulesParser)parsersMap_.get(ComponentType.SET_OF_RULES);
        rulesParser.setParsedSets(nonterminals, terminals);
        ArrayList<Rule> rules = (ArrayList<Rule>)rulesParser.parseInputString(rulesString);

        StartSymbol startSymbol = (StartSymbol)parsersMap_
                .get(ComponentType.START_SYMBOL)
                .parseInputString(startSymbolString);

        Grammar grammar = new Grammar(nonterminals, terminals, rules, startSymbol);
        Stage stage = (Stage)startButton.getScene().getWindow();
        stage.close();
        return grammar;
    }
}
