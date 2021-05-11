package ru.nsu.fit.sokolova.dataModels.symbols;

import java.util.Objects;

public class Symbol
{
    protected String value_;
    protected SymbolType type_;

    public Symbol(String value, SymbolType type)
    {
        value_ = value;
        type_ = type;
    }

    public String getValue()
    {
        return value_;
    }
    public SymbolType getType()
    {
        return type_;
    }

    @Override
    public String toString()
    {
        return value_;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        Symbol symbol = (Symbol) o;
        return symbol.value_.equals(this.value_) && symbol.type_ == this.type_;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value_, type_);
    }
}
