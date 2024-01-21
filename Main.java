import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame();
        GameBoard gameBoard = new GameBoard();

        JComboBox<String> colorSchemeDropdown = new JComboBox<>(new String[]{"Purple", "Pink", "BluePurple"});
        colorSchemeDropdown.addActionListener(e -> {
            if (colorSchemeDropdown.getSelectedItem().equals("Purple")) {
                gameBoard.setColorScheme(ColorSchemes.PURPLE_SCHEME);
            } else if (colorSchemeDropdown.getSelectedItem().equals("Pink")) {
                gameBoard.setColorScheme(ColorSchemes.PINK_SCHEME);
            } else if (colorSchemeDropdown.getSelectedItem().equals("BluePurple")) {
                gameBoard.setColorScheme(ColorSchemes.BLUE_PURPLE_SCHEME);
            }
            gameBoard.requestFocusInWindow(); // Request focus back to the game board, from the combo box
        });

        gameFrame.setTitle("2048 Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(340, 400);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(colorSchemeDropdown, BorderLayout.NORTH);
        gameFrame.add(gameBoard, BorderLayout.CENTER);
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
        gameFrame.getContentPane().setBackground(Color.WHITE);

        gameBoard.requestFocusInWindow(); // Request focus when window is first shown
    }
}
