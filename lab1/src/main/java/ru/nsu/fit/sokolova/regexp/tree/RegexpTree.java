package ru.nsu.fit.sokolova.regexp.tree;

import javafx.util.Pair;
import ru.nsu.fit.sokolova.lexemes.Lexeme;
import ru.nsu.fit.sokolova.lexemes.LexemeType;

import java.util.ArrayList;

public class RegexpTree
{
    private TreeNode root_;
    private TreeNode current_;

    public RegexpTree()
    {
        root_ = new TreeNode();
        current_ = root_;
    }

    public TreeNode getRoot()
    {
        return root_;
    }

    public void setRoot(TreeNode newRoot)
    {
        this.root_ = newRoot;
    }

    public TreeNode getCurrent()
    {
        return current_;
    }

    public void setCurrent(TreeNode newCurrent)
    {
        this.current_ = newCurrent;
    }

    public void print(TreeNode startNode)
    {
        if(startNode != null)
        {
            print(startNode.leftChild_);
            startNode.printNode();
            print(startNode.rightChild_);
        }
    }
    public void createTreeFromRegexp(ArrayList<Lexeme> analyzedRegexp)
    {
        for(Lexeme lexeme : analyzedRegexp)
        {
            lexeme.insertIntoTree(this);
        }
    }
}
