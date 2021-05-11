package ru.nsu.fit.sokolova.parsers;

import ru.nsu.fit.sokolova.dataModels.symbols.Terminal;

import java.util.ArrayList;

public class TerminalsParser implements IParser
{
    @Override
    public Object parseInputString(String input)
    {
        String preparedInput = input.replaceAll("\\s+","").replaceAll(",","");
        char[] chars = preparedInput.toCharArray();

        ArrayList<Terminal> result = new ArrayList<>();

        for(int i = 0; i < chars.length; i++)
        {
            Terminal terminal = new Terminal(String.valueOf(chars[i]));
            result.add(terminal);
        }
        return result;
    }
}
