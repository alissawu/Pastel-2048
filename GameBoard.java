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
            g.drawString(String.valueOf(value), y * 80 + 35, x * 80 + 45);
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
    private static final Color[] purpleScheme = {
        new Color(243, 243, 252),
        new Color(237, 237, 248),
        new Color(232, 231, 243),
        new Color(226, 225, 239),
        new Color(220, 219, 234),
        new Color(215, 213, 230),
        new Color(210, 207, 225),
        new Color(204, 201, 220),
        new Color(199, 196, 216),
        new Color(194, 190, 211),
        new Color(189, 184, 207),
        new Color(184, 178, 202),
        new Color(179, 172, 198),
        new Color(174, 166, 193),
        new Color(170, 161, 188),
        new Color(165, 155, 184),
        new Color(160, 149, 179),
        new Color(156, 143, 175),
        new Color(151, 138, 170),
        new Color(147, 132, 165),
        new Color(143, 126, 161),
        new Color(138, 121, 156),
        new Color(134, 115, 151),
        new Color(130, 110, 146),
        new Color(126, 104, 142),
        new Color(122, 99, 137),
        new Color(118, 93, 132),
        new Color(114, 88, 127),
        new Color(110, 82, 123),
        new Color(106, 77, 118),
        new Color(102, 71, 113),
        new Color(98, 66, 108),
        new Color(95, 61, 104),
        new Color(91, 55, 99),
        new Color(87, 50, 94),
        new Color(84, 44, 89),
        new Color(80, 39, 84),
        new Color(76, 33, 80),
        new Color(73, 28, 75),
        new Color(69, 22, 70)
    };
    public static Color[] getPurpleScheme() {
        return purpleScheme;
    }
    
    private static final Color[] pinkScheme = {
        new Color(252, 243, 251),
        new Color(247, 237, 246),
        new Color(242, 230, 241),
        new Color(238, 224, 236),
        new Color(233, 218, 231),
        new Color(228, 212, 225),
        new Color(224, 205, 220),
        new Color(219, 199, 215),
        new Color(215, 193, 210),
        new Color(210, 187, 205),
        new Color(205, 181, 200),
        new Color(201, 175, 195),
        new Color(197, 168, 190),
        new Color(192, 162, 185),
        new Color(188, 156, 179),
        new Color(183, 150, 174),
        new Color(179, 144, 169),
        new Color(175, 139, 164),
        new Color(170, 133, 159),
        new Color(166, 127, 154),
        new Color(162, 121, 149),
        new Color(157, 115, 144),
        new Color(153, 109, 139),
        new Color(149, 103, 134),
        new Color(145, 98, 129),
        new Color(141, 92, 124),
        new Color(136, 86, 119),
        new Color(132, 81, 114),
        new Color(128, 75, 109),
        new Color(124, 69, 104),
        new Color(120, 64, 99),
        new Color(115, 58, 94),
        new Color(111, 52, 89),
        new Color(107, 46, 84),
        new Color(103, 40, 80),
        new Color(99, 34, 75),
        new Color(95, 28, 70),
        new Color(90, 21, 65),
        new Color(86, 14, 61),
        new Color(82, 5, 56)
    };
    
    public static Color[] getPinkScheme() {
        return pinkScheme;
    }
    
    
}
