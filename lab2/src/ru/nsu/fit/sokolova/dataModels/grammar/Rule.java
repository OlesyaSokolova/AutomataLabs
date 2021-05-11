package ru.nsu.fit.sokolova.dataModels.grammar;

import ru.nsu.fit.sokolova.dataModels.symbols.Nonterminal;
import ru.nsu.fit.sokolova.dataModels.symbols.Symbol;
import ru.nsu.fit.sokolova.dataModels.symbols.SymbolType;

public class Rule
{
    private Nonterminal leftPart_;
    private Regexp rightPart_;
    private boolean isChain_;

    public static final String TRANSITION = "->";


    public Rule(Nonterminal leftPart, Regexp rightPart)
    {
        leftPart_ = leftPart;
        rightPart_ = rightPart;
        checkIfIsChain();
    }

    public Rule(Rule rule)
    {
        this.leftPart_ = rule.getLeftPart();
        this.rightPart_ = rule.getRightPart();
        this.isChain_ = rule.isChain();
    }

    private void checkIfIsChain()
    {
        if(rightPart_.isNonterminal())
        {
            isChain_ = true;
        }
        else
        {
            isChain_ = false;
        }
    }

    @Override
    public String toString()
    {
        return leftPart_+ TRANSITION + rightPart_;
    }

    public Nonterminal getLeftPart()
    {
        return leftPart_;
    }

    public Regexp getRightPart()
    {
        return rightPart_;
    }

    public boolean isChain()
    {
        return isChain_;
    }

    public RuleType getType()
    {
        if(rightPart_.isTerminal())
        {
            return RuleType.SECOND_TYPE;
        }

        else if(rightPart_.getSymbols().size() == 2)
        {
            for (Symbol symbol: rightPart_.getSymbols())
            {
                if(symbol instanceof Nonterminal)
                {
                    return RuleType.FIRST_TYPE;
                }
            }
        }

        else if(leftPart_.getType() == SymbolType.START_SYMBOL && rightPart_.isEmpty())
        {
            return RuleType.THIRD_TYPE;
        }

        return RuleType.UNDEFINED;
    }
}
