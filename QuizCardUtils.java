import javax.swing.*;
import java.awt.*;

/**
 * Created by john.gaffney on 4/24/16.
 */
public class QuizCardUtils {

    public static JFrame buildJFrame(JPanel panel){
        JFrame frame = new JFrame("Quiz Card Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(500,600);
        frame.setVisible(true);
        return frame;
    }

    public static JScrollPane buildJScrollPane(JTextArea inputBox) {
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        inputBox.setLineWrap(true);
        inputBox.setWrapStyleWord(true);
        inputBox.setFont(bigFont);

        JScrollPane newScroller = new JScrollPane(inputBox);
        newScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        newScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return newScroller;
    }
}
