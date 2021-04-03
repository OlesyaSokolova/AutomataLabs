package ru.nsu.fit.sokolova.lexemes;

import com.sun.source.tree.Tree;
import ru.nsu.fit.sokolova.regexp.tree.RegexpTree;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

import static ru.nsu.fit.sokolova.lexemes.LexemePriority.DEFAULT_PRIORITY;

public class LexRightBracket extends Lexeme
{
    public LexRightBracket(Character value)
    {
        super(LexemeType.RIGHT_BRACKET, value, DEFAULT_PRIORITY);
    }

    @Override
    public void insertIntoTree(RegexpTree tree)
    {
        TreeNode localCurrent = tree.getCurrent();
        TreeNode localRoot = tree.getRoot();

        if(!this.isLast())
        {
            TreeNode newRoot = new TreeNode();
            localRoot.parent_ = newRoot;
            newRoot.leftChild_ = localRoot;
            localRoot = newRoot;
            localCurrent = localRoot;
        }

        tree.setCurrent(localCurrent);
        tree.setRoot(localRoot);
    }
}