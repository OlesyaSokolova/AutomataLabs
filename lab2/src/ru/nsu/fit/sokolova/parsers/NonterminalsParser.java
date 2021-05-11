package ru.nsu.fit.sokolova.parsers;

import ru.nsu.fit.sokolova.dataModels.symbols.Nonterminal;
import java.util.ArrayList;

public class NonterminalsParser implements IParser
{
    @Override
    public Object parseInputString(String input)
    {
        String preparedInput = input.replaceAll("\\s+","").replaceAll(",","");
        char[] chars = preparedInput.toCharArray();

        ArrayList<Nonterminal> result = new ArrayList<>();

        for(int i = 0; i < chars.length; i++)
        {
            Nonterminal nonterminal = new Nonterminal(String.valueOf(chars[i]));
            result.add(nonterminal);
        }
        return result;
    }
}
