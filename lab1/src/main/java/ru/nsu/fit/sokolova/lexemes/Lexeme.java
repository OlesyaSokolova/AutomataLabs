package ru.nsu.fit.sokolova.lexemes;

import ru.nsu.fit.sokolova.regexp.tree.RegexpTree;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

import static ru.nsu.fit.sokolova.lexemes.LexemePriority.*;

abstract public class Lexeme
{
    protected LexemeType type_;
    private int priority_;
    private Character value_;
    private boolean isLast_;

    public Lexeme(LexemeType type, Character value)
    {
        type_ = type;
        value_ = value;
        priority_ = DEFAULT_PRIORITY;
    }

    public Lexeme(LexemeType type, Character value, int priority)
    {
        type_ = type;
        value_ = value;
        priority_ = priority;
    }

    public Integer getPriority()
    {
        return priority_;
    }

    public boolean isLast()
    {
        return isLast_;
    }

    public void setLast(boolean last)
    {
        isLast_ = last;
    }

    public LexemeType getType()
    {
        return type_;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value_);
    }

    public void insertIntoTree(RegexpTree tree)
    {
        TreeNode localCurrent = tree.getCurrent();
        TreeNode localRoot = tree.getRoot();
        TreeNode previous;

        if(localCurrent.nodeLexeme_ == null)
        {
            localCurrent.nodeLexeme_ = this;
            localCurrent.rightChild_ = new TreeNode();
            previous = localCurrent;
            localCurrent = localCurrent.rightChild_;
            localCurrent.parent_ = previous;
        }
        else
        {
            int curNodeLexPriority = localCurrent.nodeLexeme_.getPriority();
            int lexPriority = this.getPriority();
            if(curNodeLexPriority < lexPriority)
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
                localCurrent.rightChild_ = new TreeNode();
                previous = localCurrent;
                localCurrent = localCurrent.rightChild_;
                localCurrent.parent_ = previous;
            }
            else if(curNodeLexPriority >= lexPriority)
            {
                TreeNode newRoot  = new TreeNode();
                localRoot.parent_ = newRoot;
                newRoot.leftChild_ = localRoot;
                newRoot.nodeLexeme_ = this;
                newRoot.rightChild_ = new TreeNode();
                localRoot = newRoot;
                localCurrent = newRoot.rightChild_;
                localCurrent.parent_ = localRoot;
            }
        }
        tree.setCurrent(localCurrent);
        tree.setRoot(localRoot);
    }
}
