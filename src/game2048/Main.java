package game2048;

import game2048.model.Board;
import game2048.ui.GamePanel;

import javax.swing.*;

/**
 * 2048 o'yinini ishga tushiruvchi asosiy sinf (Main Entry Point).
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("2048 O'yini (Java Swing OOP)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            Board board = new Board();
            GamePanel gamePanel = new GamePanel(board);

            frame.add(gamePanel);
            frame.pack();
            frame.setLocationRelativeTo(null); // Oynani ekran markazida ko'rsatish
            frame.setVisible(true);
        });
    }
}
