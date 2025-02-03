import javax.swing.*;
import java.awt.event.*;

public class GameMenu {
    private JFrame frame;
    private JButton startButton;
    private JButton settingButton;
    private JButton exitButton;
    private JPanel settingsPanel;

    public GameMenu() {
        // Crearea ferestrei principale
        frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);

        // Dimensiunea ferestrei
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);// Centrarea ferestrei pe ecran

        // Crearea butoanelor
        startButton = new JButton("New Game");
        settingButton = new JButton("Settings");
        exitButton = new JButton("Exit");

        // Instalam butoanele shi fereastra
        int screenWidth = frame.getWidth();
        int screenHeight = frame.getHeight();
        int buttonWidth = 300;
        int buttonHeight = 40;

        // Calculam pozitia butoanelor în centru
        int startX = (screenWidth - buttonWidth) / 2;
        int startY = (screenHeight - buttonHeight) / 2 - 40;

        // Pozitia altor butoane
        int settingY = startY + buttonHeight + 10;
        int exitY = settingY + buttonHeight + 10;

        // Setarea poziției butoanelor
        startButton.setBounds(startX, startY, buttonWidth, buttonHeight);
        settingButton.setBounds(startX, settingY, buttonWidth, buttonHeight);
        exitButton.setBounds(startX, exitY, buttonWidth, buttonHeight);

        // Adaugam un handler pentru butonul „New Game”.
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame(); // Logica de pornire a jocului
                frame.setVisible(false); // Ascundem meniul
            }
        });

        // Adaugam un handler pentru butonul „Setări”.
        settingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleSettingsPanel(); // Schimbarea vizibilității panoului de setari
            }
        });

        // Adaugam un handler pentru butonul „Iesire”.
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Inchiderea jocului
            }
        });

        // Creem un panou de setari
        settingsPanel = new JPanel();
        settingsPanel.setBounds(50, 400, 300, 200);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS)); // Dispunerea verticală a elementelor

        // Adaugam controalelor pentru setari
        JCheckBox soundCheckBox = new JCheckBox("Enable Sound");
        settingsPanel.add(soundCheckBox);

        JSlider volumeSlider = new JSlider(0, 100, 50);
        settingsPanel.add(volumeSlider);

        // Buton pentru aplicarea setarilor
        JButton applyButton = new JButton("Apply Settings");
        settingsPanel.add(applyButton);

        // Panoul de setari este ascuns in mod implicit
        settingsPanel.setVisible(false);

        // Adaugam butoanele in fereastra
        frame.add(startButton);
        frame.add(settingButton);
        frame.add(exitButton);
        frame.add(settingsPanel); // Adaugarea unui panou de setari la fereastra

        // Afisarea ferestrei
        frame.setVisible(true);
    }

    private void startGame() {
        // Se creează fereastra de joc cu logica pentru "SnakeGame"
        JFrame gameFrame = new JFrame("Snake Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(1920, 1080);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.add(new GameLogic());
        gameFrame.setVisible(true);
    }

    // Metoda de comutare a vizibilitații panoului de setari
    private void toggleSettingsPanel() {
        settingsPanel.setVisible(!settingsPanel.isVisible()); // Comutarea vizibilitatea
    }

    public static void main(String[] args) {
        new GameMenu();
    }
}
