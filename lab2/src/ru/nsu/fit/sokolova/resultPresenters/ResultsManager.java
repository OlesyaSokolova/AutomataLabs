package ru.nsu.fit.sokolova.resultPresenters;

import javafx.scene.layout.Pane;
import ru.nsu.fit.sokolova.algorithms.AlgorithmName;
import ru.nsu.fit.sokolova.resultPresenters.preparers.ResultPreparer;
import ru.nsu.fit.sokolova.resultPresenters.preparers.implementations.ChainRulesPreparer;
import ru.nsu.fit.sokolova.resultPresenters.preparers.implementations.ChomskyNormalFormPreparer;
import ru.nsu.fit.sokolova.resultPresenters.preparers.implementations.EmptyCheckPreparer;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultsManager
{
    ArrayList<AlgorithmName> preparersList_;

    public ResultsManager()
    {
        preparersList_ = new ArrayList<>();
    }

    private static final Map<AlgorithmName, ResultPreparer> preparers = Map.of
            (
                    AlgorithmName.EMPTY_CHECK, new EmptyCheckPreparer(),
                    AlgorithmName.CHAIN_RULES_ELIMINATION, new ChainRulesPreparer(),
                    AlgorithmName.CHOMSKY_NORMAL_FORM_CONVERTATION, new ChomskyNormalFormPreparer()
            );
    public void add(AlgorithmName algName)
    {
        preparersList_.add(algName);
    }

    public HashMap<AlgorithmName, Pane> prepareAllResults(HashMap<AlgorithmName, Grammar> results) throws IOException
    {
        HashMap<AlgorithmName, Pane> preparedResults = new HashMap<>();

        for(Map.Entry<AlgorithmName, Grammar> result: results.entrySet())
        {
            AlgorithmName algorithmName = result.getKey();
            Grammar processedGrammar = result.getValue();
            ResultPreparer preparer = preparers.get(algorithmName);
            Pane resultScene = preparer.prepareResult(processedGrammar);
            preparedResults.put(algorithmName, resultScene);
        }

        return preparedResults;
    }
}
