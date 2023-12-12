import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameBoard extends JPanel {
    private final int size = 4;
    private int[][] board;
    private Random random;

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
            g.drawString(String.valueOf(value), y * 80 + 35, x * 80 + 45);
        }
    }

    private Color getTileColor(int value) {
        switch (value) {
            case 2: return new Color(198, 199, 229);
            case 4: return new Color(171, 174, 215);
            case 8: return new Color(149, 151, 202);
            case 16: return new Color(149, 149, 201);
            case 32: return new Color(129, 131, 190);
            case 64: return new Color(113, 113, 177);
            case 128: return new Color(113, 117, 164);
            //idea for future; create set of colors, or a rule it goes by ie decreases 20 each time or sth

            default: return Color.GRAY;
        }
    }
}
