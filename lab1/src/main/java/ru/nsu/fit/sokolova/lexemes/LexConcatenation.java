package ru.nsu.fit.sokolova.lexemes;

import static ru.nsu.fit.sokolova.lexemes.LexemePriority.CONCATENATION_PRIORITY;

public class LexConcatenation extends Lexeme
{
    public LexConcatenation(Character value)
    {
        super(LexemeType.OP_CONCATENATION, value, CONCATENATION_PRIORITY);
    }
}