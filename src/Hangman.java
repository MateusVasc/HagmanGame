package HagmanGame.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hangman extends JFrame implements ActionListener {
    private final String[] words = {"hangman", "java", "swing", "programming", "openai", "computer", "laptop"};
    private String wordToGuess;
    private final int guessesLeft = 6;
    private StringBuilder hiddenWord;

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

        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
