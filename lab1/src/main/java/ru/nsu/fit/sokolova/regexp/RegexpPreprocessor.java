package ru.nsu.fit.sokolova.regexp;

public class RegexpPreprocessor
{
    private static final String USELESS_SYMBOLS = "\\s+";

    public static String preprocessUserInput(String regexp)
    {
        String preprocessedRegexp = regexp.replaceAll(USELESS_SYMBOLS,"");
        return preprocessedRegexp;
    }
}
