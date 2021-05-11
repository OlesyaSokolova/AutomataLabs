package ru.nsu.fit.sokolova;

import ru.nsu.fit.sokolova.dataModels.grammar.Regexp;
import ru.nsu.fit.sokolova.dataModels.symbols.Symbol;
import ru.nsu.fit.sokolova.dataModels.symbols.Terminal;

import java.util.ArrayList;
import java.util.Collection;

public class Utils
{
    public static ArrayList<String> createAlphabet(ArrayList<?> a, ArrayList<?> b)
    {
        ArrayList<String> aStrings = new ArrayList<>();
        a.forEach(i -> aStrings.add(i.toString()));

        ArrayList<String> bStrings = new ArrayList<>();
        b.forEach(i -> bStrings.add(i.toString()));

        if ((aStrings == null) || (a.isEmpty() && (b != null))) return bStrings;
        if ((b == null) || b.isEmpty()) return aStrings;
        ArrayList<String> result = new ArrayList();
        result.addAll(aStrings);
        result.addAll(bStrings);
        result.removeAll(aStrings);
        result.addAll(aStrings);

        return result;
    }

    public static ArrayList<String> terminalsToSymbols(ArrayList<Terminal> terminals)
    {
        ArrayList<String> result = new ArrayList<>();
        for(Terminal terminal: terminals)
        {
            result.add(terminal.getValue());
        }
        return result;
    }
    public static ArrayList<String> regexpToSymbols(Regexp regexp)
    {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Symbol> regexpSymbols = regexp.getSymbols();
        for(Symbol symbol: regexpSymbols)
        {
            result.add(symbol.getValue());
        }
        return result;
    }

    public static boolean arraysAreEqual(Collection<?> a, Collection<?>  b)
    {
        if(a.isEmpty() && b.isEmpty())
        {
            return false;
        }
        else if(a.containsAll(b) && b.containsAll(a))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static ArrayList<String> getValues(ArrayList<?> set)
    {
        ArrayList<String> setStrings = new ArrayList<>();
        set.forEach(i -> setStrings.add(i.toString()));
        return setStrings;
    }
}
