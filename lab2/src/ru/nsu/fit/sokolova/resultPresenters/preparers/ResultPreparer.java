package ru.nsu.fit.sokolova.resultPresenters.preparers;

import javafx.scene.layout.Pane;

import ru.nsu.fit.sokolova.algorithms.AlgorithmName;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public abstract class ResultPreparer
{
    protected AlgorithmName algorithmName_;
    private static final Map<AlgorithmName, String> algorithmNames = Map.of
            (
                    AlgorithmName.EMPTY_CHECK, "Empty check",
                    AlgorithmName.CHAIN_RULES_ELIMINATION, "Chain rules elimination",
                    AlgorithmName.CHOMSKY_NORMAL_FORM_CONVERTATION, "Convertation to Chomsky normal form"
            );

    public static String getTitle(AlgorithmName title)
    {
        return algorithmNames.get(title);
    }

    public ResultPreparer(AlgorithmName algorithmName)
    {
        algorithmName_ = algorithmName;
    }

    public abstract Pane prepareResult(Grammar processedGrammar) throws IOException;

    public <T> String arrayToString(String arrayName, ArrayList<?> array)
    {
        String result = arrayName+ " = {";
        for (int i = 0; i < (array.size() - 1); i++)
        {
            result += (array.get(i) + ", ");
        }
        result += array.get(array.size() - 1);;
        result += "}";
        return result;
    }

}
