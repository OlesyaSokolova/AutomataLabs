package ru.nsu.fit.sokolova.operations;

import javafx.util.Pair;
import ru.nsu.fit.sokolova.finiteAutomata.Transition;
import ru.nsu.fit.sokolova.finiteAutomata.TransitionTable;
import ru.nsu.fit.sokolova.lexemes.LexSymbol;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

import java.util.ArrayList;

public class OpIteration extends Operation
{
    private static final Character EMPTY_REGEXP = 'e';

    @Override
    public void perform(Transition transitionToProcess, TreeNode regexp, TransitionTable transitionTable)
    {
        Pair<TreeNode, TreeNode> splittedRegexp = regexp.split();
        //rightNode == null!
        int startState = transitionToProcess.getStartState();
        int endState = transitionToProcess.getEndState();
        int newState = transitionTable.newStateNumber();

        LexSymbol emptySymbol = new LexSymbol(EMPTY_REGEXP);
        TreeNode emptyRegexp = new TreeNode(emptySymbol);
        Transition transition1 = new Transition(startState, emptyRegexp, newState);
        Transition transition2 = new Transition(newState, splittedRegexp.getKey(), newState);
        Transition transition3 = new Transition(newState, emptyRegexp, endState);

        transitionTable.addTransition(transition1);
        transitionTable.addTransition(transition2);
        transitionTable.addTransition(transition3);
    }
}
