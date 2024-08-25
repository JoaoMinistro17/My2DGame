package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Milos Adventure 2D");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //causes the window to be sized to fit the preferred size and layouts of its subcomponents (=Main.GamePanel)
        window.pack();

        //window will be displayed in the center of the screen
        window.setLocationRelativeTo(null);

        //so you can see the window
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}