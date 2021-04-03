package ru.nsu.fit.sokolova.finiteAutomata;

import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

public class Transition
{
    private int startStateNumber_;
    private int endStateNumber_;
    private TreeNode regexp_;

    @Override
    public String toString() {
        return "Transition{" +
                "" + startStateNumber_ +
                "," + regexp_ +
                "," + endStateNumber_ +
                '}';
    }

    public Transition(int newStartStateNumber, TreeNode newRegexp, int newEndStateNumber)
    {
        startStateNumber_ = newStartStateNumber;
        endStateNumber_ = newEndStateNumber;
        regexp_ = newRegexp;
    }

    public int getStartState()
    {
        return startStateNumber_;
    }

    public int getEndState()
    {
        return endStateNumber_;
    }

    public TreeNode getRegexp()
    {
        return regexp_;
    }

}
