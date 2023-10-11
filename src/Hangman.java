package HagmanGame.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Hangman extends JFrame implements ActionListener {
    private final String[] words = {"hangman", "java", "swing", "programming", "openai", "computer", "laptop"};
    private String wordToGuess;
    private int guessesLeft = 6;
    private StringBuilder hiddenWord;
    private String victoryMessage = "Congratulations! You won!";
    private String defeatMessage = String.format("You lose! The word was: %s", wordToGuess);

    private JFrame gameFrame;
    private JLabel hiddenWordLabel;
    private JLabel guessesLeftLabel;
    private JTextField guessTextField;
    private JButton guessButton;
    private JPanel mainPanel;

    public Hangman() {
        gameFrame = new JFrame();
        gameFrame.setTitle("Hangman Game");
        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameFrame.setResizable(false);

        hiddenWordLabel = new JLabel();
        guessesLeftLabel = new JLabel(String.format("Guesses left: %s", String.valueOf(guessesLeft)));
        guessTextField = new JTextField(10);
        guessButton = new JButton("Guess");

        guessButton.addActionListener(this);
        guessTextField.addActionListener(this);

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(hiddenWordLabel);
        mainPanel.add(guessesLeftLabel);
        mainPanel.add(guessTextField);
        mainPanel.add(guessButton);

        gameFrame.getContentPane().add(mainPanel);

        initializeGame();

        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    public void initializeGame() {
        Random random = new Random();

        int selectedWordPosition = random.nextInt(0, words.length);
        wordToGuess = words[selectedWordPosition];
        hiddenWord = new StringBuilder();

        for (int i = 0; i < wordToGuess.length(); i++)  {
            hiddenWord.append("_ ");
        }
        hiddenWordLabel.setText(hiddenWord.toString());
        resetNumberOfGuesses();
    }

    public void resetNumberOfGuesses() {
        guessesLeft = 6;
        guessesLeftLabel.setText(String.format("Guesses left: %s", String.valueOf(guessesLeft)));
    }

    public void updateHiddenWord(char guess) {
        boolean charLocated = false;

        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                hiddenWord.setCharAt(i, guess);
                charLocated = true;
            }
        }

        hiddenWordLabel.setText(hiddenWord.toString());
        verifyGuess(charLocated);
    }

    public void verifyGuess(boolean guess) {
        if (!guess) {
            guessesLeft--;
            guessesLeftLabel.setText(String.format("Guesses left: %s", String.valueOf(guessesLeft)));
            verifyGameOver(guessesLeft);
        } else if (hiddenWord.toString().equals(wordToGuess)) {
            gameOver(victoryMessage);
        }
    }

    public void verifyGameOver(int numberOfGuesses) {
        if (numberOfGuesses == 0) {
            gameOver(defeatMessage);
        }
    }

    public void gameOver(String message) {
        guessTextField.setEnabled(false);
        guessButton.setEnabled(false);
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        initializeGame();
        guessTextField.setEnabled(true);
        guessButton.setEnabled(true);
        guessTextField.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton || e.getSource() == guessTextField) {
            String guessText = guessTextField.getText();
            if (guessText.length() > 0) {
                char guess = guessText.charAt(0);
                updateHiddenWord(guess);
                guessTextField.setText("");
            }
        }
    }
}
