package ru.nsu.fit.sokolova.operations;

import javafx.util.Pair;
import ru.nsu.fit.sokolova.finiteAutomata.Transition;
import ru.nsu.fit.sokolova.finiteAutomata.TransitionTable;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

import java.util.ArrayList;

public class OpDisjunction extends Operation
{
    @Override
    public void perform(Transition transitionToProcess, TreeNode regexp, TransitionTable transitionTable)
    {
        Pair<TreeNode, TreeNode> splittedRegexp = regexp.split();

        int startState = transitionToProcess.getStartState();
        int endState = transitionToProcess.getEndState();

        Transition transition1 = new Transition(startState, splittedRegexp.getKey(), endState);
        Transition transition2 = new Transition(startState, splittedRegexp.getValue(), endState);

        transitionTable.addTransition(transition1);
        transitionTable.addTransition(transition2);
    }
}
