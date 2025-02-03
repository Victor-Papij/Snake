import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class Snake {
    private final int NUM_TILES_X;
    private final int NUM_TILES_Y;
    private ArrayList<Point> snake;
    private int direction;


    public Snake(int width, int height, int tileSize) {
        NUM_TILES_X = width / tileSize;
        NUM_TILES_Y = height / tileSize;
        snake = new ArrayList<>();
        snake.add(new Point(NUM_TILES_X / 2, NUM_TILES_Y / 2)); // Голова
        snake.add(new Point(NUM_TILES_X / 2 - 1, NUM_TILES_Y / 2)); // Первая часть тела
        snake.add(new Point(NUM_TILES_X / 2 - 2, NUM_TILES_Y / 2)); // Вторая часть тела
        direction = KeyEvent.VK_RIGHT;

    }

    public ArrayList<Point> getSnake() {
        return snake;
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



            snake.remove(snake.size() - 1); // Убираем хвост
    }
// TODO generalizeaza checkColision
public boolean checkDeathCollisions() {
    Point head = snake.get(0);


    if (isOutOfBounds(head)) {
        return true;
    }

    if (isCollidingWithSelf(head)) {
        return true;
    }

    return false;
}

    public boolean checkCollision(Point point){

        if (point.equals(snake.get(0))){
            return true;
        } else {
            return false;

        }
    }
// T
    public void addTail(){
//        snake.add(snake.size() -1,new Point(snake.get(snake.size()-1)));
        snake.add(snake.size() -1,new Point(100,100));

    }


    private boolean isOutOfBounds(Point head) {
        return head.x < 0 || head.x >= NUM_TILES_X || head.y < 0 || head.y >= NUM_TILES_Y;
    }

    private boolean isCollidingWithSelf(Point head) {
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



    public void restartGame() {
        snake.clear();
        snake.add(new Point(NUM_TILES_X / 2, NUM_TILES_Y / 2)); // Голова
        snake.add(new Point(NUM_TILES_X / 2 - 1, NUM_TILES_Y / 2)); // Первая часть тела
        snake.add(new Point(NUM_TILES_X / 2 - 2, NUM_TILES_Y / 2)); // Вторая часть тела
        direction = KeyEvent.VK_RIGHT;
    }
}
