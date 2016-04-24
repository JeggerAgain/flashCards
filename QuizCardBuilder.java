
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class QuizCardBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList cardList = new ArrayList();
    private JPanel mainPanel = new JPanel();
    private JFrame frame;

    public static void main (String[] args) {
        QuizCardBuilder builder = new QuizCardBuilder();
        builder.renderQuestionBuilderGui();
    }

    public void renderQuestionBuilderGui() {
        question = new JTextArea(6,20);
        answer = new JTextArea(6,20);
        JScrollPane qScroller = QuizCardUtils.buildJScrollPane(question);
        JScrollPane aScroller = QuizCardUtils.buildJScrollPane(answer);
        JLabel qLabel = new JLabel("Enter Question:");
        JLabel aLabel = new JLabel("Enter Answer:");


        JButton nextButton = new JButton("Create Another Card");
        nextButton.addActionListener(new NextCardListener());
        JButton saveButton = new JButton("Save Cards");
        saveButton.addActionListener(new SaveButtonListener());
        JButton studyButton = new JButton("Start Studying");
        studyButton.addActionListener(new StudyListener());

        mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(nextButton);
        mainPanel.add(saveButton);
        mainPanel.add(studyButton);

        frame = QuizCardUtils.buildJFrame(mainPanel);
    }

    public class NextCardListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        }
    }

    public class StudyListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            QuizCardPlayer newGame = new QuizCardPlayer();
            newGame.renderLoadCardsGui();
        }
    }

    public class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private void saveFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            Iterator cardIterator = cardList.iterator();
            while (cardIterator.hasNext()) {
                QuizCard card = (QuizCard) cardIterator.next();
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();
        } catch(IOException ex) {
            System.out.println("couldn't write the cardList out");
            ex.printStackTrace();
        }
    }
}
       
           
          
          
       