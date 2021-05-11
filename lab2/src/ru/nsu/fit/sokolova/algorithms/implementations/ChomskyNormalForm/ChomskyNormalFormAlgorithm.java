package ru.nsu.fit.sokolova.algorithms.implementations.ChomskyNormalForm;

import javafx.util.Pair;
import ru.nsu.fit.sokolova.algorithms.IAlgorithm;
import ru.nsu.fit.sokolova.dataModels.grammar.Grammar;
import ru.nsu.fit.sokolova.dataModels.grammar.Regexp;
import ru.nsu.fit.sokolova.dataModels.grammar.Rule;
import ru.nsu.fit.sokolova.dataModels.grammar.RuleType;
import ru.nsu.fit.sokolova.dataModels.symbols.Nonterminal;
import ru.nsu.fit.sokolova.dataModels.symbols.Symbol;
import ru.nsu.fit.sokolova.dataModels.symbols.SymbolType;
import ru.nsu.fit.sokolova.dataModels.symbols.Terminal;

import java.util.ArrayList;

public class ChomskyNormalFormAlgorithm implements IAlgorithm
{
    private static final int MIN_REQUIRED_LENGTH = 2;
    private static final int ONE_SYMBOL = 1;
    private static final int TAIL_INDEX = 1;
    private ArrayList<Replacement> replacements_;

    private ArrayList<Nonterminal> newNonterminals_;
    private ArrayList<Rule> newRules_;
    private Grammar grammar_;

    @Override
    public Grammar perform(Grammar grammar)
    {
        grammar_ = grammar;
        replacements_ = new ArrayList<>();

        newNonterminals_ = new ArrayList<>();
        newNonterminals_.addAll(grammar_.getNonterminals());
        newRules_ = new ArrayList<>();

        addOldRules();
        createAndAddNewRules();
        createRulesFromReplacements();


        Grammar result = new Grammar(newNonterminals_, grammar.getTerminals(), newRules_, grammar.getStartSymbol());
        return result;
    }

    private void createRulesFromReplacements()
    {
        for(Replacement replacement: replacements_)
        {
            Rule newRule = new Rule(replacement.getAfter(), new Regexp(replacement.getBefore()));
            newRules_.add(newRule);
        }
    }

    private void createAndAddNewRules()
    {
        for(Rule rule: grammar_.getRules())
        {
            int rightPartLength = rule.getRightPart().getLength();
            if(rightPartLength > MIN_REQUIRED_LENGTH)
            {
                Rule currentRule = new Rule(rule);
                while(currentRule.getRightPart().getLength() > MIN_REQUIRED_LENGTH)
                {
                    Nonterminal leftPart = currentRule.getLeftPart();
                    Regexp rightPart = currentRule.getRightPart();
                    Pair<Symbol, Regexp> splittedRightPart = Splitter.split(rightPart);
                    Regexp newRightPart = new Regexp();
                    newRightPart.addSymbol(replaceSymbol(splittedRightPart.getKey()));
                    newRightPart.addSymbol(replaceSymbol(new Nonterminal(splittedRightPart.getValue().toString())));

                    Rule newRule = new Rule(leftPart, newRightPart);
                    newRules_.add(newRule);
                    currentRule = new Rule((Nonterminal) newRightPart.getSymbol(TAIL_INDEX), splittedRightPart.getValue());
                }

                Pair<Symbol, Regexp> splittedRightPart = Splitter.split(currentRule.getRightPart());
                Symbol symbol1 = splittedRightPart.getKey();
                Symbol symbol2 = splittedRightPart.getValue().getSymbol(0);
                Regexp newRegexp = new Regexp();
                newRegexp.addSymbol(replaceSymbol(symbol1));
                newRegexp.addSymbol(replaceSymbol(symbol2));
                Rule newRule = new Rule(currentRule.getLeftPart(), newRegexp);
                newRules_.add(newRule);
            }
        }
    }

    private Nonterminal replaceSymbol(Symbol symbol)
    {
        Nonterminal newNonterminal = null;
        if(symbol.getType() == SymbolType.NONTERMINAL || symbol.getType() == SymbolType.START_SYMBOL)
        {
            if(symbol.getValue().length() > ONE_SYMBOL)
            {
                newNonterminal = new Nonterminal("N" + NewNonterminalsCounter.getNextNumber());
            }
            else
            {
                newNonterminal = (Nonterminal) symbol;
            }
        }
        else
        {
            newNonterminal = new Nonterminal("N" + NewNonterminalsCounter.getNextNumber());
            replacements_.add(new Replacement((Terminal) symbol, newNonterminal));
        }
        newNonterminals_.remove(symbol);
        newNonterminals_.add(newNonterminal);
        return newNonterminal;
    }

    private void addOldRules()
    {
        for(Rule rule: grammar_.getRules())
        {
            if(rule.getType() != RuleType.UNDEFINED)
            {
                newRules_.add(rule);
            }

           if(rule.getRightPart().getLength() == MIN_REQUIRED_LENGTH)
            {
                Pair<Symbol, Regexp> splittedRightPart = Splitter.split(rule.getRightPart());
                Symbol symbol1 = splittedRightPart.getKey();
                Symbol symbol2 = splittedRightPart.getValue().getSymbol(0);
                if (symbol1.getType() == SymbolType.TERMINAL
                        || symbol2.getType() == SymbolType.TERMINAL) {
                    Regexp newRegexp = new Regexp();
                    newRegexp.addSymbol(replaceSymbol(symbol1));
                    newRegexp.addSymbol(replaceSymbol(symbol2));
                    Rule newRule = new Rule(rule.getLeftPart(), newRegexp);
                    newRules_.add(newRule);
                }
            }
        }
    }
}
