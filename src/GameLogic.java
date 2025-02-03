import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameLogic extends JPanel implements ActionListener {
    private final int TILE_SIZE = 40;
    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;
    private boolean isGameOver = false;

    private Button restartButton;  // Кнопка рестарта
    private Snake snake;
    private Timer timer;
    private Food food;

    public GameLogic() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        restartButton = new Button(WIDTH / 2 - 50, HEIGHT / 2 + 100);
        restartButton.addRestartActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        setLayout(null);
        add(restartButton);


        snake = new Snake(WIDTH, HEIGHT, TILE_SIZE);
        timer = new Timer(100, this);
        food = new Food(WIDTH, HEIGHT, TILE_SIZE);
        timer.start();

        // Обработчик клавиш
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                snake.changeDirection(e.getKeyCode());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            snake.moveSnake();
            checkCollisions();
            repaint();
        }
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
            restartButton.setVisible(true);
        } else {
            // Отображаем змейку
            for (Point p : snake.getSnake()) {
                g.setColor(p.equals(snake.getSnake().get(0)) ? Color.RED : Color.GREEN);
                g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            // Отображаем еду
            g.setColor(Color.RED);
            Point foodPoint = food.getFood();
            g.fillRect(foodPoint.x * TILE_SIZE, foodPoint.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    private void checkCollisions() {
        if (snake.checkDeathCollisions()) {
            isGameOver = true;
            restartButton.setVisible(true);
        }
        if (snake.checkCollision(food.getFood())) {
            food.spawnFood();
            snake.addTail();
        }
        if ()
    }

    private void restartGame() {
        snake.restartGame();
        isGameOver = false;
        restartButton.setVisible(false);
        repaint();
    }
}
