import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameBoard extends JPanel {
    private final int size = 4;
    private int[][] board;
    private Random random;
    private Color[] currentColorScheme;

    public GameBoard() {
        setFocusable(true);
        random = new Random();
        board = new int[size][size];
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean needToAddTile = false;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    needToAddTile = moveTiles(Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    needToAddTile = moveTiles(Direction.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    needToAddTile = moveTiles(Direction.UP);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    needToAddTile = moveTiles(Direction.DOWN);
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    resetGame();
                }
                if (needToAddTile) {
                    addTile();
                }
                repaint();
            }
        });
        resetGame();
    }
    public void setColorScheme(Color[] colorScheme) {
        currentColorScheme = colorScheme;
        repaint();
    }
    private Color getTileColor(int value) {
        int index = (int)(Math.log(value) / Math.log(2)) - 1;
        if (index >= 0 && index < currentColorScheme.length) {
            return currentColorScheme[index];
        } else {
            return Color.GRAY;
        }
    }

    private void resetGame() {
        board = new int[size][size];
        addTile();
        addTile();
    }

    private void addTile() {
        int x, y;
        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while (board[x][y] != 0);

        board[x][y] = random.nextInt(10) < 9 ? 2 : 4;
    }

    private boolean moveTiles(Direction direction) {
        boolean moved = false;
        int[] line = new int[size];
    
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (direction) {
                    case LEFT:
                        line[j] = board[i][j];
                        break;
                    case RIGHT:
                        line[j] = board[i][size - 1 - j];
                        break;
                    case UP:
                        line[j] = board[j][i];
                        break;
                    case DOWN:
                        line[j] = board[size - 1 - j][i];
                        break;
                }
            }
    
            int target = 0;
            for (int j = 1; j < size; j++) {
                if (line[j] == 0) continue; 
    
                if (line[target] == line[j]) {
                    line[target] *= 2; 
                    line[j] = 0;
                    moved = true;
                } else if (line[target] == 0) {
                    line[target] = line[j];
                    line[j] = 0;
                    moved = true;
                    j = target; 
                } else {
                    target++;
                    if (target != j) {
                        line[target] = line[j];
                        line[j] = 0;
                        moved = true;
                    }
                }
            }
            for (int j = 0; j < size; j++) {
                int value = line[j];
                switch (direction) {
                    case LEFT:
                        if (board[i][j] != value) {
                            board[i][j] = value;
                            moved = true;
                        }
                        break;
                    case RIGHT:
                        if (board[i][size - 1 - j] != value) {
                            board[i][size - 1 - j] = value;
                            moved = true;
                        }
                        break;
                    case UP:
                        if (board[j][i] != value) {
                            board[j][i] = value;
                            moved = true;
                        }
                        break;
                    case DOWN:
                        if (board[size - 1 - j][i] != value) {
                            board[size - 1 - j][i] = value;
                            moved = true;
                        }
                        break;
                }
            }
        }
        return moved;
    }
    
    private enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                drawTile(g, board[x][y], x, y);
            }
        }
    }

    private void drawTile(Graphics g, int value, int x, int y) {
        g.setColor(getTileColor(value));
        g.fillRect(y * 80, x * 80, 80, 80);
        g.setColor(Color.BLACK);
        g.drawRect(y * 80, x * 80, 80, 80);
        if (value != 0) {
            Font myFont = new Font("Courier Bold", Font.BOLD, 30);
            g.setFont(myFont);
            String valueStr = String.valueOf(value);
    
            FontMetrics metrics = g.getFontMetrics(myFont);
            int textX = y * 80 + (80 - metrics.stringWidth(valueStr)) / 2;
            int textY = x * 80 + (80 - metrics.getHeight()) / 2 + metrics.getAscent();
    
            g.drawString(valueStr, textX, textY);
        }
    }

    private Color getTileColor(int value, Color[] colorScheme) {
        int index = (int)(Math.log(value) / Math.log(2)) - 1;
        if (index >= 0 && index < colorScheme.length) {
            return colorScheme[index];
        } else {
            return Color.GRAY;
        }
    }
    
    
    
}
