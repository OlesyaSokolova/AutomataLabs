package ru.nsu.fit.sokolova.parsers;

import ru.nsu.fit.sokolova.dataModels.grammar.Regexp;
import ru.nsu.fit.sokolova.dataModels.grammar.Rule;
import ru.nsu.fit.sokolova.dataModels.symbols.Nonterminal;
import ru.nsu.fit.sokolova.dataModels.symbols.Terminal;

import java.util.ArrayList;

import static ru.nsu.fit.sokolova.dataModels.grammar.Rule.TRANSITION;

public class RulesParser implements IParser
{
    private static final int LEFT_PART_INDEX = 0;
    private static final int RIGHT_PART_INDEX = 1;

    ArrayList<Nonterminal> nonterminals_;
    ArrayList<Terminal> terminals_;
    RegexpParser regexpParser_;

    public RulesParser()
    {
        regexpParser_ = new RegexpParser();
    }

    public void setParsedSets(ArrayList<Nonterminal> nonterminals, ArrayList<Terminal> terminals)
    {
        nonterminals_ = nonterminals;
        terminals_ = terminals;
    }

    @Override
    public Object parseInputString(String input)
    {
        String preparedInput = input.replaceAll("\\s+","");
        ArrayList<Rule> result = new ArrayList<>();
        String[] rules = preparedInput.split(",(?!\\s)");

        for(String ruleString: rules)
        {
            String[] parts = ruleString.split(TRANSITION);
            Nonterminal leftPart = new Nonterminal(String.valueOf(parts[LEFT_PART_INDEX].charAt(0)));
            String rightPartString = parts[RIGHT_PART_INDEX];
            String[] regexps = (String[])regexpParser_.parseInputString(rightPartString);
            for(String regexpString: regexps)
            {
                Regexp rightPart = new Regexp(regexpString, nonterminals_);
                Rule rule = new Rule(leftPart, rightPart);
                result.add(rule);
            }
        }
        return result;
    }
}
