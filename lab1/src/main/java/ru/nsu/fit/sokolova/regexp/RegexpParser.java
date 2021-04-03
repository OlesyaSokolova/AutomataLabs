package ru.nsu.fit.sokolova.regexp;

import ru.nsu.fit.sokolova.lexemes.*;

import java.util.ArrayList;

public class RegexpParser
{
    private static final char LEFT_BRACKET_CHAR = '(';
    private static final char RIGHT_BRACKET_CHAR = ')';
    private static final char OP_DISJUNCTION_CHAR = '+';
    private static final char OP_ITERATION_CHAR = '*';
    private static final char OP_CONCATENATION_CHAR = ' ';

    public static ArrayList<Lexeme> parseRegexp(String regexp)
    {
        ArrayList<Lexeme> lexemes = new ArrayList<>();

        int position = 0;
        int regexpLength = regexp.length();
        int lastCharPos = regexpLength - 1;
        while(position < regexpLength)
        {
            char character = regexp.charAt(position);
            switch(character)
            {
                case LEFT_BRACKET_CHAR:
                {
                    Lexeme newLexeme =  new LexLeftBracket(character);
                    lexemes.add(newLexeme);
                    position++;
                    continue;
                }

                case RIGHT_BRACKET_CHAR:
                {
                    Lexeme newLexeme =  new LexRightBracket(character);
                    if(position == lastCharPos)
                    {
                        newLexeme.setLast(true);
                        lexemes.add(newLexeme);
                    }
                    else
                    {
                        int nextCharPos = position + 1;
                        char nextChar = regexp.charAt(nextCharPos);
                        if(Character.isLetterOrDigit(nextChar))
                        {
                            Lexeme operation = new LexConcatenation(OP_CONCATENATION_CHAR);
                            lexemes.add(newLexeme);
                            lexemes.add(operation);
                        }
                        else
                        {
                            lexemes.add(newLexeme);
                        }
                    }
                    position++;
                    continue;
                }

                case OP_DISJUNCTION_CHAR:
                {
                    Lexeme newLexeme =  new LexDisjunction(character);
                    lexemes.add(newLexeme);
                    position++;
                    continue;
                }

                case OP_ITERATION_CHAR:
                {
                    Lexeme newLexeme =  new LexIteration(character);
                    if(position == lastCharPos)
                    {
                        newLexeme.setLast(true);
                        lexemes.add(newLexeme);
                    }
                    else
                    {
                        int nextCharPos = position + 1;
                        char nextChar = regexp.charAt(nextCharPos);
                        if(Character.isLetterOrDigit(nextChar))
                        {
                            Lexeme operation = new LexConcatenation(OP_CONCATENATION_CHAR);
                            lexemes.add(newLexeme);
                            lexemes.add(operation);
                        }
                        else
                        {
                            lexemes.add(newLexeme);
                        }
                    }
                    position++;
                    continue;
                }

                default:
                {
                    if(Character.isLetterOrDigit(character))
                    {
                        Lexeme newLexeme = new LexSymbol(character);
                        if(position == lastCharPos)
                        {
                            newLexeme.setLast(true);
                            lexemes.add(newLexeme);
                        }
                        else
                        {
                            int nextCharPos = position + 1;
                            char nextChar = regexp.charAt(nextCharPos);
                            if(Character.isLetterOrDigit(nextChar))
                            {
                                Lexeme operation = new LexConcatenation(OP_CONCATENATION_CHAR);
                                lexemes.add(newLexeme);
                                lexemes.add(operation);
                            }
                            else
                            {
                                lexemes.add(newLexeme);
                            }
                        }
                    }
                    position++;
                    continue;
                }
            }
        }
        return lexemes;
    }
}
