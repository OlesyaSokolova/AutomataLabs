package ru.nsu.fit.sokolova.operations;

import ru.nsu.fit.sokolova.lexemes.LexemeType;
import ru.nsu.fit.sokolova.regexp.tree.RegexpTree;
import ru.nsu.fit.sokolova.regexp.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class OperationsFactory
{
    private HashMap<OperationType, Operation> operationsMap_;
    private HashMap<LexemeType, OperationType> lexemesAsOperations_;

    public OperationsFactory()
    {
        operationsMap_ = new HashMap<OperationType, Operation>();
        operationsMap_.put(OperationType.CONCATENATION, new OpConcatenation());
        operationsMap_.put(OperationType.DISJUNCTION, new OpDisjunction());
        operationsMap_.put(OperationType.ITERATION, new OpIteration());

        lexemesAsOperations_ = new HashMap<LexemeType, OperationType>();
        lexemesAsOperations_.put(LexemeType.OP_CONCATENATION, OperationType.CONCATENATION);
        lexemesAsOperations_.put(LexemeType.OP_DISJUNCTION, OperationType.DISJUNCTION);
        lexemesAsOperations_.put(LexemeType.OP_ITERATION, OperationType.ITERATION);

    };
    public Operation createOperation(TreeNode regexp) throws CloneNotSupportedException
    {
        Operation result = null;
        LexemeType lexemeType = regexp.nodeLexeme_.getType();
        OperationType operationType = lexemesAsOperations_.get(lexemeType);
        result = operationsMap_.get(operationType);
        Operation clone = result.clone();
        return clone;
    }
}
