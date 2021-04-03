package ru.nsu.fit.sokolova.finiteAutomata;

import ru.nsu.fit.sokolova.lexemes.Lexeme;
import ru.nsu.fit.sokolova.regexp.RegexpParser;
import ru.nsu.fit.sokolova.regexp.tree.RegexpTree;

import java.util.ArrayList;

public class AutomataBuilder
{
    public TransitionTable build(String preprocessedRegexp) throws CloneNotSupportedException
    {
        ArrayList<Lexeme> analyzedRegexp = RegexpParser.parseRegexp(preprocessedRegexp);
        TransitionTable transitionTable = new TransitionTable();
        RegexpTree regexpTree = new RegexpTree();
        regexpTree.createTreeFromRegexp(analyzedRegexp);
        transitionTable.init(regexpTree.getRoot());
        transitionTable.fill();
        //transitionTable.print();
        return transitionTable;
    }
}
