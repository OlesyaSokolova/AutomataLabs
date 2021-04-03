package ru.nsu.fit.sokolova.lexemes;
import ru.nsu.fit.sokolova.regexp.tree.RegexpTree;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

import static ru.nsu.fit.sokolova.lexemes.LexemePriority.DEFAULT_PRIORITY;

public class LexLeftBracket extends Lexeme
{
    public LexLeftBracket(Character value)
    {
        super(LexemeType.LEFT_BRACKET, value, DEFAULT_PRIORITY);
    }

    @Override
    public void insertIntoTree(RegexpTree tree)
    {
        TreeNode localCurrent = tree.getCurrent();
        TreeNode previous;

        localCurrent.leftChild_ = new TreeNode();
        previous = localCurrent;
        localCurrent = localCurrent.leftChild_;
        localCurrent.parent_ = previous;

        tree.setCurrent(localCurrent);
    }
}
