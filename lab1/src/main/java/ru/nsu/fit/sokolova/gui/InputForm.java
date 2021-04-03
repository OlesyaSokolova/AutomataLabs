package ru.nsu.fit.sokolova.gui;

import ru.nsu.fit.sokolova.finiteAutomata.AutomataBuilder;
import ru.nsu.fit.sokolova.finiteAutomata.TransitionTable;
import ru.nsu.fit.sokolova.regexp.RegexpPreprocessor;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputForm
{
    private static final String ENTER_REGEXP_SUGGESTION = "Enter regexp.";
    private static final String PROGRAM_TITLE = "AUTOMATA BUILDER";
    private static final String RULES_DELIMITER = ": ";
    private static final String START_BUTTON_TEXT = "Create automata!";
    private static final String EXAMPLE_INPUT = "Example: (a+b*)(x+y)";
    private static final String NO_INPUT = "No input.";
    private static final String WRONG_INPUT_WARNING = "Wrong input!";
    private static final String RUNTIME_ERRORR = "Something went wrong! Please, check input and try again.";
    public static final String FONT = "Arial";
    public static final int LABELS_TEXT_SIZE = 25;
    public static final int RULES_TEXT_SIZE = 15;
    public static final int WARNING_TEXT_SIZE = 15;
    public static final Color APP_TEXT_FIELD_COLOR = Color.LIGHT_GRAY;
    public static final Color USER_TEXT_FIELD_COLOR = Color.WHITE;
    public static final Color USER_BORDER_FIELD_COLOR = Color.LIGHT_GRAY;
    private static final int MAX_INPUT_LENGTH = 15;
    private static final int WIDTH_GAP = 0;
    private static final int HEIGHT_GAP = 15;
    private static final String EMPTY_STRING = "";
    private static final String NOTE = "Remember that 'e' means \"empty regexp\"";

    private AutomataBuilder automataBuilder_;
    private HashMap<String, String> inputRegexpRules_;

    private JLabel suggestionToEnterRegexp_;
    private ArrayList<JLabel> regexpRules_;
    private JLabel example_;
    private JTextField inputRegexp_;
    private JTextArea checkResultWarning_;
    private JLabel note_;
    private JButton startButton_;
    private JFrame frame_;

    public InputForm()
    {
        automataBuilder_ = new AutomataBuilder();
        inputRegexpRules_ = new HashMap<>();
        regexpRules_ = new ArrayList<>();
        frame_ = new JFrame(PROGRAM_TITLE);
        initRules();
        setRules();
    }

    private void createUI(Container contentPane)
    {
        BoxLayout boxLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(boxLayout);

        suggestionToEnterRegexp_ = new JLabel(ENTER_REGEXP_SUGGESTION);
        suggestionToEnterRegexp_.setFont(new Font(FONT, Font.PLAIN, LABELS_TEXT_SIZE));
        //suggestionToEnterRegexp_.setBorder(new LineBorder(APP_TEXT_FIELD_COLOR, APP_TEXT_THICKNESS));
        suggestionToEnterRegexp_.setAlignmentX(Component.CENTER_ALIGNMENT);
        suggestionToEnterRegexp_.setBackground(APP_TEXT_FIELD_COLOR);

        example_ = new JLabel(EXAMPLE_INPUT);
        example_.setFont(new Font(FONT, Font.PLAIN, RULES_TEXT_SIZE));
        example_.setAlignmentX(Component.CENTER_ALIGNMENT);
        example_.setBackground(APP_TEXT_FIELD_COLOR);

        note_ = new JLabel(NOTE);
        note_.setFont(new Font(FONT, Font.PLAIN, RULES_TEXT_SIZE));
        note_.setAlignmentX(Component.CENTER_ALIGNMENT);
        note_.setBackground(APP_TEXT_FIELD_COLOR);

        inputRegexp_ = new JTextField(MAX_INPUT_LENGTH);
        inputRegexp_.setFont(new Font(FONT, Font.PLAIN, LABELS_TEXT_SIZE));
        inputRegexp_.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputRegexp_.setEditable(true);

        checkResultWarning_ = new JTextArea (NO_INPUT);
        checkResultWarning_.setFont(new Font(FONT, Font.PLAIN, WARNING_TEXT_SIZE));
        checkResultWarning_.setEditable(false);
        checkResultWarning_.setLineWrap(true);
        checkResultWarning_.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkResultWarning_.setBackground(APP_TEXT_FIELD_COLOR);

        startButton_ = new JButton(START_BUTTON_TEXT);
        startButton_.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton_.setFont(new Font(FONT, Font.PLAIN, LABELS_TEXT_SIZE));
        startButton_.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String regexp = inputRegexp_.getText();
                String preprocessedRegexp = RegexpPreprocessor.preprocessUserInput(regexp);
                if(!preprocessedRegexp.equals(EMPTY_STRING))
                {
                    TransitionTable result = null;
                    try {
                        result = automataBuilder_.build(preprocessedRegexp);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                        checkResultWarning_.setText(RUNTIME_ERRORR);
                        return;
                    }
                    //show result
                    frame_.setVisible(false);
                    showResult(result, preprocessedRegexp);
                }
                else
                {
                    checkResultWarning_.setText(WRONG_INPUT_WARNING);
                }
            }
        });
        Dimension fillerDimension = new Dimension(WIDTH_GAP,HEIGHT_GAP);
        contentPane.add(Box.createRigidArea(fillerDimension));
        contentPane.add(suggestionToEnterRegexp_);
        contentPane.add(Box.createRigidArea(fillerDimension));
        addAllRules(contentPane);
        contentPane.add(Box.createRigidArea(fillerDimension));
        contentPane.add(note_);
        contentPane.add(Box.createRigidArea(fillerDimension));
        contentPane.add(example_);
        contentPane.add(Box.createRigidArea(fillerDimension));
        contentPane.add(inputRegexp_);
        contentPane.add(Box.createRigidArea(fillerDimension));
        contentPane.add(checkResultWarning_);
        contentPane.add(Box.createRigidArea(fillerDimension));
        contentPane.add(startButton_);
        contentPane.add(Box.createRigidArea(fillerDimension));
    }

    private void addAllRules(Container contentPane)
    {
        for(JLabel labelRule: regexpRules_)
        {
            contentPane.add(labelRule);
        }
    }

    private void initRules()
    {
        inputRegexpRules_.put("Concatenation", "ab");
        inputRegexpRules_.put("Disjunction", "a+b");
        inputRegexpRules_.put("Iteration", "a*");
    }

    private void setRules()
    {
        for(Map.Entry<String, String> pair: inputRegexpRules_.entrySet())
        {
            String stringRule = pair.getKey() + RULES_DELIMITER + pair.getValue();
            JLabel labelRule = new JLabel(stringRule);
            labelRule.setFont(new Font(FONT, Font.PLAIN, RULES_TEXT_SIZE));
            labelRule.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelRule.setBackground(APP_TEXT_FIELD_COLOR);
            regexpRules_.add(labelRule);
        }
    }

    public void showForm()
    {
        frame_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame_.getContentPane());

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Открытие окна
                frame_.pack();
                frame_.setLocationRelativeTo(null);
                frame_.setAlwaysOnTop(true);
                frame_.setVisible(true);
            }
        });

    }

    private void showResult(TransitionTable result, String userInput)
    {
        IAutomataPresenter automataPresenter = new AutomataGraph(result, userInput);
        automataPresenter.show();
    }
}
