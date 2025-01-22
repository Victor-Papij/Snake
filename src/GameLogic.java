import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic extends JPanel implements ActionListener {
    private final int TILE_SIZE = 40;
    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;
    private final int NUM_TILES_X = WIDTH / TILE_SIZE;
    private final int NUM_TILES_Y = HEIGHT / TILE_SIZE;
    private Timer timer;
    private ArrayList<Point> snake;
    private Point food;
    private int direction = KeyEvent.VK_RIGHT;
    private boolean isGameOver = false;

    private JButton restartButton;  // Кнопка рестарта

    public GameLogic() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        snake = new ArrayList<>();
        snake.add(new Point(NUM_TILES_X / 2, NUM_TILES_Y / 2));
        snake.add(new Point(NUM_TILES_X / 2 - 1, NUM_TILES_Y / 2));
        snake.add(new Point(NUM_TILES_X / 2 - 2, NUM_TILES_Y / 2));

        spawnFood();

        timer = new Timer(100, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int newDirection = e.getKeyCode();
                if ((direction == KeyEvent.VK_LEFT && newDirection != KeyEvent.VK_RIGHT) ||
                        (direction == KeyEvent.VK_RIGHT && newDirection != KeyEvent.VK_LEFT) ||
                        (direction == KeyEvent.VK_UP && newDirection != KeyEvent.VK_DOWN) ||
                        (direction == KeyEvent.VK_DOWN && newDirection != KeyEvent.VK_UP)) {
                    direction = newDirection;
                }
            }
        });

        // Создание кнопки рестарта
        restartButton = new JButton("Restart");
        restartButton.setBounds(WIDTH / 2 - 50, HEIGHT / 2 + 100, 100, 40);
        restartButton.setFont(new Font("Arial", Font.BOLD, 16));
        restartButton.setFocusable(false);
        restartButton.setVisible(false);  // Кнопка скрыта по умолчанию
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        setLayout(null);
        add(restartButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            moveSnake();
            checkCollisions();
            repaint();
        }
    }

    private void moveSnake() {
        Point head = snake.get(0);
        Point newHead = null;

        switch (direction) {
            case KeyEvent.VK_UP:
                newHead = new Point(head.x, head.y - 1);
                break;
            case KeyEvent.VK_DOWN:
                newHead = new Point(head.x, head.y + 1);
                break;
            case KeyEvent.VK_LEFT:
                newHead = new Point(head.x - 1, head.y);
                break;
            case KeyEvent.VK_RIGHT:
                newHead = new Point(head.x + 1, head.y);
                break;
        }

        snake.add(0, newHead);

        if (newHead.equals(food)) {
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private void checkCollisions() {
        Point head = snake.get(0);

        if (head.x < 0 || head.x >= NUM_TILES_X || head.y < 0 || head.y >= NUM_TILES_Y) {
            isGameOver = true;
        }


        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                isGameOver = true;
                break;
            }
        }

        if (isGameOver) {
            restartButton.setVisible(true);
        }
    }

    private void spawnFood() {
        Random rand = new Random();
        food = new Point(rand.nextInt(NUM_TILES_X), rand.nextInt(NUM_TILES_Y));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));

            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth("Game Over");
            int textHeight = fm.getHeight();


            int x = (WIDTH - textWidth) / 2;
            int y = (HEIGHT - textHeight) / 2 + fm.getAscent();


            g.drawString("Game Over", x, y);

        } else {
            // Отображаем змейку
            for (int i = 0; i < snake.size(); i++) {
                Point p = snake.get(i);

                if (i == 0) { // Если это голова змейки
                    g.setColor(Color.RED); // Цвет головы (красный)
                } else {
                    g.setColor(Color.GREEN); // Цвет тела змейки (зеленый)
                }

                g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }



            g.setColor(Color.RED);
            g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    private void restartGame() {
        // Перезапуск игры
        snake.clear();
        direction = KeyEvent.VK_RIGHT;
        isGameOver = false;
        restartButton.setVisible(false); // Скрываем кнопку рестарта
        snake.add(new Point(NUM_TILES_X / 2, NUM_TILES_Y / 2)); // Голова
        snake.add(new Point(NUM_TILES_X / 2 - 1, NUM_TILES_Y / 2)); // Первая часть тела
        snake.add(new Point(NUM_TILES_X / 2 - 2, NUM_TILES_Y / 2)); // Вторая часть тела
        spawnFood();
        timer.restart();
    }
}
