import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissor extends JFrame implements ActionListener {

    private final JButton rockButton, paperButton, scissorsButton;
    private final JLabel resultLabel, scoreLabel;
    private final String[] choices = {"Rock", "Paper", "Scissors"};
    private int playerScore = 0;
    private int computerScore = 0;
    private final Random random = new Random();
    private int roundCounter = 0; // Declare roundCounter as a class-level variable

    public RockPaperScissor() {

        setTitle("Rock Paper Scissors");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.lightGray);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        rockButton = createStyledButton("Rock", "C:/Users/deepi/Desktop/INTERNSHIPS/TechNoHacks/RockPaperScissor/src/rock.jpg");
        paperButton = createStyledButton("Paper", "C:/Users/deepi/Desktop/INTERNSHIPS/TechNoHacks/RockPaperScissor/src/paper.jpg");
        scissorsButton = createStyledButton("Scissors","C:/Users/deepi/Desktop/INTERNSHIPS/TechNoHacks/RockPaperScissor/src/scissors.jpg");

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        resultLabel = new JLabel("Choose your move!");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        scoreLabel = new JLabel("Score: Player - " + playerScore + " | Computer - " + computerScore);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        add(buttonPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.NORTH);
        add(scoreLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton createStyledButton(String text, String iconPath) {
        JButton button = new JButton();
        button.setToolTipText(text);

        // Load and scale the icon
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);
        button.setIcon(icon);

        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerMove = ((JButton) e.getSource()).getToolTipText();
        String computerMove = choices[random.nextInt(choices.length)];
        String result = determineWinner(playerMove, computerMove);
        resultLabel.setText("<html><div style='text-align: center;'>" +
                            "Player chose <b>" + playerMove + "</b>. " +
                            "Computer chose <b>" + computerMove + "</b>.<br/>" +
                            "<font color='blue'>" + result + "</font></div></html>");

        // Update the score
        if (result.equals("Player wins!")) {
            playerScore++;
        } else if (result.equals("Computer wins!")) {
            computerScore++;
        }

        // Update the score label
        scoreLabel.setText("Score: Player - " + playerScore + " | Computer - " + computerScore);

        // Increment the round counter
        roundCounter++;

        // Determine the winner after every 3 rounds and display in a pop-up message
        if (roundCounter % 3 == 0) {
            String winner;
            if (playerScore > computerScore) {
                winner = "Player";
            } else if (playerScore < computerScore) {
                winner = "Computer";
            } else {
                winner = "It's a tie!";
            }

            // Display the winner in a pop-up message
            JOptionPane.showMessageDialog(this, "The winner after 3 rounds is: " + winner, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            
            // Reset the game state for a new game
            playerScore = 0;
            computerScore = 0;
            roundCounter = 0;
            resultLabel.setText("Choose your move!");
        }
    }

    private String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove))
            return "It's a tie!";
        else if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            playerScore++;
            return "Player wins!";
        } else {
            computerScore++;
            return "Computer wins!";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RockPaperScissor::new);
    }
}
