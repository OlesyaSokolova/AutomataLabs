package ru.nsu.fit.sokolova;

import ru.nsu.fit.sokolova.algorithms.AlgorithmName;
import ru.nsu.fit.sokolova.algorithms.AlgorithmsContainer;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;
import ru.nsu.fit.sokolova.resultPresenters.ResultShower;

import java.io.IOException;
import java.util.HashMap;

public class GrammarProcessor
{
    private Grammar grammar_;
    private AlgorithmsContainer algorithms_;
    private ResultShower resultShower_;

    public GrammarProcessor(Grammar grammar)
    {
        grammar_ = grammar;
        algorithms_ = new AlgorithmsContainer();
        resultShower_ = new ResultShower();
    }

    public void prepareAlgorithms()
    {
        addAlgorithmWithResultPresenter(AlgorithmName.EMPTY_CHECK);
        addAlgorithmWithResultPresenter(AlgorithmName.CHAIN_RULES_ELIMINATION);
        addAlgorithmWithResultPresenter(AlgorithmName.CHOMSKY_NORMAL_FORM_CONVERTATION);
    }

    private void addAlgorithmWithResultPresenter(AlgorithmName algorithmName)
    {
        algorithms_.add(algorithmName);
        resultShower_.addResultPresenter(algorithmName);
    }

    public void start() throws IOException {
        HashMap<AlgorithmName, Grammar> results = algorithms_.performAll(grammar_);
        resultShower_.show(results);

    }
}
