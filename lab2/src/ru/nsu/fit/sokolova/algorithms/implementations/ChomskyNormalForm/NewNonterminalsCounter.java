package ru.nsu.fit.sokolova.algorithms.implementations.ChomskyNormalForm;

public class NewNonterminalsCounter
{
    private static final int START_NUMBER = 0;
    private static int counter = START_NUMBER;

    public static int getNextNumber()
    {
        counter++;
        return counter;
    }

}
