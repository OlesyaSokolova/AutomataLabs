package ru.nsu.fit.sokolova.dataModels;

import ru.nsu.fit.sokolova.Utils;
import ru.nsu.fit.sokolova.dataModels.grammar.Regexp;

import java.util.ArrayList;

public class Language
{
    ArrayList<String> alphabet_;

    public Language(ArrayList<String> alphabet)
    {
        alphabet_ = alphabet;
    }

    public boolean containsRegexp(Regexp regexp)
    {
        if(regexp.isEmpty())
        {
            return true;
        }
        else
        {
            ArrayList<String> regexpSymbols = Utils.regexpToSymbols(regexp);
            for (String symbolValue: regexpSymbols)
            {
                if(alphabet_.contains(symbolValue))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
            return true;
        }
    }
}
