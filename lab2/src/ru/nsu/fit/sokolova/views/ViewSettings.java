package ru.nsu.fit.sokolova.views;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ViewSettings
{
    public static final String INPUT_FORM_PATH = "/ru/nsu/fit/sokolova/views/inputForm.fxml";
    public static final String RESULT_PATH = "/ru/nsu/fit/sokolova/views/result.fxml";

    public static final String PROGRAM_TITLE = "Context Free Grammar Processor";
    public static final String RESULT_TITLE = "Result";

    public static final double RESULT_WIDTH = 1000;
    public static final double RESULT_HEIGHT = 500;

    public static final double INPUT_FORM_WIDTH = 750;
    public static final double INPUT_FORM_HEIGHT = 500;
    public static double LETTER_WIDTH = 5;

    public static final Paint FILLER = Color.valueOf("#268134");
    public static final double MAIN_FONT = 20.0;

    public static int RESULTS_NUMBER = 0;
    public static double MAX_WIDTH_OF_PARTIAL_RESULT = Double.MAX_VALUE;

    public static void setResultsSettings(int NEW_NUMBER, int resultsLength)
    {
        RESULTS_NUMBER = NEW_NUMBER;
        MAX_WIDTH_OF_PARTIAL_RESULT = RESULT_WIDTH/RESULTS_NUMBER;
        LETTER_WIDTH = RESULT_WIDTH / resultsLength;
    }

}
