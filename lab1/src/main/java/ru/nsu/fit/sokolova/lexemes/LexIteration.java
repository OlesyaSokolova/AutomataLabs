package ru.nsu.fit.sokolova.lexemes;

import ru.nsu.fit.sokolova.regexp.tree.RegexpTree;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

import static ru.nsu.fit.sokolova.lexemes.LexemePriority.ITERATION_PRIORITY;

public class LexIteration extends Lexeme
{
    public LexIteration(Character value)
    {
        super(LexemeType.OP_ITERATION, value, ITERATION_PRIORITY);
    }

    @Override
    public void insertIntoTree(RegexpTree tree)
    {
        TreeNode localCurrent = tree.getCurrent();

        TreeNode previous;
        if(localCurrent.nodeLexeme_ == null)
        {
            localCurrent.nodeLexeme_ = this;
            localCurrent.rightChild_ = null;
        }
        else
        {
            previous = localCurrent;
            localCurrent = localCurrent.rightChild_;
            Lexeme rightLexeme = localCurrent.nodeLexeme_;
            localCurrent.nodeLexeme_ = this;
            localCurrent.parent_ = previous;
            localCurrent.leftChild_ = new TreeNode();
            previous = localCurrent;
            localCurrent = localCurrent.leftChild_;
            localCurrent.nodeLexeme_ = rightLexeme;
            localCurrent.parent_ = previous;
            localCurrent = localCurrent.parent_;
            localCurrent.rightChild_ = null;
        }

        tree.setCurrent(localCurrent);
    }
}