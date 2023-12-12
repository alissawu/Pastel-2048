import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame();
        GameBoard gameBoard = new GameBoard();

        gameFrame.setTitle("2048 Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(340, 400);
        gameFrame.setResizable(false);
        gameFrame.add(gameBoard);
        gameFrame.setVisible(true);
    }
}
