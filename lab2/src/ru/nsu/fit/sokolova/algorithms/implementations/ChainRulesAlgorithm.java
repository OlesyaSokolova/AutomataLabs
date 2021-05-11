package ru.nsu.fit.sokolova.algorithms.implementations;

import ru.nsu.fit.sokolova.algorithms.IAlgorithm;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;
import ru.nsu.fit.sokolova.dataModels.grammar.Regexp;
import ru.nsu.fit.sokolova.dataModels.grammar.Rule;
import ru.nsu.fit.sokolova.dataModels.symbols.Nonterminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChainRulesAlgorithm implements IAlgorithm
{
    HashMap<Nonterminal, ArrayList<Nonterminal>> nonterminalsSets_;
    Grammar grammar_;
    ArrayList<Rule> newRules_;

    @Override
    public Grammar perform(Grammar grammar)
    {
        if(grammar.containsEmtyRules())
        {
            return null;
        }
        grammar_ = grammar;
        prepareNonterminalsSets();
        createNewRules();
        Grammar result = new Grammar(grammar.getNonterminals(), grammar.getTerminals(), newRules_, grammar.getStartSymbol());
        return result;
    }

    private void prepareNonterminalsSets()
    {
        nonterminalsSets_ = new HashMap<>();

        for(Nonterminal nonterminal: grammar_.getNonterminals())
        {
            ArrayList<Nonterminal> currentSet = new ArrayList<>();

            currentSet.add(nonterminal);

            for(Rule rule: grammar_.getRules())
            {
                if(currentSet.contains(rule.getLeftPart())
                        && rule.getRightPart().isNonterminal())
                {
                    currentSet.add((Nonterminal)rule.getRightPart().getSymbols().get(0));
                }
            }
            nonterminalsSets_.put(nonterminal, currentSet);
        }
    }

    private void createNewRules()
    {
        newRules_ = new ArrayList<>();

        for(Rule rule: grammar_.getRules())
        {
            Nonterminal B = rule.getLeftPart();
            Regexp regexp = rule.getRightPart();
            if(!rule.isChain())
            {
                ArrayList<Nonterminal> setsOfNonterminalsThatContainB = findSetsThatContainNonterminal(B, nonterminalsSets_);

                for(Nonterminal A: setsOfNonterminalsThatContainB)
                {
                    Rule newRule = new Rule(A, regexp);
                    newRules_.add(newRule);
                }
           }
        }
    }

    private ArrayList<Nonterminal> findSetsThatContainNonterminal(Nonterminal B, HashMap<Nonterminal, ArrayList<Nonterminal>> nonterminalsSets)
    {
        ArrayList<Nonterminal> result = new ArrayList<>();
        for(Map.Entry<Nonterminal, ArrayList<Nonterminal>> pair: nonterminalsSets.entrySet())
        {
            Nonterminal A = pair.getKey();
            ArrayList<Nonterminal> nonterminalsOfA = pair.getValue();
            if(nonterminalsOfA.contains(B))
            {
                result.add(A);
            }
        }
        return result;
    }
}
