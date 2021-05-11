package ru.nsu.fit.sokolova.algorithms;

import ru.nsu.fit.sokolova.algorithms.implementations.ChainRulesAlgorithm;
import ru.nsu.fit.sokolova.algorithms.implementations.ChomskyNormalForm.ChomskyNormalFormAlgorithm;
import ru.nsu.fit.sokolova.algorithms.implementations.EmptyCheckAlgorithm;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlgorithmsContainer
{
    ArrayList<AlgorithmName> algorithmsList_;

    public AlgorithmsContainer()
    {
        algorithmsList_ = new ArrayList<>();
    }

    private static final Map<AlgorithmName, IAlgorithm> algorithmsMap_ = Map.of
            (
                    AlgorithmName.EMPTY_CHECK, new EmptyCheckAlgorithm(),
                    AlgorithmName.CHAIN_RULES_ELIMINATION, new ChainRulesAlgorithm(),
                    AlgorithmName.CHOMSKY_NORMAL_FORM_CONVERTATION, new ChomskyNormalFormAlgorithm()
            );

    public void add(AlgorithmName algName)
    {
        algorithmsList_.add(algName);
    }

    public HashMap<AlgorithmName, Grammar> performAll(Grammar grammar)
    {
        HashMap<AlgorithmName, Grammar> result = new HashMap<>();
        Grammar currentGrammar = grammar;
        Grammar processedGrammar = null;
        for(AlgorithmName algorithmName: algorithmsList_)
        {
            IAlgorithm algorithm = algorithmsMap_.get(algorithmName);
            if(currentGrammar == null)
            {
                break;
            }
            processedGrammar = algorithm.perform(currentGrammar);
            result.put(algorithmName, processedGrammar);
            currentGrammar = processedGrammar;
        }
        return result;
    }
}
