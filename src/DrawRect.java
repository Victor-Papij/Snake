import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class DrawRect extends JPanel {
    private static final int RECT_X = 100;
    private int rectY = 200; // Начальное положение по вертикали (не статическое)
    private static final int RECT_WIDTH = 20;
    private static final int RECT_HEIGHT = RECT_WIDTH;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // рисуем прямоугольник
        g.drawRect(RECT_X, rectY, RECT_WIDTH, RECT_HEIGHT);
    }

    @Override
    public Dimension getPreferredSize() {
        // Размер панели должен быть достаточным для отображения прямоугольника
        return new Dimension(RECT_X + RECT_WIDTH + 10, rectY + RECT_HEIGHT + 10);
    }

    public void moveRectangle() {
        rectY--; // Двигаем прямоугольник вверх на 1 пиксель
        repaint(); // Перерисовываем панель, чтобы отобразить изменения
    }

    public DrawRect() {
        // Добавляем обработчик нажатий клавиш
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Если нажата клавиша 'W', двигаем прямоугольник
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    moveRectangle();
                }
            }
        });

        // Делаем панель фокусируемой для получения событий клавиш
        setFocusable(true);
        requestFocusInWindow();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Move Rectangle");
        DrawRect panel = new DrawRect();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}