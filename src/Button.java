import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton {

    public Button(int x, int y) {
        setText("Restart");
        setBounds(x, y, 100, 40);
        setFont(new Font("Arial", Font.BOLD, 16));
        setVisible(false);
    }

    public void addRestartActionListener(ActionListener listener) {
        addActionListener(listener);
    }
}
