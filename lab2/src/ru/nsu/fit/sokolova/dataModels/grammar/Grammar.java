package ru.nsu.fit.sokolova.dataModels.grammar;

import ru.nsu.fit.sokolova.dataModels.symbols.*;

import java.util.ArrayList;

public class Grammar
{
    private ArrayList<Nonterminal> nonterminals_;
    private ArrayList<Terminal> terminals_;
    private ArrayList<Rule> rules_;
    private StartSymbol startSymbol_;

    public Grammar(ArrayList<Nonterminal> nonterminals, ArrayList<Terminal> terminals, ArrayList<Rule> rules, StartSymbol startSymbol)
    {
        nonterminals_ = nonterminals;
        terminals_ = terminals;
        rules_ = rules;
        startSymbol_ = startSymbol;
    }

    public ArrayList<Rule> getRules()
    {
        return rules_;
    }

    public ArrayList<Terminal> getTerminals()
    {
        return terminals_;
    }

    public StartSymbol getStartSymbol()
    {
        return startSymbol_;
    }

    public ArrayList<Nonterminal> getNonterminals()
    {
        return nonterminals_;
    }

    public boolean containsEmtyRules()
    {
            for(Rule rule: rules_)
            {
                if(rule.getRightPart().isEmpty())
                {
                    return true;
                }
            }
            return false;
    }
}
