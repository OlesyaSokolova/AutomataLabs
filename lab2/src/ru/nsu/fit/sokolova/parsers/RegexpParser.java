package ru.nsu.fit.sokolova.parsers;

public class RegexpParser implements IParser
{
    public static final String DELIMITER = "\\|";

    @Override
    public Object parseInputString(String input)
    {
        String[] result = input.split(DELIMITER);
        return result;
    }
}
