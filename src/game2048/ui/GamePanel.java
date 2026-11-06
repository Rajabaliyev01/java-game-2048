package game2048.ui;

import game2048.model.Board;
import game2048.model.Direction;
import game2048.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * Zamonaviy Dark Blue Gradient & Animatsiyali 2048 O'yin Paneli.
 * - Silliq paydo bo'lish (Pop-in animation)
 * - Yumshoq soya va neon porlash effekti (Glow FX)
 * - Chuqur gradient fonlar va zamonaviy Cyberpunk/Glassmorphism uslubi
 */
public class GamePanel extends JPanel {
    private static final int TILE_SIZE = 100;
    private static final int GAP = 14;
    private static final int HEADER_HEIGHT = 110;

    private final Board board;
    private boolean wonNoticeShown = false;

    // Animatsiya uchun ko'rsatkichlar
    private float spawnAnimationProgress = 1.0f; // 0.0 -> 1.0 oralig'ida kattalashadi
    private final Timer spawnTimer;

    public GamePanel(Board board) {
        this.board = board;
        int panelWidth = GAP + (TILE_SIZE + GAP) * Board.SIZE;
        int panelHeight = HEADER_HEIGHT + GAP + (TILE_SIZE + GAP) * Board.SIZE + 10;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setFocusable(true);

        // 60 FPS silliq pop-in animatsiya taymeri
        spawnTimer = new Timer(16, e -> {
            spawnAnimationProgress += 0.12f;
            if (spawnAnimationProgress >= 1.0f) {
                spawnAnimationProgress = 1.0f;
                ((Timer) e.getSource()).stop();
            }
            repaint();
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (board.isGameOver()) {
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        board.reset();
                        wonNoticeShown = false;
                        triggerSpawnAnimation();
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
                    triggerSpawnAnimation();
                    if (!wonNoticeShown && board.hasWon()) {
                        wonNoticeShown = true;
                        JOptionPane.showMessageDialog(GamePanel.this,
                                "🎉 Tabriklaymiz! 2048 ga erishdingiz! Davom etaverishingiz mumkin.",
                                "Ajoyib Natija!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    private void triggerSpawnAnimation() {
        spawnAnimationProgress = 0.2f;
        spawnTimer.restart();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Grafik sifatini maksimal qilish (Antialiasing)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 1. Asosiy fon (Chuqur Midnight Blue Dark Gradient)
        GradientPaint bgGradient = new GradientPaint(
                0, 0, new Color(0x0a, 0x0f, 0x1d),
                0, getHeight(), new Color(0x0f, 0x17, 0x2a)
        );
        g2.setPaint(bgGradient);
        g2.fillRect(0, 0, getWidth(), getHeight());

        drawHeader(g2);
        drawBoard(g2);

        if (board.isGameOver()) {
            drawGameOverOverlay(g2);
        }

        g2.dispose();
    }

    private void drawHeader(Graphics2D g2) {
        // Logo matni (Neon Blue gradient)
        g2.setFont(new Font("SF Pro Display", Font.BOLD, 46));
        GradientPaint titleGradient = new GradientPaint(
                GAP, 30, new Color(0x38, 0xbd, 0xf8),
                GAP + 120, 75, new Color(0x81, 0x8c, 0xf8)
        );
        g2.setPaint(titleGradient);
        g2.drawString("2048", GAP + 4, 62);

        // Kichik taglavha
        g2.setColor(new Color(0x64, 0x74, 0x8b));
        g2.setFont(new Font("Arial", Font.BOLD, 11));
        g2.drawString("CYBER BLUE EDITION", GAP + 6, 82);

        // SCORE Kartochkasi (Glassmorphism + Dark Blue border)
        int scoreBoxWidth = 145;
        int scoreBoxHeight = 62;
        int scoreBoxX = getWidth() - GAP - scoreBoxWidth;
        int scoreBoxY = 20;

        // Kartochka foni
        GradientPaint scoreBg = new GradientPaint(
                scoreBoxX, scoreBoxY, new Color(0x1e, 0x29, 0x3b, 220),
                scoreBoxX, scoreBoxY + scoreBoxHeight, new Color(0x0f, 0x17, 0x2a, 240)
        );
        g2.setPaint(scoreBg);
        g2.fill(new RoundRectangle2D.Float(scoreBoxX, scoreBoxY, scoreBoxWidth, scoreBoxHeight, 16, 16));

        // Chegaradagi neon ko'k chiziq
        g2.setColor(new Color(0x38, 0xbd, 0xf8, 90));
        g2.setStroke(new BasicStroke(1.5f));
        g2.draw(new RoundRectangle2D.Float(scoreBoxX, scoreBoxY, scoreBoxWidth, scoreBoxHeight, 16, 16));

        // Score matnlari
        g2.setColor(new Color(0x94, 0xa3, 0xb8));
        g2.setFont(new Font("Arial", Font.BOLD, 11));
        FontMetrics fmLabel = g2.getFontMetrics();
        String label = "SCORE";
        g2.drawString(label, scoreBoxX + (scoreBoxWidth - fmLabel.stringWidth(label)) / 2, scoreBoxY + 22);

        g2.setColor(new Color(0x38, 0xbd, 0xf8));
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fmScore = g2.getFontMetrics();
        String scoreStr = String.valueOf(board.getScore());
        g2.drawString(scoreStr, scoreBoxX + (scoreBoxWidth - fmScore.stringWidth(scoreStr)) / 2, scoreBoxY + 50);

        // Pastki yo'riqnoma
        g2.setColor(new Color(0x64, 0x74, 0x8b));
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        g2.drawString("WASD / Strelkalar orqali boshqaring  •  [R] Qayta boshlash", GAP + 4, HEADER_HEIGHT - 6);
    }

    private void drawBoard(Graphics2D g2) {
        int boardWidth = Board.SIZE * TILE_SIZE + (Board.SIZE + 1) * GAP;
        int startY = HEADER_HEIGHT;

        // Doska ramkasi foni (Chuqur Dark Blue)
        g2.setColor(new Color(0x0d, 0x15, 0x27));
        g2.fill(new RoundRectangle2D.Float(GAP / 2f, startY, boardWidth - GAP, boardWidth - GAP, 22, 22));

        // Doska chetidagi nafis porlash
        g2.setColor(new Color(0x1e, 0x29, 0x3b));
        g2.setStroke(new BasicStroke(2f));
        g2.draw(new RoundRectangle2D.Float(GAP / 2f, startY, boardWidth - GAP, boardWidth - GAP, 22, 22));

        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                int x = GAP + c * (TILE_SIZE + GAP);
                int y = startY + GAP + r * (TILE_SIZE + GAP);
                Tile tile = board.getTile(r, c);

                boolean isNewSpawn = (r == board.getLastSpawnRow() && c == board.getLastSpawnCol());
                drawAnimatedTile(g2, tile, x, y, isNewSpawn);
            }
        }
    }

    private void drawAnimatedTile(Graphics2D g2, Tile tile, int x, int y, boolean isNewSpawn) {
        if (tile.isEmpty()) {
            // Bo'sh katakchaning yumshoq qora-ko'k foni
            g2.setColor(new Color(0x15, 0x1f, 0x33));
            g2.fill(new RoundRectangle2D.Float(x, y, TILE_SIZE, TILE_SIZE, 16, 16));
            return;
        }

        // Animatsiya o'lchami hisobi (Spawn pop-in)
        float scale = isNewSpawn ? spawnAnimationProgress : 1.0f;
        int renderSize = (int) (TILE_SIZE * scale);
        int offsetX = (TILE_SIZE - renderSize) / 2;
        int offsetY = (TILE_SIZE - renderSize) / 2;

        int drawX = x + offsetX;
        int drawY = y + offsetY;

        // 1. Neon Glow (Porlash soyasi)
        g2.setColor(tile.getGlowColor());
        g2.fill(new RoundRectangle2D.Float(drawX - 3, drawY - 3, renderSize + 6, renderSize + 6, 20, 20));

        // 2. Blue Dark Gradiyentli plitka
        GradientPaint tileGrad = new GradientPaint(
                drawX, drawY, tile.getGradientStart(),
                drawX + renderSize, drawY + renderSize, tile.getGradientEnd()
        );
        g2.setPaint(tileGrad);
        g2.fill(new RoundRectangle2D.Float(drawX, drawY, renderSize, renderSize, 16, 16));

        // 3. Yuqorigi yorug'lik refleksi (Glass shine effect)
        GradientPaint shine = new GradientPaint(
                drawX, drawY, new Color(255, 255, 255, 55),
                drawX, drawY + renderSize / 2f, new Color(255, 255, 255, 0)
        );
        g2.setPaint(shine);
        g2.fill(new RoundRectangle2D.Float(drawX, drawY, renderSize, renderSize / 2f, 16, 16));

        // 4. Nozik chetki neon chiziq
        g2.setColor(new Color(255, 255, 255, 45));
        g2.setStroke(new BasicStroke(1.2f));
        g2.draw(new RoundRectangle2D.Float(drawX, drawY, renderSize, renderSize, 16, 16));

        // 5. Raqam matni
        int val = tile.getValue();
        String s = String.valueOf(val);

        int baseFont = val < 100 ? 38 : (val < 1000 ? 32 : (val < 10000 ? 25 : 20));
        int fontSize = Math.max(10, (int) (baseFont * scale));
        g2.setFont(new Font("Arial", Font.BOLD, fontSize));

        // Yengil matn soyasi (Contrast uchun)
        g2.setColor(new Color(0, 0, 0, 100));
        FontMetrics fm = g2.getFontMetrics();
        int tx = drawX + (renderSize - fm.stringWidth(s)) / 2;
        int ty = drawY + (renderSize - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(s, tx + 1, ty + 2);

        // Haqiqiy matn
        g2.setColor(tile.getTextColor());
        g2.drawString(s, tx, ty);
    }

    private void drawGameOverOverlay(Graphics2D g2) {
        // Blur / Qorong'ilashtirish
        g2.setColor(new Color(0x0a, 0x0f, 0x1d, 225));
        g2.fillRect(0, HEADER_HEIGHT, getWidth(), getHeight() - HEADER_HEIGHT);

        g2.setColor(new Color(0xf4, 0x3f, 0x5e)); // Qizil neon
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics fm = g2.getFontMetrics();
        String overText = "O'yin Yakunlandi";
        g2.drawString(overText, (getWidth() - fm.stringWidth(overText)) / 2, HEADER_HEIGHT + 180);

        g2.setColor(new Color(0x94, 0xa3, 0xb8));
        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        FontMetrics fmSub = g2.getFontMetrics();
        String restartText = "Qayta boshlash uchun [R] tugmasini bosing";
        g2.drawString(restartText, (getWidth() - fmSub.stringWidth(restartText)) / 2, HEADER_HEIGHT + 230);
    }
}
