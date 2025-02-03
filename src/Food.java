import java.awt.*;
import java.util.Random;

public class Food {

    private Point food;
    private final int NUM_TILES_X;
    private int NUM_TILES_Y;

    public void spawnFood() {
        Random rand = new Random();
        food = new Point(rand.nextInt(NUM_TILES_X), rand.nextInt(NUM_TILES_Y));
    }
    public Food(int width, int height, int tileSize){
        NUM_TILES_X = width / tileSize;
        NUM_TILES_Y = height / tileSize;
        this.spawnFood();
    }
    public Point getFood(){
        return food ;
    }
}
