package ru.nsu.fit.sokolova.lexemes;

import static ru.nsu.fit.sokolova.lexemes.LexemePriority.DISJUNCTION_PRIORITY;

public class LexDisjunction extends Lexeme
{
    public LexDisjunction(Character value)
    {
        super(LexemeType.OP_DISJUNCTION, value, DISJUNCTION_PRIORITY);
    }
}