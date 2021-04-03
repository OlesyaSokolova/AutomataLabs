package ru.nsu.fit.sokolova.finiteAutomata;

import javafx.util.Pair;
import ru.nsu.fit.sokolova.operations.Operation;
import ru.nsu.fit.sokolova.operations.OperationsFactory;
import ru.nsu.fit.sokolova.regexp.tree.RegexpTree;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

import java.util.ArrayList;

public class TransitionTable
{
    private final int START_STATE_NUMBER = 0;
    private int lastStateNumber_ = 0;
    private ArrayList<Transition> transitions_;
    private OperationsFactory operationsFactory_;

    public int newStateNumber()
    {
        lastStateNumber_++;
        return lastStateNumber_;
    }

    public void init(TreeNode rootRegexp)
    {
        int startStateNumber = START_STATE_NUMBER;
        int endStateNumber = startStateNumber + 1;
        lastStateNumber_ = endStateNumber;
        Transition initialTransition = new Transition(startStateNumber, rootRegexp, endStateNumber);
        transitions_ = new ArrayList<>();
        transitions_.add(initialTransition);
        operationsFactory_ = new OperationsFactory();
    }

    public void fill() throws CloneNotSupportedException
    {
        Transition transitionToProcess = findUnsplittedTransition();
        while(transitionToProcess != null)
        {
            TreeNode regexp = transitionToProcess.getRegexp();
            Operation regexpOperation = operationsFactory_.createOperation(regexp);
            regexpOperation.perform(transitionToProcess, regexp, this);
            transitions_.remove(transitionToProcess);
            transitionToProcess = findUnsplittedTransition();
        }
    }

    private Transition findUnsplittedTransition()
    {
        for(Transition transition: transitions_)
        {
            if(transition.getRegexp().canBeSplitted())
            {
                return transition;
            }
        }
        return null;
    }

    public void print()
    {
        for(Transition transition: transitions_)
        {
            System.out.println(transition.toString());
        }
    }

    public ArrayList<Transition> getTransitions()
    {
        return transitions_;
    }

    public int getLastStateNumber()
    {
        return lastStateNumber_;
    }

    public void addTransition(Transition transition)
    {
        transitions_.add(transition);
    }
}
