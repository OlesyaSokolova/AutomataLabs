package ru.nsu.fit.sokolova.operations;

import javafx.util.Pair;
import ru.nsu.fit.sokolova.finiteAutomata.Transition;
import ru.nsu.fit.sokolova.finiteAutomata.TransitionTable;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

public class OpConcatenation extends Operation
{
    @Override
    public void perform(Transition transitionToProcess, TreeNode regexp, TransitionTable transitionTable)
    {
        Pair<TreeNode, TreeNode> splittedRegexp = regexp.split();

        int startState = transitionToProcess.getStartState();
        int endState = transitionToProcess.getEndState();
        int newState = transitionTable.newStateNumber();

        Transition transition1 = new Transition(startState, splittedRegexp.getKey(), newState);
        Transition transition2 = new Transition(newState, splittedRegexp.getValue(), endState);

        transitionTable.addTransition(transition1);
        transitionTable.addTransition(transition2);
    }
}
