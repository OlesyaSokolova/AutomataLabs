package ru.nsu.fit.sokolova.operations;

import javafx.util.Pair;
import ru.nsu.fit.sokolova.finiteAutomata.Transition;
import ru.nsu.fit.sokolova.finiteAutomata.TransitionTable;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

import java.util.ArrayList;

abstract public class Operation implements Cloneable
{
    protected OperationType type_;

    public Operation clone() throws CloneNotSupportedException
    {
        return (Operation)super.clone();
    }

    abstract public void perform(Transition transitionToProcess, TreeNode regexp, TransitionTable transitionTable);
}
