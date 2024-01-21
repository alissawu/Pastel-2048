import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GameBoard board = new GameBoard();

        JComboBox<String> colorDropdown = new JComboBox<>(new String[]{"Purple", "Pink", "BluePurple", "BlueGreen"});
        colorDropdown.addActionListener(e -> {
            if (colorDropdown.getSelectedItem().equals("Purple")) {
                board.setColorScheme(ColorSchemes.PURPLE_SCHEME);
            } else if (colorDropdown.getSelectedItem().equals("Pink")) {
                board.setColorScheme(ColorSchemes.PINK_SCHEME);
            } else if (colorDropdown.getSelectedItem().equals("BluePurple")) {
                board.setColorScheme(ColorSchemes.BLUE_PURPLE_SCHEME);
            } else if (colorDropdown.getSelectedItem().equals("BlueGreen")) {
                board.setColorScheme(ColorSchemes.BLUE_GREEN_SCHEME);
            }
            board.requestFocusInWindow(); // Request focus back to the game board, from the combo box
        });

        frame.setTitle("2048 Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(340, 400);
        frame.setLayout(new BorderLayout());
        frame.add(colorDropdown, BorderLayout.NORTH);
        frame.add(board, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
