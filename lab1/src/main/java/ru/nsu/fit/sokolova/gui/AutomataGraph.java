package ru.nsu.fit.sokolova.gui;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;
import ru.nsu.fit.sokolova.finiteAutomata.Transition;
import ru.nsu.fit.sokolova.finiteAutomata.TransitionTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutomataGraph implements IAutomataPresenter
{
    private static final String VERTEX_LETTER = "q";
    private static final int RESULT_WINDOW_WIDTH = 700;
    private static final int RESULT_WINDOW_HEIGHT = 700;
    private static final String RESULT_TITLE = "Result";
    private static final String FONT = "Arial";
    private static final int LABELS_TEXT_SIZE = 20;
    private static final int WIDTH_GAP = 0;
    private static final int HEIGHT_GAP = 20;
    private static final String NEW_AUTOMATA = "Create new automata";
    private static final String FIRST_STATE = VERTEX_LETTER + 0;
    private static final String LAST_STATE = VERTEX_LETTER + 1;

    private static final Color FIRST_STATE_COLOR = new Color(0x94E3CD);
    private static final Color LAST_STATE_COLOR = FIRST_STATE_COLOR;
    private static final Color STATE_COLOR = new Color(0xF6DBFF);

    private JFrame frame_;

    TransitionTable graphData_;
    DirectedSparseMultigraph<String, Edge> graph_;
    JLabel userInput_;
    JButton newAutomataButton_;

    public AutomataGraph(TransitionTable newGraphData, String userInput)
    {
        graphData_ = newGraphData;
        graph_ = new DirectedSparseMultigraph<>();
        frame_ = new JFrame(RESULT_TITLE);
        userInput_ = new JLabel(userInput);
        newAutomataButton_ = new JButton(NEW_AUTOMATA);
    }

    @Override
    public void show()
    {
        prepareGraph();
        CircleLayout<String, Edge> circleLayout = new CircleLayout<String, Edge>(graph_);
        Dimension dimension = new Dimension(RESULT_WINDOW_WIDTH, RESULT_WINDOW_HEIGHT);
        VisualizationViewer<String, Edge> visViewer = new VisualizationViewer<String, Edge>(circleLayout, dimension);

        visViewer.getRenderContext().setVertexLabelTransformer(arg0 -> arg0);
        visViewer.getRenderContext().setEdgeLabelTransformer(arg0 -> String.valueOf(arg0));

        Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
            public Paint transform(String i)
            {
                if(i.equals(FIRST_STATE))
                {
                    return FIRST_STATE_COLOR;
                }
                if(i.equals(LAST_STATE))
                {
                    return LAST_STATE_COLOR;
                }
                return STATE_COLOR;
            }
        };

        DefaultModalGraphMouse<String, Number> graphMouse = new DefaultModalGraphMouse<String, Number>();
        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
        visViewer.setGraphMouse(graphMouse);

        visViewer.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        visViewer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        visViewer.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        visViewer.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

        frame_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addUserInput();
        frame_.getContentPane().add(visViewer);
        addNewAutomataButton();

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                frame_.pack();
                frame_.setLocationRelativeTo(null);
                frame_.setAlwaysOnTop(true);
                frame_.setVisible(true);
            }
        });
    }

    private void addNewAutomataButton()
    {
        BoxLayout boxLayout = new BoxLayout(frame_.getContentPane(), BoxLayout.Y_AXIS);
        frame_.getContentPane().setLayout(boxLayout);

        newAutomataButton_.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                InputForm inputForm = new InputForm();
                frame_.setVisible(false);
                inputForm.showForm();
            }
        });

        newAutomataButton_.setFont(new Font(FONT, Font.PLAIN, LABELS_TEXT_SIZE));
        newAutomataButton_.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame_.getContentPane().add(newAutomataButton_);
        Dimension fillerDimension = new Dimension(WIDTH_GAP, HEIGHT_GAP);
        frame_.getContentPane().add(Box.createRigidArea(fillerDimension));
    }

    private void addUserInput()
    {
        BoxLayout boxLayout = new BoxLayout(frame_.getContentPane(), BoxLayout.Y_AXIS);
        frame_.getContentPane().setLayout(boxLayout);

        userInput_.setFont(new Font(FONT, Font.PLAIN, LABELS_TEXT_SIZE));
        Dimension fillerDimension = new Dimension(WIDTH_GAP, HEIGHT_GAP);
        frame_.getContentPane().add(Box.createRigidArea(fillerDimension));
        userInput_.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame_.getContentPane().add(userInput_);

    }

    private void prepareGraph()
    {
        int vertexsNumber = graphData_.getLastStateNumber() + 1;

        for(int i = 0; i < vertexsNumber; i++)
        {
            String vertexTitle = VERTEX_LETTER + i;
            graph_.addVertex(vertexTitle);
        }

        for(Transition transition: graphData_.getTransitions())
        {
            String startVertex = VERTEX_LETTER + transition.getStartState();
            String endVertex = VERTEX_LETTER + transition.getEndState();
            String edgeTitle = transition.getRegexp().toString();
            graph_.addEdge(new Edge(edgeTitle), startVertex, endVertex, EdgeType.DIRECTED);
        }
    }
}
