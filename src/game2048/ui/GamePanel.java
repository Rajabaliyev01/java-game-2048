package game2048.ui;

import game2048.model.Board;
import game2048.model.Direction;
import game2048.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 2048 O'yinining Grafik paneli (GUI View + Controller).
 * Doskani chizish, animatsiyali ko'rinish va klaviatura hodisalarini qabul qiladi.
 */
public class GamePanel extends JPanel {
    private static final int TILE_SIZE = 95;
    private static final int GAP = 12;
    private static final int HEADER_HEIGHT = 90;

    private final Board board;
    private boolean wonNoticeShown = false;

    public GamePanel(Board board) {
        this.board = board;
        int panelWidth = GAP + (TILE_SIZE + GAP) * Board.SIZE;
        int panelHeight = HEADER_HEIGHT + GAP + (TILE_SIZE + GAP) * Board.SIZE;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(new Color(0xfaf8ef));
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (board.isGameOver()) {
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        board.reset();
                        wonNoticeShown = false;
                        repaint();
                    }
                    return;
                }

                boolean moved = switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT, KeyEvent.VK_A  -> board.move(Direction.LEFT);
                    case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> board.move(Direction.RIGHT);
                    case KeyEvent.VK_UP, KeyEvent.VK_W    -> board.move(Direction.UP);
                    case KeyEvent.VK_DOWN, KeyEvent.VK_S  -> board.move(Direction.DOWN);
                    case KeyEvent.VK_R                   -> {
                        board.reset();
                        wonNoticeShown = false;
                        yield true;
                    }
                    default -> false;
                };

                if (moved) {
                    repaint();
                    if (!wonNoticeShown && board.hasWon()) {
                        wonNoticeShown = true;
                        JOptionPane.showMessageDialog(GamePanel.this,
                                "Tabriklaymiz! Siz 2048 ga erishdingiz! Davom ettirishingiz mumkin.",
                                "G'alaba!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawHeader(g2);
        drawBoard(g2);

        if (board.isGameOver()) {
            drawGameOverOverlay(g2);
        }
    }

    private void drawHeader(Graphics2D g2) {
        // O'yin nomi
        g2.setColor(new Color(0x776e65));
        g2.setFont(new Font("Arial", Font.BOLD, 42));
        g2.drawString("2048", GAP, 55);

        // Score taxtasi
        int scoreBoxWidth = 140;
        int scoreBoxHeight = 55;
        int scoreBoxX = getWidth() - GAP - scoreBoxWidth;
        int scoreBoxY = 15;

        g2.setColor(new Color(0xbbada0));
        g2.fillRoundRect(scoreBoxX, scoreBoxY, scoreBoxWidth, scoreBoxHeight, 10, 10);

        g2.setColor(new Color(0xeee4da));
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fmTitle = g2.getFontMetrics();
        String title = "SCORE";
        g2.drawString(title, scoreBoxX + (scoreBoxWidth - fmTitle.stringWidth(title)) / 2, scoreBoxY + 20);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 22));
        FontMetrics fmScore = g2.getFontMetrics();
        String scoreStr = String.valueOf(board.getScore());
        g2.drawString(scoreStr, scoreBoxX + (scoreBoxWidth - fmScore.stringWidth(scoreStr)) / 2, scoreBoxY + 45);

        // Qisqa yo'riqnoma
        g2.setColor(new Color(0x8f7a66));
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        g2.drawString("Strelkalar / WASD orqali boshqaring. R - Yangilash", GAP, HEADER_HEIGHT - 8);
    }

    private void drawBoard(Graphics2D g2) {
        int boardWidth = Board.SIZE * TILE_SIZE + (Board.SIZE + 1) * GAP;
        int startY = HEADER_HEIGHT;

        // Doska foni
        g2.setColor(new Color(0xbbada0));
        g2.fillRoundRect(GAP / 2, startY, boardWidth - GAP, boardWidth - GAP, 14, 14);

        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                int x = GAP + c * (TILE_SIZE + GAP);
                int y = startY + GAP + r * (TILE_SIZE + GAP);
                drawTile(g2, board.getTile(r, c), x, y);
            }
        }
    }

    private void drawTile(Graphics2D g2, Tile tile, int x, int y) {
        g2.setColor(tile.getBackgroundColor());
        g2.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, 10, 10);

        if (!tile.isEmpty()) {
            int val = tile.getValue();
            String s = String.valueOf(val);

            int fontSize = val < 100 ? 36 : (val < 1000 ? 30 : 24);
            g2.setFont(new Font("Arial", Font.BOLD, fontSize));
            g2.setColor(tile.getTextColor());

            FontMetrics fm = g2.getFontMetrics();
            int tx = x + (TILE_SIZE - fm.stringWidth(s)) / 2;
            int ty = y + (TILE_SIZE - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(s, tx, ty);
        }
    }

    private void drawGameOverOverlay(Graphics2D g2) {
        g2.setColor(new Color(238, 228, 218, 190));
        g2.fillRect(0, HEADER_HEIGHT, getWidth(), getHeight() - HEADER_HEIGHT);

        g2.setColor(new Color(0x776e65));
        g2.setFont(new Font("Arial", Font.BOLD, 38));
        FontMetrics fm = g2.getFontMetrics();
        String overText = "O'yin tugadi!";
        g2.drawString(overText, (getWidth() - fm.stringWidth(overText)) / 2, HEADER_HEIGHT + 180);

        g2.setFont(new Font("Arial", Font.PLAIN, 18));
        FontMetrics fmSub = g2.getFontMetrics();
        String restartText = "Qayta boshlash uchun 'R' tugmasini bosing";
        g2.drawString(restartText, (getWidth() - fmSub.stringWidth(restartText)) / 2, HEADER_HEIGHT + 230);
    }
}
