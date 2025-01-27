import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyEvent;

public class Snake {
    private final int NUM_TILES_X;
    private final int NUM_TILES_Y;
    private final int TILE_SIZE;
    private ArrayList<Point> snake;
    private Point food;
    private int direction;
    private boolean isGameOver;

    public Snake(int width, int height, int tileSize) {
        NUM_TILES_X = width / tileSize;
        NUM_TILES_Y = height / tileSize;
        TILE_SIZE = tileSize;
        snake = new ArrayList<>();
        snake.add(new Point(NUM_TILES_X / 2, NUM_TILES_Y / 2)); // Голова
        snake.add(new Point(NUM_TILES_X / 2 - 1, NUM_TILES_Y / 2)); // Первая часть тела
        snake.add(new Point(NUM_TILES_X / 2 - 2, NUM_TILES_Y / 2)); // Вторая часть тела
        direction = KeyEvent.VK_RIGHT;
        isGameOver = false;
        spawnFood();
    }

    public ArrayList<Point> getSnake() {
        return snake;
    }

    public Point getFood() {
        return food;
    }

    public void moveSnake() {
        Point head = snake.get(0);
        Point newHead = null;

        switch (direction) {
            case KeyEvent.VK_UP: newHead = new Point(head.x, head.y - 1); break;
            case KeyEvent.VK_DOWN: newHead = new Point(head.x, head.y + 1); break;
            case KeyEvent.VK_LEFT: newHead = new Point(head.x - 1, head.y); break;
            case KeyEvent.VK_RIGHT: newHead = new Point(head.x + 1, head.y); break;
        }

        snake.add(0, newHead);

        if (newHead.equals(food)) {
            spawnFood(); // Змейка съела еду, спавним новую
        } else {
            snake.remove(snake.size() - 1); // Убираем хвост
        }
    }

    public boolean checkCollisions() {
        Point head = snake.get(0);

        // Проверка на столкновение с границами
        if (head.x < 0 || head.x >= NUM_TILES_X || head.y < 0 || head.y >= NUM_TILES_Y) {
            return true;
        }

        // Проверка на столкновение с собой
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void changeDirection(int newDirection) {
        if ((direction == KeyEvent.VK_LEFT && newDirection != KeyEvent.VK_RIGHT) ||
                (direction == KeyEvent.VK_RIGHT && newDirection != KeyEvent.VK_LEFT) ||
                (direction == KeyEvent.VK_UP && newDirection != KeyEvent.VK_DOWN) ||
                (direction == KeyEvent.VK_DOWN && newDirection != KeyEvent.VK_UP)) {
            direction = newDirection;
        }
    }

    public void spawnFood() {
        Random rand = new Random();
        food = new Point(rand.nextInt(NUM_TILES_X), rand.nextInt(NUM_TILES_Y));
    }

    public void restartGame() {
        snake.clear();
        snake.add(new Point(NUM_TILES_X / 2, NUM_TILES_Y / 2)); // Голова
        snake.add(new Point(NUM_TILES_X / 2 - 1, NUM_TILES_Y / 2)); // Первая часть тела
        snake.add(new Point(NUM_TILES_X / 2 - 2, NUM_TILES_Y / 2)); // Вторая часть тела
        spawnFood();
        direction = KeyEvent.VK_RIGHT;
    }
}
