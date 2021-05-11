package ru.nsu.fit.sokolova.dataModels.grammar;

import ru.nsu.fit.sokolova.dataModels.symbols.Nonterminal;
import ru.nsu.fit.sokolova.dataModels.symbols.Symbol;
import ru.nsu.fit.sokolova.dataModels.symbols.SymbolType;
import ru.nsu.fit.sokolova.dataModels.symbols.Terminal;

import java.util.*;
import java.util.stream.Collectors;

public class Regexp
{
    private static final int EMPTY_REGEXP_SIZE = 1;
    private static final String EMPTY_REGEXP_VALUE = "e";
    private static final int ONE_SYMBOL_REGEXP_SIZE = 1;
    private ArrayList<Symbol> symbols_;

    public Regexp(String regexpString, ArrayList<Nonterminal> nonterminals)
    {
        symbols_ = new ArrayList<>();
        ArrayList<String> regexpSymbols = new ArrayList<>(regexpString.chars()
                        .mapToObj(c -> String.valueOf((char)c))
                        .collect(Collectors.toList()
                        )
        );
        ArrayList<String> nonterminalChars = new ArrayList<>();

        nonterminals.stream().forEach(currentNonterminal ->
        {
            String currentSymbol = currentNonterminal.getValue();
            if (regexpSymbols.contains(currentSymbol))
            {
                nonterminalChars.add(currentSymbol);
            }
        });

        regexpSymbols.stream().forEach(currentSymbol ->
        {
            Symbol symbol;
            if(nonterminalChars.contains(currentSymbol))
            {
                symbol = new Nonterminal(currentSymbol);
            }
            else
            {
                symbol = new Terminal(currentSymbol);
            }
            symbols_.add(symbol);
        });
    }

    public Regexp(Symbol value)
    {
        symbols_ = new ArrayList<>();
        symbols_.add(value);
    }

    public Regexp()
    {
        symbols_ = new ArrayList<>();
    }

    public ArrayList<Symbol> getSymbols()
    {
        return symbols_;
    }

    public boolean isEmpty()
    {
        if(symbols_.size() == EMPTY_REGEXP_SIZE && symbols_.get(0).getValue() == EMPTY_REGEXP_VALUE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isNonterminal()
    {
        if(symbols_.size() == ONE_SYMBOL_REGEXP_SIZE && symbols_.get(0).getType() == SymbolType.NONTERMINAL)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isTerminal()
    {
        if(symbols_.size() == ONE_SYMBOL_REGEXP_SIZE && symbols_.get(0).getType() == SymbolType.TERMINAL)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        String result = "";
        for (Symbol symbol: symbols_)
        {
            result += symbol;
        }
        return result;
    }

    public int getLength()
    {
        return symbols_.size();
    }

    public Symbol getSymbol(int index)
    {
        return symbols_.get(index);
    }

    public Regexp getTail(int startIndex)
    {
        Regexp tail = new Regexp();
        for(int i = startIndex; i <symbols_.size(); i++)
        {
            tail.addSymbol(symbols_.get(i));
        }
        return tail;
    }

    public void addSymbol(Symbol symbol)
    {
        symbols_.add(symbol);
    }
}
