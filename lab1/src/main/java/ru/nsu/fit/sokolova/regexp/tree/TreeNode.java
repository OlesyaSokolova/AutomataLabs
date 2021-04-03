package ru.nsu.fit.sokolova.regexp.tree;

import javafx.util.Pair;
import ru.nsu.fit.sokolova.lexemes.Lexeme;
import ru.nsu.fit.sokolova.lexemes.LexemeType;

public class TreeNode
{
    public Lexeme nodeLexeme_;
    public TreeNode leftChild_;
    public TreeNode rightChild_;
    public TreeNode parent_;

    @Override
    public String toString()
    {
        if(nodeLexeme_ != null)
        {
            return nodeLexeme_.toString();
        }
        else
        {
            return "";
        }
    }

    public TreeNode(Lexeme newNodeLexeme)
    {
        nodeLexeme_ = newNodeLexeme;
    }

    public TreeNode()
    {
    }

    public void printNode()
    {
        System.out.println("lexeme= " + this.toString());
    }

    public boolean canBeSplitted()
    {
        //if(root_.leftChild_ != null && root_.rightChild_ != null)
        if(nodeLexeme_.getType().equals(LexemeType.SYMBOL))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Pair<TreeNode, TreeNode> split()
    {
        TreeNode leftRegexp = leftChild_;
        TreeNode rightRegexp = rightChild_;
        return new Pair<>(leftRegexp, rightRegexp);
    }
}
