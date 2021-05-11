package ru.nsu.fit.sokolova.algorithms.implementations.ChomskyNormalForm;

import javafx.util.Pair;
import ru.nsu.fit.sokolova.dataModels.grammar.Regexp;
import ru.nsu.fit.sokolova.dataModels.symbols.Symbol;

public class Splitter {

    public static Pair<Symbol, Regexp> split(Regexp partToSplit)
    {
        Regexp tail =  partToSplit.getTail(1);
        return new Pair<>(partToSplit.getSymbol(0), tail);
    }
}
