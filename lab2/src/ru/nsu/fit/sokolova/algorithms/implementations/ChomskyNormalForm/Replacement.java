package ru.nsu.fit.sokolova.algorithms.implementations.ChomskyNormalForm;

import ru.nsu.fit.sokolova.dataModels.symbols.Nonterminal;
import ru.nsu.fit.sokolova.dataModels.symbols.Terminal;

public class Replacement
{
    private Terminal before_;
    private Nonterminal after_;

    public Replacement(Terminal before, Nonterminal after)
    {
        before_ = before;
        after_ = after;
    }

    public Nonterminal getAfter()
    {
        return after_;
    }

    public Terminal getBefore()
    {
        return before_;
    }
}
