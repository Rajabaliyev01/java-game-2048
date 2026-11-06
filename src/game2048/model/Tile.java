package game2048.model;

import java.awt.Color;

/**
 * Katakcha (Tile) modeli - Blue Dark Gradient va zamonaviy neon/glow uslubi bilan.
 * OOP prinsipi: Encapsulation - har bir plitka o'z rangi, gradiyenti va porlashini o'zi biladi.
 */
public class Tile {
    private final int value;

    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    /**
     * Blue Dark Gradientning yuqori rangi (Gradient Start)
     */
    public Color getGradientStart() {
        return switch (value) {
            case 2    -> new Color(0x1e3a8a); // Chuqur ko'k (Royal Blue)
            case 4    -> new Color(0x2563eb); // Yorqin ko'k (Electric Blue)
            case 8    -> new Color(0x0ea5e9); // Moviy (Sky Blue)
            case 16   -> new Color(0x06b6d4); // Tsian (Cyan)
            case 32   -> new Color(0x14b8a6); // Teal / Feruza
            case 64   -> new Color(0x10b981); // Zumrad yashil
            case 128  -> new Color(0x6366f1); // Indigo neon
            case 256  -> new Color(0x8b5cf6); // Binafsha (Violet)
            case 512  -> new Color(0xa855f7); // Purpur (Purple)
            case 1024 -> new Color(0xd946ef); // Fuchsia neon
            case 2048 -> new Color(0x00f5d4); // Super Cyberpunk Neon Cyan
            default   -> value > 2048 ? new Color(0xf43f5e) : new Color(0x1e293b, true); // Bo'sh slot
        };
    }

    /**
     * Blue Dark Gradientning pastki rangi (Gradient End)
     */
    public Color getGradientEnd() {
        return switch (value) {
            case 2    -> new Color(0x172554);
            case 4    -> new Color(0x1d4ed8);
            case 8    -> new Color(0x0284c7);
            case 16   -> new Color(0x0891b2);
            case 32   -> new Color(0x0d9488);
            case 64   -> new Color(0x059669);
            case 128  -> new Color(0x4338ca);
            case 256  -> new Color(0x6d28d9);
            case 512  -> new Color(0x7e22ce);
            case 1024 -> new Color(0xa21caf);
            case 2048 -> new Color(0x7b2cbf);
            default   -> value > 2048 ? new Color(0xbe123c) : new Color(0x0f172a, true);
        };
    }

    /**
     * Katta raqamlardagi Neon porlash (Glow) rangi
     */
    public Color getGlowColor() {
        if (value >= 1024) return new Color(0x00, 0xf5, 0xd4, 80);
        if (value >= 128)  return new Color(0x8b, 0x5c, 0xf6, 70);
        if (value >= 16)   return new Color(0x06, 0xb6, 0xd4, 50);
        return new Color(0x3b, 0x82, 0xf6, 30);
    }

    /**
     * Shrift rangi
     */
    public Color getTextColor() {
        if (value == 2048) {
            return Color.WHITE;
        }
        return value <= 4 ? new Color(0xe2e8f0) : Color.WHITE;
    }
}
