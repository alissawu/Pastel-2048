import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main {
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame();
        GameBoard gameBoard = new GameBoard();

        JComboBox<String> colorSchemeDropdown = new JComboBox<>(new String[]{"Purple", "Pink"});
        colorSchemeDropdown.addActionListener(e -> {
            if (colorSchemeDropdown.getSelectedItem().equals("Purple")) {
                gameBoard.setColorScheme(GameBoard.getPurpleScheme());
            } else if (colorSchemeDropdown.getSelectedItem().equals("Pink")) {
                gameBoard.setColorScheme(GameBoard.getPinkScheme());
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

        gameBoard.requestFocusInWindow(); // Request focus when window is first shown
    }
}
