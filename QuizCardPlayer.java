
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuizCardPlayer {

    private ArrayList cardList;
    private QuizCard currentCard;
    private QuizCard firstCard;
    private Iterator cardIterator;
    private JFrame frame;
    private JButton nextButton;
    private JButton loadCardsButton;
    private boolean isShowAnswer;
    private JTextArea question;
    private JTextArea answer;
    private JPanel mainPanel;

    public static void main (String[] args) {
        QuizCardPlayer qReader = new QuizCardPlayer();
        qReader.renderLoadCardsGui();
    }

    public void renderLoadCardsGui() {
        mainPanel = new JPanel();

        loadCardsButton = new JButton("Load card set");
        loadCardsButton.addActionListener(new OpenFileDialogListener());
        mainPanel.add(loadCardsButton);

        frame = QuizCardUtils.buildJFrame(mainPanel);
    }

    public class NextCardListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (isShowAnswer) {
                answer.setText(currentCard.getAnswer());
                nextButton.setText("Next Card");
                isShowAnswer = false;
            } else {
                if (cardIterator.hasNext()) {

                    showNextCard();

                } else {
                        showFirstCard();
                    }
                }
            }
    }

    public class OpenFileDialogListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
            renderCardGui();
            showFirstCard();
        }
    }

    private void loadFile(File file) {
        cardList = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                makeCard(line);
            }
            reader.close();

        } catch(Exception ex) {
            System.out.println("couldn't read the card file");
            ex.printStackTrace();
        }
        cardIterator = cardList.iterator();
    }

    private void renderCardGui() {
        mainPanel.remove(loadCardsButton);

        question = new JTextArea(6,20);
        answer = new JTextArea(6,20);
        JScrollPane qScroller = QuizCardUtils.buildJScrollPane(question);
        JScrollPane aScroller = QuizCardUtils.buildJScrollPane(answer);
        JLabel qLabel = new JLabel("Question");
        JLabel aLabel = new JLabel("Answer");

        nextButton = new JButton("Show Question");
        nextButton.addActionListener(new NextCardListener());

        mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(nextButton);

        frame = QuizCardUtils.buildJFrame(mainPanel);
    }

    private void makeCard(String lineToParse) {

        StringTokenizer parser = new StringTokenizer(lineToParse, "/");
        if (parser.hasMoreTokens()) {
            QuizCard card = new QuizCard(parser.nextToken(), parser.nextToken());
            cardList.add(card);
        }
    }

    private void showNextCard() {
        currentCard = (QuizCard) cardIterator.next();
        question.setText(currentCard.getQuestion());
        answer.setText("");
        nextButton.setText("Show Answer");
        isShowAnswer = true;
    }

    private void showFirstCard() {
        firstCard = (QuizCard) cardList.get(0);
        question.setText(currentCard.getQuestion());
        answer.setText("");
        nextButton.setText("Show Answer");
        isShowAnswer = true;
    }
}
      



