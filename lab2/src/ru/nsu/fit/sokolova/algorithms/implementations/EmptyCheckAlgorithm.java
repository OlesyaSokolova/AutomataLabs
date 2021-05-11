package ru.nsu.fit.sokolova.algorithms.implementations;

import ru.nsu.fit.sokolova.Utils;
import ru.nsu.fit.sokolova.algorithms.IAlgorithm;
import ru.nsu.fit.sokolova.dataModels.Language;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;
import ru.nsu.fit.sokolova.dataModels.grammar.Regexp;
import ru.nsu.fit.sokolova.dataModels.grammar.Rule;
import ru.nsu.fit.sokolova.dataModels.symbols.Nonterminal;

import java.util.ArrayList;

public class EmptyCheckAlgorithm implements IAlgorithm
{
    @Override
    public Grammar perform(Grammar grammar)
    {
        ArrayList<String> previousSet = new ArrayList<>();
        ArrayList<String> currentSet = new ArrayList<>();

        while(Utils.arraysAreEqual(previousSet, currentSet) != true)
        {
            previousSet.removeAll(currentSet);
            previousSet.addAll(currentSet);

            for(Rule rule: grammar.getRules())
            {
                Nonterminal leftPart = rule.getLeftPart();
                Regexp rightPart = rule.getRightPart();
                ArrayList<String> alphabet = Utils.createAlphabet(previousSet, Utils.terminalsToSymbols(grammar.getTerminals()));
                Language language = new Language(alphabet);
                if(language.containsRegexp(rightPart))
                {
                    if(!currentSet.contains(leftPart.getValue()))
                    {
                        currentSet.add(leftPart.getValue());
                    }
                }
            }
            if(Utils.arraysAreEqual(currentSet, Utils.getValues(grammar.getNonterminals())))
            {
                break;
            }
        }

        if(currentSet.contains(grammar.getStartSymbol().getValue()))
        {
            return grammar;
        }
        else
        {
              return null;
        }
    }
}
