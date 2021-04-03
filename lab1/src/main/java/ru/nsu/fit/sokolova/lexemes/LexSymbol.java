package ru.nsu.fit.sokolova.lexemes;


import ru.nsu.fit.sokolova.regexp.tree.RegexpTree;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

import static ru.nsu.fit.sokolova.lexemes.LexemePriority.DEFAULT_PRIORITY;

public class LexSymbol extends Lexeme
{
    public LexSymbol(Character value)
    {
        super(LexemeType.SYMBOL, value, DEFAULT_PRIORITY);
    }

    @Override
    public void insertIntoTree(RegexpTree tree)
    {
        TreeNode localCurrent = tree.getCurrent();
        if(localCurrent.parent_ == null)
        {
            if(this.isLast())
            {
                localCurrent.nodeLexeme_ = this;
                tree.setCurrent(localCurrent);
                return;
            }
            TreeNode previous;
            localCurrent.leftChild_ = new TreeNode();
            previous = localCurrent;
            localCurrent = localCurrent.leftChild_;
            localCurrent.parent_ = previous;
        }
        localCurrent.nodeLexeme_ = this;
        localCurrent = localCurrent.parent_;
        tree.setCurrent(localCurrent);
    }
}