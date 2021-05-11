package ru.nsu.fit.sokolova.parsers;

import ru.nsu.fit.sokolova.dataModels.symbols.StartSymbol;

public class StartSymbolParser implements IParser
{

    private static final int START_SYMBOL_CHAR_POS = 0;

    @Override
    public Object parseInputString(String input)
    {
        return new StartSymbol(String.valueOf(input.charAt(START_SYMBOL_CHAR_POS)));
    }
}
